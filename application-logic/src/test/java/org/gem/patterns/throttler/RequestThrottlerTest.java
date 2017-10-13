package org.gem.patterns.throttler;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequestThrottlerTest {

	private static final long MIN_REQUEST_INTERVAL = 10L;

	@Test
	public void test() throws InterruptedException {

	 RequestThrottler<String> rt = RequestThrottler.getInstance(MIN_REQUEST_INTERVAL); 
	 rt.dispatchRequest("Hi");
	 Thread.sleep(4L);
	 rt.dispatchRequest("Hi Again");
	 Thread.sleep(6L);
	 rt.dispatchRequest("Hi Once More");
	 Thread.sleep(6L);
	 rt.dispatchRequest("Hi Once More");
	 Thread.sleep(10L);
	 rt.dispatchRequest("Process Me");
	 Thread.sleep(6L);
	 rt.dispatchRequest("Queue Me");
	 rt.dispatchRequest("Hi");
	 Thread.sleep(4L);
	 rt.dispatchRequest("Hi Again");
	 Thread.sleep(6L);
	 rt.dispatchRequest("Hi Once More");
	 Thread.sleep(6L);
	 rt.dispatchRequest("Hi Once More");
	 Thread.sleep(10L);
	 rt.dispatchRequest("Process Me");
	 Thread.sleep(6L);
	 rt.dispatchRequest("Queue Me");
	 
	 rt.getStatus();
	}

}
