package logic.model;

public class FactoryElementoFarmacia implements AbstractFactory  {
	
	@Override
	public FarmacoFarmacia creaFarmaco(String nome, String descrizione, String scadenza, int quantita) {
		return new FarmacoFarmacia(nome, descrizione, scadenza, quantita);
	}
	
	@Override
	public Farmacia creaUtente(String user, String pwd, String em) {
		return new Farmacia(user, pwd, em);
	}
	
}
