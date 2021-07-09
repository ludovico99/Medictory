package logic.model;

public interface AbstractFactory {
	public Farmaco creaFarmaco(String nome, String descrizione, String scadenza, int quantita);
	public Utente creaUtente(String user, String pwd, String em);
}
