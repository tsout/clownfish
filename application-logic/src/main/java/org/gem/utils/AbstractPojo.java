package org.gem.utils;


public class AbstractPojo {
	
	public String printPojo(Object pojo){
		if(pojo!=null){			
			return PojoTestUtils.printPojo(pojo);
		}else{
			return null;
		}
		
	}
	public String toString(){
		return  printPojo(this);
	}
}
