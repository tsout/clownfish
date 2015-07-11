package org.gem.persistence;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.gem.utils.BusinessKey;
import org.junit.Before;
import org.junit.Test;

public class BusinessKeyTest {

	private static LinkedHashMap<String, String> map =null;
	@Before
	public void setup(){
		initializeData();
	}
	@Test
	public void testBusinessKey() throws InstantiationException{
		LinkedHashMap<String, String> map = initializeData();
		assertNotNull(new BusinessKey(map).getBusinessKeyMap());
	}
	private LinkedHashMap<String, String> initializeData() {
		String stringKey ="stuff";
		map = new LinkedHashMap<String,String>();
		map.put("stuff", stringKey);
		return map;
	}
	@Test
	public void testImmutableBusinessKey() throws InstantiationException{
		BusinessKey bk  = new  BusinessKey(map);
		assertEquals("Expecting"+map,map,bk.getBusinessKeyMap());
		LinkedHashMap<String,String> other = new LinkedHashMap<String,String> (map);
		String stringKey ="otherStuff";
		other.put("stuff", stringKey);
		bk.getBusinessKeyMap().putAll(other);
		assertFalse("Expecting no change: "+stringKey, stringKey.equals(bk.getBusinessKeyMap().get("stuff")));
//		bk.addBusinessKey("stuff", stringKey);
		assertFalse("Expecting no change: "+stringKey, stringKey.equals(bk.getBusinessKeyMap().get("stuff")));
	}
	
//	@Test (expected=NoSuchFieldException.class)
//	public void testPrivateFieldInaccessible() throws NoSuchFieldException,InstantiationException, IllegalArgumentException, IllegalAccessException {
//		String stringKey ="stuff";
//		BusinessKey bk  = new  BusinessKey(Arrays.asList(stringKey));
//		Field k =BusinessKey.class.getField("key");
//		String str = new String(); 
//		k.set(str,"test");
//		
//	}
}
