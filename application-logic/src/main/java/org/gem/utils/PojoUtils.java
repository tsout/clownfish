package org.gem.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

public class PojoUtils {

	public static String printPojo(Object pojo) {
		StringBuffer sb = new StringBuffer();
		String newLine = System.lineSeparator();
		sb.append(pojo.getClass().getName() + "{" + newLine);
		sb.append(PojoUtils.printPublicFields(pojo));
		sb.append(PojoUtils.printPublicGetters(pojo));
		sb.append("}").append(newLine);
		return sb.toString();
	}

	public static String printPublicGetters(Object pojo) {
		StringBuffer sb = new StringBuffer();

		for (Method m : pojo.getClass().getMethods()) {
			Object fieldValue = null;
			try {
				String getterRegex = "get.*";
				boolean matchesGetterPattern = m.getName().matches(getterRegex);
				String getClassRegexPattern = "getClass.*";

				boolean matchesGetClassRegex = m.getName().matches(
						getClassRegexPattern);
				String getInstanceRegexPattern = "getInstance.*";
				boolean matchesGetInstanceRegex = m.getName().matches(
						getInstanceRegexPattern);

				if (matchesGetterPattern && !matchesGetInstanceRegex
						&& !matchesGetClassRegex) {
					fieldValue = m.invoke(pojo);
					String fieldName = m.getName().substring(3,
							m.getName().length());
					fieldName = PojoUtils.firstCharToLowerCase(fieldName);

					String map = PojoUtils.printFieldValueMap(fieldValue,
							fieldName);
					if (map != null)
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

	public static String printPublicFields(Object pojo) {
		StringBuffer sb = new StringBuffer();
		Field[] fields = pojo.getClass().getFields();
		for (Field field : fields) {

			Object pojoFieldValue = "";

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

			String pojoFieldName = field.getName();
			sb.append(PojoUtils.printFieldValueMap(pojoFieldValue,
					pojoFieldName));
		}

		return sb.toString();
	}

	public static String firstCharToLowerCase(String fieldName) {

		if (fieldName != null && fieldName.length() > 0) {

			char firstChar = fieldName.charAt(0);
			String lowerFirstChar = Character.toString(firstChar).toLowerCase();
			fieldName = fieldName.replace(firstChar, lowerFirstChar.charAt(0));
		}
		return fieldName;

	}

	private static String printFieldValueMap(Object pojoFieldValue,
			String pojoFieldName) {
		if (StringUtils.isEmpty(pojoFieldName)) {
			return null;
		} else {
			String value = pojoFieldValue == null ? "null" : pojoFieldValue
					.toString();
			String newLine = System.lineSeparator();
			return String.format("%s\t : %s," + newLine, pojoFieldName, value);
		}
	}

}
