package org.gem.javabasics;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JavaBasicsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClassCasting() {
		
		Animal a = getAnAnimal(); 
		System.out.println(a);
		
		Dog d = new Dog(); 
		Animal a2 = new Animal(); 
		a2 = d; 
		System.out.println(a2);
//		Cat c = (Cat) a2;
//		System.out.println(c);
	}

	public Animal getAnAnimal() {
		Random r = new Random();
		Integer randomInt = r.nextInt();

		if ((randomInt % 100) < 50) {
			return new Dog();
		} else {
			return new Cat();
		}
	}

}
