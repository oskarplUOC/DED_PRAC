package uoc.ei.practica;

import uoc.ei.tads.Iterador;
import uoc.ei.tads.LlistaEncadenada;

/**
 * classe que modela una entitat usuari
 */
public class User extends IdentifiedObject {

	private String idUser;
	/**
	 * email de l'usuari
	 */
	private String email;

	/**
	 * password de l'usuari
	 */
	private String password;
	
	/**
	 * llista encadenada de visualitzacions d'un usuari
	 */
	private LlistaEncadenada<View> views;
	
	public User(String idUser, String email, String password) {
		this.idUser = idUser;
		this.update(email, password);
		this.views=new LlistaEncadenada<View>();
	}

	/**
	 * mètode que modifica les dades de l'usuari
	 * @param email email de l'usuari
	 * @param password password de l'usuari
	 */
	public void update(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	/**
	 * mètode que proporciona una representació en forma de string d'un usuari
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.idUser).append(" [");
		sb.append(this.email).append("] ");
		sb.append(this.password).append(" ").append(Messages.LS);
		return sb.toString();
	}

	/**
	 * mètode que retorna l'identificador de l'usuari
	 * @return retorna l'identificador de l'usuari
	 */
	public String getIdUser() {
		return this.identifier;
	}

	public void addView(Program p) {
		this.views.afegirAlFinal(new View(p));
	}
	
	public Iterador<View> views() {
		
		return this.views.elements();
	}

}
