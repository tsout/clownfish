package org.gem.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.springframework.util.StringUtils;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PojoTestUtils {
	
	public static String printPojo(Object pojo){
		StringBuffer sb = new StringBuffer(); 
		String newLine = System.lineSeparator();
		sb.append(pojo.getClass().getName()+"{"+newLine);		
		sb.append(printPublicFields(pojo));
		sb.append(printPublicGetters(pojo));		
		sb.append("}").append(newLine);
		return sb.toString();
	}

	private static String printPublicGetters(Object pojo) {
		StringBuffer sb = new StringBuffer();
		
		for (Method m : pojo.getClass().getMethods())
		{
			Object fieldValue = null;
			try {
				String getterRegex = "get.*";
				boolean matchesGetterPattern = m.getName().matches(getterRegex);
				String getClassRegexPattern = "getClass.*";
				boolean matchesGetClassRegex = m.getName().matches(getClassRegexPattern);
//				System.out.println(m.getName()+"\tmatches ?:"+matches);
				if(matchesGetterPattern && !matchesGetClassRegex){
					fieldValue = m.invoke(pojo);
					String fieldName = m.getName().substring(3, m.getName().length());
					fieldName=firstCharToLowerCase(fieldName);
					
					String map = printFieldValueMap(fieldValue,fieldName);
					if(map!=null)
					sb.append(map);
				}
				 
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	private static String firstCharToLowerCase(String fieldName) {
		
		if (fieldName!=null && fieldName.length()>0){
			
			char firstChar = fieldName.charAt(0);
			String lowerFirstChar = Character.toString(firstChar).toLowerCase();
			fieldName= fieldName.replace(firstChar, lowerFirstChar.charAt(0));
		}
		return fieldName;
		
	}

	private static String printPublicFields(Object pojo) {
		StringBuffer sb = new StringBuffer(); 
		Field[] fields = pojo.getClass().getFields();
		for (Field field : fields){
			
		Object pojoFieldValue="";
		
		try {
			pojoFieldValue = field.get(field.getType().newInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String pojoFieldName= field.getName();
		sb.append(printFieldValueMap(pojoFieldValue, pojoFieldName));
		}
		
		
		return sb.toString();
	}

	private static String printFieldValueMap(Object pojoFieldValue, String pojoFieldName) {
		if(StringUtils.isEmpty(pojoFieldName) ){
			return null;
		}else{
			String value = pojoFieldValue==null?"null":pojoFieldValue.toString();
			String newLine =System.lineSeparator();
			return String.format("%s\t : %s,"+newLine, pojoFieldName, value);			
		}
	}

	public static <T> List<T> generatePojos(int numberToGenerate,Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		int x = 0;
		PodamFactory factory = new PodamFactoryImpl();
		T pojo = null;
		while (x++ < numberToGenerate) {
			pojo = factory.manufacturePojo(clazz,
					clazz.getTypeParameters());
			list.add(pojo);
	
		}
		return list;
	}

}
