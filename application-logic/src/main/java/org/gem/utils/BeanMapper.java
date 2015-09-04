package org.gem.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

public class BeanMapper {

	/**
	 * Dozer recommends creating only a single instance of Dozer Mapper
	 * 
	 * @Question What happens if many concurrent transactions need to map
	 *           objects? Will they be blocked because there is only one mapper
	 *           instance?
	 */
	private final static Mapper mapper =null;

	private BeanMapper() {
		// noOp
	}

	public static Mapper getMapper() {
		return mapper;
	}

	public static Mapper getInstance() {
		if (mapper == null) {
			return new DozerBeanMapper();
		} else {
			return getMapper();
		}
 
	}

	public static <T> T map(Object inputObject, Class<T> outputClassType) {
		System.out.println("Converting: "+PojoTestUtils.printPojo(inputObject));
		T obj = DozerBeanMapperSingletonWrapper.getInstance().map(inputObject, outputClassType);
		System.out.println("To: "+PojoTestUtils.printPojo(obj));
		return obj;
	}

}
