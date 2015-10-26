package org.gem.patterns;

public class Phone implements PhoneDialer {

	String lastNumberDialed = null; 
	public boolean dial (String phoneNumber){
		
		if(phoneNumber!=null){
			lastNumberDialed=phoneNumber;
			System.out.println("dialing..."+lastNumberDialed);
			return true;
		} 
		return false;
	}
	public void dial() {
		dial("7573333333");
		
	}
}
