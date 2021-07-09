package logic.ingegnerizzazione;

public class DateException extends InputException{

	private static final long serialVersionUID = 1L;
	
	public DateException (String message){
		super(message);
	}
	
	public DateException (Throwable cause) {
		super(cause);
	}

	public DateException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}

}
