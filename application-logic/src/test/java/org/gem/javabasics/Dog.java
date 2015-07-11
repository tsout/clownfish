package org.gem.javabasics;

public class Dog extends Animal{
	
	private static final long serialVersionUID = -4964005238434335934L;
	private boolean tailIsWagging; 
	
	public Dog(){
		super();

		setNumArms(0);
		setNumLegs(4);
		setHasTail(true);
	}

	public boolean isTailIsWagging() {
		return tailIsWagging;
	}

	public void setTailIsWagging(boolean tailIsWagging) {
		this.tailIsWagging = tailIsWagging;
	}
	
	public String toString(){
		return "Woof! ";
	}
	
}
