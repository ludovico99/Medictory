package logic.model;

import javax.mail.Message;

public abstract class Decorator extends Premiazione {
	protected Premiazione prem;


	@Override
	public abstract Message premia();

	
}
