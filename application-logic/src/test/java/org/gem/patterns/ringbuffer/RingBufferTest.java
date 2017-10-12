package org.gem.patterns.ringbuffer;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class RingBufferTest {

	@Test
	public void test() throws InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		
		RingBuffer<Date> drb = new RingBufferBuilder<Date>().bufferSize(10).setClassType(Date.class).setFormatter(sdf).build();
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		Thread.sleep(5);
		drb.writeNext(new Date());
		
	
		System.out.println(sdf.format(drb.getLast()));
		System.out.println("");
		System.out.println(drb);
		
	}

}
