package logic.ingegnerizzazione;

public class NoInternetException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public NoInternetException (){
		super("Impossibile connettersi a internet");
	}
	
	public NoInternetException (Throwable cause) {
		super(cause);
	}

	public NoInternetException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
	}

}