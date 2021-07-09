package logic.ingegnerizzazione;

public class RelationshipException extends InputException {
	
	private static final long serialVersionUID = 1L;
	
	public RelationshipException (String message){
		super(message);
	}
	
	public RelationshipException (Throwable cause) {
		super(cause);
	}

	public RelationshipException (String message, Throwable cause) {
		super(" +++ " + message + " +++ ", cause);
		}

}
