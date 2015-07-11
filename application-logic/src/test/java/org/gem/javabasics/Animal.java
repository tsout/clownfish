package org.gem.javabasics;

import java.io.Serializable;

public class Animal implements Serializable{

	private static final long serialVersionUID = 5588496852411806233L;
	private int numArms=-1; 
	private int numLegs=-1;
	private boolean hasTail=false; 

	public Animal(){
		
	}

	public int getNumArms() {
		return numArms;
	}

	public void setNumArms(int numArms) {
		this.numArms = numArms;
	}

	public int getNumLegs() {
		return numLegs;
	}

	public void setNumLegs(int numLegs) {
		this.numLegs = numLegs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numArms;
		result = prime * result + numLegs;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (numArms != other.numArms)
			return false;
		if (numLegs != other.numLegs)
			return false;
		return true;
	}

	public boolean hasTail() {
		return hasTail;
	}

	public void setHasTail(boolean hasTail) {
		this.hasTail = hasTail;
	}
}

