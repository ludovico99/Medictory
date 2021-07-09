package logic.ingegnerizzazione;

public class RequirementException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public RequirementException (String message){
		super(message);
	}
	
	public RequirementException (Throwable cause) {
		super(cause);
	}

	public RequirementException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
		}

}
