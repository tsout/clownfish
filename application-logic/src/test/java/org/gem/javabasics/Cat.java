package org.gem.javabasics;

public class Cat extends Animal{
	private static final long serialVersionUID = 3534226586910553551L;
	public boolean isPurring= false;
	
	public Cat (){
		super();
		setNumArms(0);
		setNumLegs(4);
		
		
	}

	public boolean isPurring() {
		return isPurring;
	}

	public void setPurring(boolean isPurring) {
		this.isPurring = isPurring;
	}
	

	public String toString(){
		return "Meow! ";
	}
	

}
