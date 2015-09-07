package org.gem.utils;


public class AbstractPojo {
	
	public String printPojo(Object pojo){
		if(pojo!=null){			
			return PojoUtils.printPojo(pojo);
		}else{
			return null;
		}
		
	}
	public String toString(){
		return  printPojo(this);
	}
}
