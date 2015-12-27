package uoc.ei.practica;

import java.util.Date;

import uoc.ei.tads.DiccionariAVLImpl;
import uoc.ei.tads.Iterador;

public class TVUOCManagerImpl implements TVUOCManager {
	
	/**
	 * Llista encadenada d'usuarius
	 */
	private IdentifiedList<Channel> channels;
		
	/**
	 * vector dels 10 programes amb més visites del sistema
	 */
	private OrderedVector<Program> top10;
	
	/**
	 * Diccionari AVL d'usuaris.
	 */ 
	private DiccionariAVLImpl<String, User> mUsers;
	
	/**
	 * apuntador al programa amb millor valoració
	 */
	private Program topRating;
	
	public TVUOCManagerImpl() {
		
		this.channels = new IdentifiedList<Channel>();
		this.top10= new OrderedVector<Program>(TVUOCManager.TOP_10, Program.PROGRAM_VIEWS_CMP);
		this.mUsers = new DiccionariAVLImpl<String, User>();
		this.topRating=null;
	}

	@Override
	public void addUser(String idUser, String email, String password) throws EIException {
		
		User pUser = this.mUsers.consultar(idUser);
		if (pUser==null) {
			pUser = new User(idUser, email, password);
			this.mUsers.afegir(idUser, pUser);
		}
		else pUser.update(email, password);
	}
		
	
	@Override
	public void addChannel(String idChannel, String name, String description) throws EIException {
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
				
		channel = new Channel(idChannel, name, description);
		this.channels.afegirAlFinal(channel);
				
	}

	@Override
	public void addProgram(String id, String name, String description, String idChannel) throws EIException {
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		Program p = new Program (id, name, description);
		channel.addProgram(p);
	}


	@Override
	public void registerView(String idChannel, String idProgram, String idUser, Date dateTime) throws EIException {
				
		User user = this.mUsers.consultar(idUser);
				
		if (user == null) throw new EIException(Messages.USER_NOT_FOUND);
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		if (channel == null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		
		Program p = channel.getProgram(idProgram);
		if (p == null) throw new EIException(Messages.PROGRAM_NOT_FOUND);
		
		p.inc();
		user.addView(p);
		this.top10.update(p);
		channel.updateTop10(p);
		
	}

	
	private void updateTopRating(Program p) {		
		
		if ((this.topRating==null) || (this.topRating!=null && p.rating()>0 && this.topRating.rating()<p.rating())) 
			this.topRating=p;

	}
	
	
	@Override
	public void rateProgram(String idChannel, String idProgram, int rating) throws EIException {
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		Program program = channel.getProgram(idProgram);
		
		program.addRating(rating);
		updateTopRating(program);
		
	}


	@Override
	public Iterador<View> getUserViews(String idUser) {
		
		User u = this.mUsers.consultar(idUser);
		return u.views();
	}


	@Override
	public Iterador<Program> getTop10Programs() throws EIException {
		
		if (this.top10.estaBuit()) throw new EIException(Messages.NO_PROGRAMS);
		return this.top10.elements();
	}


	@Override
	public Iterador<Program> getChannelTop10Programs(String idChannel) throws EIException {

		Channel channel = this.channels.getIdentifiedObject(idChannel);
		if (channel==null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		return channel.top10();
	}


	@Override
	public Program topRating() throws EIException {
		
		if (this.topRating==null) throw new EIException(Messages.NO_PROGRAMS);
		return this.topRating;
		
	}

	@Override
	public Iterador<User> users() throws EIException {		
		
		if (this.mUsers.estaBuit()) throw new EIException(Messages.NO_USERS);
		return this.mUsers.elements();
	}

	public Iterador<Channel> channels() throws EIException {
		
		if (this.channels.estaBuit()) throw new EIException(Messages.NO_CHANNELS);		
		return this.channels.elements();
	}

		
	@Override
	public Iterador<Program> programs(String idChannel) throws EIException {

		Channel c = this.channels.getIdentifiedObject(idChannel);
		if (c==null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		Iterador<Program> it = c.programs();
		if (!it.hiHaSeguent()) throw new EIException(Messages.NO_PROGRAMS);
	
		return it;
	}


	@Override
	public Program program(String idChannel, String idProgram) throws EIException {
		
		Channel c = this.channels.getIdentifiedObject(idChannel);
		if (c==null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		Program p = c.getProgram(idProgram);
		if (p==null) throw new EIException(Messages.PROGRAM_NOT_FOUND);
		return p;
	}


	@Override
	public void addMessage(String idChannel, String idProgram, String idUser,
			String title, String message) throws EIException {
		
		User user = this.mUsers.consultar(idUser);
		if (user == null) throw new EIException(Messages.USER_NOT_FOUND);
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		if (channel == null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		
		Program p = channel.getProgram(idProgram);
		if (p == null) throw new EIException(Messages.PROGRAM_NOT_FOUND);
		
		Message m = p.getMessage(title);
		if (m != null) throw new EIException(Messages.MESSAGE_ALREADY_EXISTS);
		
		p.addMessage(idChannel, idProgram, idUser, title, message);

	}

	public Iterador<Message> getMessages(String idChannel, String idProgram) throws EIException {
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		Program p = channel.getProgram(idProgram);
		
		return p.mMessage();
					
	}

	@Override
	public void removeMessage(String idChannel, String idProgram, String title) throws EIException {
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		if (channel == null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		
		Program p = channel.getProgram(idProgram);
		if (p == null) throw new EIException(Messages.PROGRAM_NOT_FOUND);
		
		Message m = p.getMessage(title);
		if (m == null) throw new EIException(Messages.MESSAGE_NOT_FOUND);
		
		p.removeMessage(idChannel, idProgram, title);
		
	}
       
	@Override
	public void addSubstituteProgram(String idChannel, String idProgram, String idSubstituteProgram, String name, String description,int priority) throws EIException {
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		if (channel == null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		
		Program p = channel.getProgram(idProgram);
		if (p == null) throw new EIException(Messages.PROGRAM_NOT_FOUND);
		
		SubstituteProgram psubstitute = new SubstituteProgram(idSubstituteProgram, name, description, priority);
		p.addSubstituteProgram(psubstitute);
		
	}
	
	@Override
	public void substituteProgram(String idChannel, String idProgram) throws EIException {
		
		Channel channel = this.channels.getIdentifiedObject(idChannel);
		if (channel == null) throw new EIException(Messages.CHANNEL_NOT_FOUND);
		
		Program program = channel.getProgram(idProgram);
		Program sub = null;		
		
		sub = program.desencuarCua();
		
		sub.setCua(program.getCuaAmbPrioritat()); 
		
		channel.addProgram(sub);
		
		program.setEnabled(true); 

		sub.setEnabled(false);

	}

}
