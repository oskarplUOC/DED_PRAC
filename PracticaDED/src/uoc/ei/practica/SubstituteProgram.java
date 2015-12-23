package uoc.ei.practica;

import java.util.Comparator;


public class SubstituteProgram extends IdentifiedObject{
	
	@SuppressWarnings("rawtypes")
	public static final Comparator SUBSTITUTEPROGRAM_BY_PRIORITY_COMPARATOR = new Comparator<SubstituteProgram>(){
		
		@Override
		public int compare(SubstituteProgram substituteProgram1, SubstituteProgram substituteProgram0) {
			
			return (int)(substituteProgram1.getPriority()-substituteProgram0.getPriority());
		}
		
	};
	
	private String idChannel;
	
	private String idProgram;
	
	private String idSubstituteProgram; 
	
	private String name;
	
	private String description;
	
	private int priority;
	
	public SubstituteProgram (String idChannel, String idProgram, String idSubstituteProgram, String name, String description, int priority){

		super (idSubstituteProgram);
		this.idChannel = idChannel;
		this.idProgram = idProgram;
		this.name = name;
		this.description = description;
		this.priority = priority;
	}	
	
	public String getIdSubstituteProgram() {
		return idSubstituteProgram;
	}

	public void setIdSubstituteProgram(String idSubstituteProgram) {
		this.idSubstituteProgram = idSubstituteProgram;
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getIdProgram() {
		return idProgram;
	}

	public void setIdProgram(String idProgram) {
		this.idProgram = idProgram;
	}

	public String getIdChannel() {
		return idChannel;
	}

	public void setIdChannel(String idChannel) {
		this.idChannel = idChannel;
	}
	
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer(this.getIdentifier() + ", " + name + ", " + description + "," + priority);
		return sb.toString();
	}
	
	
}
