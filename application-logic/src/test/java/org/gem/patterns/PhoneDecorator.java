package org.gem.patterns;

public class PhoneDecorator extends Decorator {

	public PhoneDecorator(PhoneDialer clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	public boolean dial(String phoneNumber) {
		System.out.println("blah blah");
		boolean result =super.dial(phoneNumber.replaceFirst("^[0-9]{3}", "800"));
		System.out.println("blah blah2s");
		return result;
	}
} 
