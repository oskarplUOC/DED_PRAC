package uoc.ei.practica;

public class Message extends IdentifiedObject {
	
	private String idChannel;
	
	private String idProgram;
	
	private String idUser;
	
	private String title;
	
	private String message;

	public Message(String idChannel, String idProgram, String idUser, String title, String message) {
		
		this.idChannel = idChannel;
		this.idProgram = idProgram;
		this.idUser = idUser;
		this.title = title;
		this.message = message;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.idUser).append(" [");
		sb.append(this.title).append("] ");
		sb.append(this.message).append(" ");
		return sb.toString();
	}
	
	public String getIdChannel() {
		return idChannel;
	}

	public void setIdChannel(String idChannel) {
		this.idChannel = idChannel;
	}

	public String getIdProgram() {
		return idProgram;
	}

	public void setIdProgram(String idProgram) {
		this.idProgram = idProgram;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
