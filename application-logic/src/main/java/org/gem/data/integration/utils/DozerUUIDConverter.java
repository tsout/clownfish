package org.gem.data.integration.utils;

import java.util.UUID;

import org.dozer.DozerConverter;

public class DozerUUIDConverter extends DozerConverter<UUID, String> {

	public DozerUUIDConverter(){
		super(UUID.class,String.class);
	}
	public DozerUUIDConverter(Class<UUID> prototypeA, Class<String> prototypeB) {
		super(prototypeA, prototypeB);
		// TODO Auto-generated constructor stub
	}
	@Override
	public UUID convertFrom(String arg0, UUID arg1) {
		if(arg0==null) return null ;
		UUID uuid = null;
		try{
			uuid = UUID.fromString(arg0);
		}catch(IllegalArgumentException iae){
			System.err.println(iae.getMessage());
		}
		return uuid;
	}
	@Override
	public String convertTo(UUID arg0, String arg1) {
		if(arg0 == null)return null; 
		return arg0.toString();
	}


}
