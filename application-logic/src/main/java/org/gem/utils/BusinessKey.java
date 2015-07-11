package org.gem.utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

public final class BusinessKey {
	static Logger logger = Logger.getLogger(BusinessKey.class.getSimpleName());
	private static final String BUSINESS_KEY_REGEX = "[a-zA-Z0-9\\-]{1,50}";
	private  LinkedHashMap<String, String> businessKeyMap = null;

	public BusinessKey() {
		businessKeyMap = new LinkedHashMap<String, String>();
	}

	public BusinessKey(LinkedHashMap<String, String> keyMap)
			throws InstantiationException {
		if (!isValidBusinessKey(keyMap))
			throw new InstantiationException();
		businessKeyMap = keyMap;
	}

	private static boolean isValidBusinessKey(
			LinkedHashMap<String, String> keyMap) {
		if (keyMap == null) {
			return false;
		}
		for (Entry<String, String> e : keyMap.entrySet()) {
			logger.info("Testing Validity " + e.toString());
			boolean isNotNull = e.getValue() != null;
			boolean isNotEmpty = e.getValue() != "";
			boolean matchesRegex = e.getValue().matches(BUSINESS_KEY_REGEX);

			if (isNotNull && isNotEmpty && matchesRegex) {
				continue;
			} else {
				return false;
			}

		}
		return true;
	}

	// public BusinessKey(List<String> keyValue) throws InstantiationException {
	// businessKeyMap = new LinkedHashMap<String, String>();
	//
	// if (keyValue != null && !keyValue.contains(null))
	// key = keyValue;
	// else {
	// throw new InstantiationException("Key Value can not be null");
	// }
	// }

	public LinkedHashMap<String, String> getBusinessKeyMap() {
		return new LinkedHashMap<String,String> (businessKeyMap);
	}
	
//	public void addBusinessKey(String key, String value){
//		if(!businessKeyMap.containsKey(key))
//		businessKeyMap.put(key, value);
//		logger.info("Key ["+key+"] already exists and cannot be overwritten");
//	}
	public String getBusinessKeyValue(String key){
		return businessKeyMap.get(key);
	}

	public String getBusinessKeyAsString() {
		return toString();
	}

	public String toString() {
		String tmp = "";
		for (Entry<String, String> e : businessKeyMap.entrySet()) {
			tmp = "|" + e.getValue();
		}
		return tmp;
	}

	// public List<String> getKey() {
	// return key;
	// }
	//
	// public String getKeyString() {
	// String str = "";
	// for (String k : key) {
	// str += k;
	// }
	// return str;
	// }

	public boolean isValid() {
		return isValidBusinessKey(this.businessKeyMap);
	}


}
