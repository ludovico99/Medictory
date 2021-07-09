package logic.controller;
import logic.model.Farmacia;
import logic.model.FarmaciaDAO;
import logic.model.FarmacoCliente;
import logic.model.FarmacoFarmacia;
import logic.model.FarmacoFarmaciaDAO;
import logic.model.SessioneCliente;
import logic.model.SessioneFarmacia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.LoginBean;
import logic.model.Cliente;
import logic.model.ClienteDAO;

public class ControllerLogin{
	private List<FarmacoFarmacia> farmaciFarmacia = new ArrayList<>();
	
	
	private void deleteExpiredOnes(String username) {
		
		Date oggi = new Date();
		
		for(FarmacoFarmacia f: farmaciFarmacia) {
			try {
								
				SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
				Date scad = sdf.parse(f.getScadenza());
			
				
				if (scad.before(oggi)) {
						
						FarmacoFarmaciaDAO.deleteExpired(f.getNome(), username, f.getScadenza());
						farmaciFarmacia.remove(f);	
				}
							
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
	}
	
	
	
	public SessioneFarmacia loginFarmacia(LoginBean bean) throws InputException {
		SessioneFarmacia sf;
		String us=bean.getUsername();
		String pwd = bean.getPassword();
		if (us == null || pwd == null || us.equals("") || pwd.equals(""))
			throw new InputException("Parametri mancanti");
		Farmacia f;
		f = FarmaciaDAO.esisteFarmacia(us, pwd);
		
		
		if(f == null) throw new InputException("Dati scorretti");
		
		
		farmaciFarmacia = f.getFarmaci();
		if(farmaciFarmacia !=  null) {
			
			this.deleteExpiredOnes(us);
			f.setFarmaci(farmaciFarmacia);
		}
			
		
		sf = new SessioneFarmacia (f.getUsername(), f.getClienti() ,f.getFarmaci(),f.getEventi());
		sf.setNomeFarmacia(f.getNome());
		sf.setIndirizzo(f.getIndirizzo());
		sf.setEmail(f.getEmail());
		
		return sf;
	}
	
	public SessioneCliente loginCliente(LoginBean bean) throws InputException {
		int qta = 0;
		String us=bean.getUsername();
		String pwd = bean.getPassword();
		if (us == null || pwd == null || us.equals("") || pwd.equals("")) {
			throw new InputException("Parametri mancanti");
		}
		Cliente c;
		c = ClienteDAO.esisteCliente(us, pwd);
			
		
		if(c == null) throw new InputException("Dati scorretti");
		
		SessioneCliente s = new SessioneCliente(c.getUsername(),c.getFarmaci(),c.getEventi());
		
		s.setPunteggio(c.getPunti());
		s.setLivello(c.getLivello());
		s.setFarmaciaAssociata(c.getFarmaAssociata());
		s.setEmail(c.getEmail());
		check(s);
		
		if(c.getFarmaci() != null) {
			for(FarmacoCliente f: c.getFarmaci()) {
				if(f.getStato().compareTo("verificato") == 0) {
					qta += f.getQuantita();
				}
			}
		}
		
		s.setQtaVerificate(qta);
		
		return s;
	}

	public void check(SessioneCliente s) {
		
		Date oggi = new Date();
		
		
		ArrayList<FarmacoCliente> farmaci;	
		if(s.getFarmaci() != null) {
			farmaci = (ArrayList<FarmacoCliente>) s.getFarmaci();
		
			for(FarmacoCliente f: farmaci) {
		
				try {
				
					SimpleDateFormat sdf = new  SimpleDateFormat("yyyy-MM-dd");
					Date scad = sdf.parse(f.getScadenza());
				
					if (scad.before(oggi) && f.getStato().compareTo("utilizzabile") == 0) {
							f.setStato("scaduto");
							f.setChanged(true);
					}
								
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
