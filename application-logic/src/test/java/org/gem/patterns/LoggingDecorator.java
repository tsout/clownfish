package org.gem.patterns;

import org.jboss.logging.Logger;

public class LoggingDecorator extends Decorator {
	
	Logger logger = Logger.getLogger("TEST");
	private void log(String msg){
		logger.info(msg);
	};
	public LoggingDecorator( PhoneDialer clazz){
		super(clazz);
	}
	
	@Override
	public boolean dial(String ph) {
		boolean result =super.dial(ph);
		log("decorated");
		return result; 
	}
}
