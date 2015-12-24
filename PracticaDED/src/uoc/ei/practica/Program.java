package uoc.ei.practica;

import java.util.Comparator;

import uoc.ei.tads.CuaAmbPrioritat;
import uoc.ei.tads.DiccionariAVLImpl;
import uoc.ei.tads.Iterador;

/**
 * mètode que modela un programa en el sistema
 *
 */
public class Program implements Comparable<Program>{
	
	/**
	 * contador de visites del programa
	 */
	private int views;
	
	/**
	 * identificador del programa
	 */
	private String id;
	
	/**
	 * nom del programa
	 */
	private String name;
	
	/**
	 * descripció del programa
	 */
	private String description;
	
	/**
	 * sumatori de la valoració 
	 */
	private double sumRating;
		
	/**
	 * nombre de valoracions existents
	 */
	private int nRating;
	
	/**
	 * Diccionari AVL de missatges.
	 */ 
	private DiccionariAVLImpl<String, Message> mMessage;
	
	
	private Boolean enabled;
	
	
	private CuaAmbPrioritat<SubstituteProgram> substitutePrograms;
	
	
	public static Comparator<String> PROGRAM_IDS_CMP = new Comparator<String>() {

		@Override
		public int compare(String arg0, String arg1) {
			return arg0.compareTo(arg1);
		}
	};

	public static Comparator<Program> PROGRAM_VIEWS_CMP = new Comparator<Program>() {

		@Override
		public int compare(Program arg0, Program arg1) {
			return arg0.views-arg1.views;
		}
	};

	@SuppressWarnings("unchecked")
	public Program(String id, String name, String description) {
		
		this.id=id;
		this.name=name;
		this.description=description;
		this.sumRating = 0;
		this.nRating=0;
		this.mMessage = new DiccionariAVLImpl<String, Message>();
		this.substitutePrograms = new CuaAmbPrioritat<SubstituteProgram>(SubstituteProgram.SUBSTITUTEPROGRAM_BY_PRIORITY_COMPARATOR);
	}

	public void inc() {
		views++;
	}

	public void addRating(int rating) {
		this.sumRating+=rating;
		this.nRating++;
	}

	public double rating() {						
		double ret =  (this.nRating!=0?this.sumRating/this.nRating:0);
		return ret;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer( id + ", " + name + ", " + description+" views("+this.views+") rating("+this.rating()+"):");
		return sb.toString();
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int compareTo(Program o) {
		
		return this.id.compareTo(o.id);
	}

	public Message getMessage(String title) {
		
		return this.mMessage.consultar(title);
	}
	
	public void addMessage(String idChannel, String idProgram, String idUser,String title, String message) {
		
		Message pMessage = new Message(idChannel, idProgram, idUser, title, message);
		this.mMessage.afegir(title, pMessage);  
	}
	
	public void removeMessage(String idChannel, String idProgram,String title) {
		
		this.mMessage.esborrar(title);
	}
	
	public Iterador<Message> mMessage() {
		
		return this.mMessage.elements();
			
	}
	
	@SuppressWarnings("rawtypes")
	public CuaAmbPrioritat getSubstitutePrograms() {
		
		return this.substitutePrograms;
		
	}
	
	public void addSubstituteProgram(String idSubstituteProgram, String name, String description,int priority) throws EIException {
		
		SubstituteProgram substituteProgramToAdd = new SubstituteProgram(idSubstituteProgram, name, description, priority);
		this.substitutePrograms.encuar(substituteProgramToAdd);
						
	}
	
}
