package logic.controller;

import logic.model.AnalisiGratis;
import logic.model.Coupon;
import logic.model.EventoFarmacia;
import logic.model.Premiazione;
import logic.model.PremioVincitaEvento;
import logic.model.Sconto50;

public class ThreadPremiazioneEvento implements Runnable {
	private String vincitore;
	private String emailCliente;
	private String premio;
	private EventoFarmacia evento;
	
	
	public ThreadPremiazioneEvento(String vincitore, String emailCliente, String premio, EventoFarmacia evento) {
		super();
		this.vincitore = vincitore;
		this.emailCliente = emailCliente;
		this.premio = premio;
		this.evento = evento;
	}


	@Override
	public void run() {
		Premiazione p;
		p = new PremioVincitaEvento(vincitore, emailCliente);
		evento.setVincitore(vincitore);
		if(premio.compareToIgnoreCase("coupon") == 0) {
				Coupon coupon =new Coupon(p);
				coupon.premia();
			}
			else if(premio.compareToIgnoreCase("analisi gratis") == 0) {
				AnalisiGratis analisi =new AnalisiGratis(p);
				analisi.premia();
			}
			else if(premio.compareToIgnoreCase("sconto 50%") == 0) {
				Sconto50 sconto =new Sconto50(p);
				sconto.premia();
			}
	}
}
