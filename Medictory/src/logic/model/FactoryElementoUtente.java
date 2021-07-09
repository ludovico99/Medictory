package logic.model;

public class FactoryElementoUtente implements AbstractFactory {
	
	@Override
	public FarmacoCliente creaFarmaco(String nome, String descrizione, String scadenza, int quantita) {
		return new FarmacoCliente(nome,descrizione,scadenza,quantita);
	}
	
	@Override
	public Cliente creaUtente(String user, String pwd, String em) {
		return new Cliente(user, pwd, em);
	}

}
