package uoc.ei.practica;

import uoc.ei.tads.Diccionari;
import uoc.ei.tads.Iterador;
import uoc.ei.tads.TaulaDispersio;

/**
 * classe que modela un canal en el sistema
 *
 */
public class Channel extends IdentifiedObject {
	/**
	 * identificador del canal
	 */
	private String idChannel;
	
	/**
	 * nom del canal
	 */
	private String name;
	
	/**
	 * descripció del canal
	 */
	private String description;
	
	/**
	 * diccionari de programes
	 */
	private TaulaDispersio<String, Program> programs;
	
	/**
	 * vector dels 10 millors programes d'un canal (amb més visualitzacions)
	 */
	private OrderedVector<Program> top10;

	
	public Channel(String idChannel, String name, String description) {
		
		super(idChannel);
		this.name=name;
		this.description=description;
		this.programs = new TaulaDispersio<String, Program>();
		this.top10 = new OrderedVector<Program>(TVUOCManager.TOP_10, Program.PROGRAM_VIEWS_CMP);
	}
	
	public String getIdChannel() {
		return idChannel;
	}

	public void setIdChannel(String idChannel) {
		this.idChannel = idChannel;
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

	public boolean is(String idChannel) {
		return this.idChannel.equals(idChannel);
	}

	public void addProgram(Program p) {
		
		this.programs.afegir(p.getId(), p);
			
	}

	public Program getProgram(String id) {
		
		return this.programs.consultar(id);
			
	}
	
	public Iterador<Program> programs() {
		
		return this.programs.elements();
		
	}
	
	/**
	 * métode que permet actualitzar un programa d'un canal entre els 10 millors
	 * @param pIn programa a actualitzar si és un dels programes amb més visites del canal
	 */
	
	public void updateTop10(Program pIn) {		
		this.top10.update(pIn);
		
	}

	public Iterador<Program> top10() {
		return this.top10.elements();
	}
		
	/**
	 * mètode que proporciona una representació en forma de cadena de caracters d'un canal
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(this.identifier + ", " + name + ", " + description);
		if (!this.programs.estaBuit()) sb.append(this.programs);
		return sb.toString();
	}
}
