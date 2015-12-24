package uoc.ei.practica;

import java.util.Date;

import uoc.ei.tads.Iterador;

/** 
 * Definició del TAD de gestió de programes de TVUOC
 */
public interface TVUOCManager {
	
	public static final int C= 50;
	public static final int TOP_10 = 10;
	public static final int PC = 100; 

	
	/*
     * Mètode que afegeix un nou usuari al sistema 
     * @pre cert
     * @post si el codi d’usuari és nou, els usuaris seran els mateixos més un 
     * nou usuari amb les dades indicades. Sinó les dades de l’usuari s’hauran
     * actualitzat amb les noves
	 *
     * @param userId identificador de l'usuari
     * @param email email del nou usuari
     * @param password password del nou usuari
     * @throws EIException  
     * 
     */
	public void addUser (String idUser, String email, String password) throws EIException;


	
	/**
	 * 
	 * @pre no existeix cap canal amb el codi idChannel
	 * @post els canals seran els mateixos més un nou canal amb les dades indicades
	 */
	public void addChannel(String idChannel, String name, String description) throws EIException;
	
	
	/**
	 * @pre  existeix un canal amb el codi idChannel, i no existeix cap programa amb el codi id
	 * @post els programes del canal idChannel seran els mateixos més un nou programa amb les dades indicades
	 */
	public void addProgram(String id, String name, String description, String idChannel) throws EIException;

	/**
	 * @pre  cert
	 * @post Si existeix el canal idChanel, el programa idProgram, i l'usuari idUser, les visualitzacions 
	 * de l'usuari idUser seran les mateixes més una nova visualització amb les dades indicades. En cas contrari retorna error.
	 */
	public void registerView(String idChannel, String idProgram, String idUser, Date dateTime) throws EIException;
	

	/**
	 * @pre  existeix un canal amb el codi idChannel, un programa amb idProgram dins del canal idChanel i rating és un valor entre 0 i5
	 * @post el promig de les valoracions del programa idProgram incorporarà aquesta nova valoració. 
	 */
	public void rateProgram(String idChannel, String idProgram, int rating) throws EIException;

	
	/**
	 * @pre  existeix un uduari amb codi idUser
	 * @post retorna un iterador per recórrer les visualitzacions realitzades per l'usuari idUser
	 */ 
	public Iterador<View> getUserViews(String idUser);

	/**
	 * @throws EIException 
	 * @pre  cert
	 * @post retorna un iterador per recórrer els 10 programes més vistos de TvUoc. En cas d'empat
	 *  a les darreres posicions dels top 10, descarta alguns dels empatats per ajustar-se al límit de 10 programes.
	 */
	public Iterador<Program> getTop10Programs() throws EIException;
	
	/**
	 * 	@throws EIException 
	 * @pre  existeix un canal amb codi idChannel
	 *	@post retorna un iterador per recórrer els 10 programes més vistos del canal idChannel. En cas d'empat a les darreres posicions dels top 10, descarta alguns dels empatats per ajustar-se al límit de 10 programes.
	 */
	public Iterador<Program> getChannelTop10Programs(String idChannel) throws EIException;


	/**
	 * @throws EIException 
	 * @pre  cert
	 * @post retorna el programa més ben valorat, o un d'ells en cas d'empat
	 */
	public Program topRating() throws EIException;

	/**
	 * mètode que proporciona els usuaris del sistema
	 */
	public Iterador<User> users() throws EIException;
	
	/**
	 * mètode que proporciona els canals del sistema
	 */
	public Iterador<Channel> channels()  throws EIException;

	/**
	 * mètode que proporciona els programes del sistema
	 */
	public Iterador<Program> programs(String idChannel) throws EIException;
	
	/**
	 * mètode que proporciona els programe especificat
	 */
	public Program program(String idChannel, String idProgram) throws EIException;
	
	/**
	 * 
	 * mètode que afegeix un missatge a un programa
	 * @pre cert
     * @post Si existeix el canal idChannel, el programa idProgram, i l'usuari idUser i no existeix 
     * cap missatge amb el títol especificat, els missatges del programa seran les mateixes més 
     * un nou missatge amb les dades indicades. En cas contrari retorna error.
	 */
	
	public void addMessage(String idChannel, String idProgram, String idUser, String title, String message) throws EIException;

	/**
	 * métode que proporciona els missatges d'un programa
	 * @pre existeix un canal amb el codi idChannel, un programa amb idProgram dins del canal idChanel
	 * @post retorna un iterador per recórrer els missatges del programa
	 */
	public Iterador<Message> getMessages (String idChannel, String idProgram) throws EIException;
	
	/**
	 * mètode que esborra un missatge d'un programe
	 * @pre cert
	 * @post Si existeix el canal idChannel, el programa idProgram i el missatge amb el títol especificat, els missatges del programa seran les mateixes menys el missatge especificat. En cas contrari retorna error
	 */
	public void removeMessage(String idChannel, String idProgram, String title) throws EIException;

	/**
	 * mètode que afegeix un programa substitut a un programa existent
	 * @pre El programa identificat amb idSubstituteProgram no existeix
	 * @post Si existeix el canal idChannel i el programa idProgram l’estructura de dades dels programes candidats a substituir el programa idProgram seran els mateixos més un nou programa amb les dades indicades i la prioritat indicada. En cas contrari retorna error.
	 */
	
	public void addSubstituteProgram(String idChannel, String idProgram, String idSubstituteProgram, String name, String description, int priority) throws EIException;

	/**
	 * Mètode que realitza una substitució d'un programa. Per fer-ho possible s'han de fer els següents passos:
	 * 1.- Localitzar el programa (a partir del canal)
	 * 2.- Recuperar el programa substitut seguint el criteri de prioritat
	 * 3.- Marcar el programa que s'ha substituit com a deshabilitat (flag enable a false)
	 * 4.- Afegir el programa substitut al contenidor de programes associat al canal heretant els programes substituts	
	 * @pre cert
	 * @post Si existeix el canal idChanel i el programa idProgram els programes del canal ja no tindran el programa idProgram i, en el seu lloc, tindran el programa dels substituts amb més prioritat. Si l'identificador del canal o el programa a substituir no existeixen retorna un error. Si no hi ha cap programa per fer la substitució també retorna un error.
	 */
	public void substituteProgram(String idChannel, String idProgram) throws EIException;

	
}
