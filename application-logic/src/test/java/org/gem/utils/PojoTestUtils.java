package org.gem.utils;

import java.util.ArrayList;
import java.util.List;





import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PojoTestUtils {
	
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
