package uoc.ei.practica;

import java.util.Comparator;


public class SubstituteProgram extends Program {
	
	@SuppressWarnings("rawtypes")
	   public static final Comparator SUBSTITUTEPROGRAM_BY_PRIORITY_COMPARATOR = new Comparator<SubstituteProgram>(){

	      @Override
	      public int compare(SubstituteProgram substituteProgram1, SubstituteProgram substituteProgram0) {

	         return (int)(substituteProgram0.getPriority()-substituteProgram1.getPriority());
	      }

	   };
	
	private int priority;
	
	public SubstituteProgram (String id, String name, String description, int priority){
		
		super (id,name,description);
		this.setPriority(priority);
	}	
	
	public int getPriority() {
		
		return priority;
	}

	public void setPriority(int priority) {
		
		this.priority = priority;
	}

		
	@Override
	public String toString() {
		
		StringBuffer sb = new StringBuffer(this.getId() + ", " + this.getName()+ ", " + this.getDescription() + ", " + "views(" + this.getViews() + "), " + "rating(" + this.rating() + "), " + priority);
		return sb.toString();
	}

}
