package org.gem.patterns;

import org.junit.Test;

public class DecoratorTest {

	private static final String PHON_NUMBERE = "555-555-5555";

	@Test
	public void test() {

		LoggingDecorator loggable = new LoggingDecorator(new Phone());

		loggable.dial(PHON_NUMBERE);
		
		PhoneDecorator phoneDecorator = new PhoneDecorator(new Phone ());
		
		phoneDecorator.dial(PHON_NUMBERE);

	}

}
