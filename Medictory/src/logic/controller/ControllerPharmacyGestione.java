package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.ingegnerizzazione.PharmacyGestioneBean;
import logic.model.Cliente;
import logic.model.Coupon;
import logic.model.FarmacoCliente;
import logic.model.FarmacoClienteDAO;
import logic.model.Premiazione;
import logic.model.PremioPassaggioDiLivello;
import logic.model.SessioneFarmacia;

public class ControllerPharmacyGestione implements Runnable {
	
	private String clienteAttuale;
	private String emailCliente;
	
	
	public List<PharmacyGestioneBean> findResources(SessioneFarmacia s) {
		
		
		ArrayList<PharmacyGestioneBean> list = new ArrayList<>();
		List<Cliente> clienti = s.getClienti();
	
		for(Cliente c: clienti) {
			List<FarmacoCliente> farmaciClienteN = FarmacoClienteDAO.myFarmaciCliente(c.getUsername());
			
			if(farmaciClienteN != null) {
				for(FarmacoCliente fc: farmaciClienteN) {
					if(fc.getStato().compareTo("smaltito")  == 0) {
						PharmacyGestioneBean modelTable = new PharmacyGestioneBean(c.getUsername(), fc.getNome(), fc.getScadenza(), Integer.toString(fc.getQuantita()));
						list.add(modelTable);
					}
				}
			}
		}
		
		return list;
	}
	
	
	public void verifica(SessioneFarmacia s, String utente, String farmaco, String scadenza, String quantita) {
		
		int livello;
		int punti;
		int qta = Integer.parseInt(quantita);
		
	
		if(s != null) {
			FarmacoClienteDAO.cambiaStato(farmaco, utente, scadenza,  "verificato");
			
			
			if(s.getClienti() != null) {
				for(Cliente c: s.getClienti()) {
					if(c.getUsername().compareTo(utente) == 0 ) {
						
						livello = c.getLivello();
						punti = c.getPunti();
						
						punti += 10*qta;
						if(punti >= livello*100) {
							
							punti = punti-livello*100;		//azzero i punti
							livello += 1;					//aumento il livello
							c.setLivello(livello);
							c.setPunti(punti);

							this.clienteAttuale = utente;
							this.emailCliente = c.getEmail();
							new Thread(this).start();
							
						}
						
						c.setPunti(punti);
						c.notifica();
					}
				}
				
			}
		}
	}


	@Override
	public void run() {
		Premiazione p;
		p = new PremioPassaggioDiLivello(clienteAttuale, emailCliente);
		new Coupon(p);
		
	}
	
	
}
