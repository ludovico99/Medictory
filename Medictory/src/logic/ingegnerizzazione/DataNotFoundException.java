package logic.ingegnerizzazione;

public class DataNotFoundException extends Exception{
private static final long serialVersionUID = 1L;
	
	public DataNotFoundException (String message){
		super(message);
	}
	
	public DataNotFoundException (Throwable cause) {
		super(cause);
	}

	public DataNotFoundException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}
}
