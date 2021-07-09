package logic.ingegnerizzazione;

public interface  Observable {
		
	public abstract void attach(Observer o);
	public abstract void detach(Observer o);
	public abstract void notifica();
	
}
