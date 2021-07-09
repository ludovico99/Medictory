package logic.controller;
import logic.model.FarmaciaDAO;
import logic.model.Farmacia;
import logic.model.Cliente;
import logic.model.ClienteDAO;

public class ControllerRegistrazione {
	
	public boolean registraFarma(String username, String pwd, String nome, String email, String indirizzo) {
		if (username == null || pwd == null || nome == null || email == null || indirizzo == null || 
			username.equals("") || pwd.equals("")  || nome.equals("")  || email.equals("")  || indirizzo.equals("")) 
			return false;
		
		Farmacia f;
		f = FarmaciaDAO.creaUtenteFarmacia(username, pwd, nome, email, indirizzo);
		
		return (f != null);
	}
	
	public boolean registraCliente(String username, String pwd, String email, String farma) {
		if (username == null || pwd == null || email == null || farma == null || 
			username.equals("") || pwd.equals("")  || email.equals("") || farma.equals("")) 
			return false;
		
		Cliente c;
		c = ClienteDAO.creaUtenteCliente(username, pwd, email, farma);
		
		return (c != null);
	}
}
