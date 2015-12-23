package uoc.ei.practica;

/** 
 * Interfície que defineix tots els missatges necessaris per a les excepcions generades
 *
 * @author   Equip docent de Disseny d'Estructura de Dades de la UOC
 * @version  Tardor 2015
 */
public interface Messages {
	
	public static final String LS = System.getProperty("line.separator");
	public static final String PREFIX = "\t";
	
	public static final String NO_USERS = "There are no users";
	public static final String USER_NOT_FOUND = "User not found";
	public static final String NO_CHANNELS = "There are no channels";
	public static final String CHANNEL_NOT_FOUND = "Channel not found";
	public static final String NO_PROGRAMS = "There are no programs";
	public static final String PROGRAM_NOT_FOUND = "Program not found";
	public static final String MESSAGE_ALREADY_EXISTS = "Message already exists";
	public static final String MESSAGE_NOT_FOUND = "Message not found";
	public static final String NO_PROGRAM_SUBSTITUTE = "There are no program substitutes";
	
}
