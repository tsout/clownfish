package org.gem.patterns;

import org.jboss.logging.Logger;

public abstract class Decorator implements PhoneDialer {

	Logger logger = null; 
	private PhoneDialer decoratedClazz = null;
	
	public Decorator (PhoneDialer clazz){
		if(clazz!=null)
		decoratedClazz = clazz; 
	}
	
	public boolean dial(String phoneNumber){
		return decoratedClazz.dial(phoneNumber);
	}
	

}
