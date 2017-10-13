package org.gem.patterns.throttler;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;

import org.gem.patterns.ringbuffer.RingBuffer;

/**
 * Model for throttling requests that
 * 
 * This class should be instantiated as a container scoped singleton. A single
 * container will throttle all requests through this mechanism handles all
 * requests will use
 * 
 * T = generic request type (e.g. string, HTTPRequest,object, etc)
 * 
 * @author tim
 *
 */
public class RequestThrottler <T> {
	public static final long DEFAULT_MIN_REQUEST_INTERVAL_IN_MILLIS = 10L;
	Queue<T> queue = new ArrayDeque<T>();
	List<T> processed = new ArrayList<T>();
	private Date lastRequestDateTime;

	private static long minRequestInterval = DEFAULT_MIN_REQUEST_INTERVAL_IN_MILLIS;
	RingBuffer<Date> rb;

	private static RequestThrottler<?>  INSTANCE = null;

	//
	@SuppressWarnings("unchecked")
	public static <T> RequestThrottler<T> getInstance(long maxRequestRate) {
		if (INSTANCE == null) {
			INSTANCE = new RequestThrottler<T>(maxRequestRate);
		}
		return (RequestThrottler<T>) INSTANCE;
	}

	public static <T> RequestThrottler <T> getInstance() {
		return getInstance(DEFAULT_MIN_REQUEST_INTERVAL_IN_MILLIS);
	}


	private RequestThrottler(long maxRequestRate) {
		this.minRequestInterval = Math.abs(maxRequestRate);
		init();
	}

	private void init() {
		this.setLastRequestDateTime(null);
	}

	/**
	 * Manages Request SLA. If request exceeds throttle threshold, then it is
	 * queued for later processing. otherwise its processed immediately
	 * 
	 * @param request
	 */
	public void dispatchRequest(T request) {
		System.out.println(">dispatchRequest");
		Date now = new Date();
		Date lastEntry = getLastRequestDateTime();

		if (lastEntry == null) {
			processRequest(request);
		} else {
			if (getRequestInterval(now, lastEntry) < minRequestInterval) {
				queueRequest(request);
			} else {
				processRequest(request);
			}
		}
		this.setLastRequestDateTime(now);

	}

	private long getRequestInterval(Date now, Date lastEntry) {
		long currentRequestInterval = now.getTime() - lastEntry.getTime();
		System.out.println("Request Interval: " + currentRequestInterval + "ms");
		return currentRequestInterval;
	}

	private void queueRequest(T request) {
		System.out.println("queued request [ " + request + " ]");
		queue.add(request);

	}

	private  void processRequest(T request) {
		System.out.println("processed request [ " + request + " ]");
		processed.add(request);
	}

	public void getStatus() {
		System.out.println("Queue");
		System.out.println(queue);
		System.out.println("Processed");
		System.out.println(processed);
	}

	public Date getLastRequestDateTime() {
		return lastRequestDateTime;
	}

	public void setLastRequestDateTime(Date lastRequestDateTime) {
		this.lastRequestDateTime = lastRequestDateTime;
	}
}
