package org.gem.patterns.ringbuffer;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

public class RequestThrottler {
	public static final long DEFAULT_MIN_REQUEST_INTERVAL_IN_MILLIS = 10L;
	Queue<String> queue = new ArrayDeque<String>();
	List<String> processed = new ArrayList<String>();

	long minRequestInterval = DEFAULT_MIN_REQUEST_INTERVAL_IN_MILLIS; 
	RingBuffer<Date> rb;

	RequestThrottler (long maxRequestRate){		
		this.minRequestInterval= Math.abs(maxRequestRate);
		init();
	}
	RequestThrottler() {
		init();
	}
	private void init() {
		this.rb = new RingBufferBuilder<Date>().bufferSize(10).setClassType(Date.class)
				.setFormatter(new SimpleDateFormat("yyyy-DD-MM:HH:mm:ss.SSS")).build();
	}

	public void dispatchRequest(String request) {
		System.out.println(">dispatchRequest");
		Date now = new Date();
		Date lastEntry = rb.getLast();
		if (lastEntry == null) {
			processRequest(request);
			rb.writeNext(now);
		} else {
			if (getRequestInterval(now, lastEntry) < this.minRequestInterval) {
				queueRequest(request);
				rb.writeNext(now);
			} else {
				processRequest(request);
				rb.writeNext(now);

			}
		}

	}
	private long getRequestInterval(Date now, Date lastEntry) {
		long currentRequestInterval = now.getTime() - lastEntry.getTime();
		System.out.println("Request Interval: "+currentRequestInterval+"ms");
		return currentRequestInterval;
	}

	private void queueRequest(String request) {
		System.out.println("queued request [ " + request + " ]");
		queue.add(request);

	}

	private void processRequest(String request) {
		System.out.println("processed request [ " + request + " ]");
		processed.add(request);
	}

	public void getStatus() {
		System.out.println("Queue");
		System.out.println(queue);
		System.out.println("Processed");
		System.out.println(processed);
	}
}
