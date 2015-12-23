package uoc.ei.practica;

/**
 * classe que modela una visualització d'un programa
 */
public class View {

	private Program p;

	public View(Program p) {
		this.p=p;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(p.getId()+", "+p.getName()+", "+p.getDescription()+", views("+p.getViews()+")");
		
		return sb.toString();
	}
}
