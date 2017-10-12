package org.gem.patterns.ringbuffer;

import java.lang.reflect.Array;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Circular Buffer that retains fixed amount of objects.
 * 
 * @author tim
 *
 * @param <T>
 */
public class RingBuffer<T> {

	private Format formatter = null;
	private int bufferLimit;
	private int last = 0;
	private T buffer[] = null;

	private RingBuffer() {
	}

	@SuppressWarnings("unchecked")
	public RingBuffer(Class<T> c, int bufferSize) {
		this.last = 0;
		this.bufferLimit = bufferSize;
		this.buffer = (T[]) Array.newInstance(c, this.bufferLimit);
	}

	public void writeNext(T data) {
		int next = last+1;
		if (next == buffer.length) {
			next = 0; // go back to the beginning
		}
		System.out.println("writing [ " + format(data) + " ] to position " + next);
		buffer[next] = data;
		last = next; 
	}

	private String format(T data) {
		return getFormatter().format(data);
	};

	public void setFormatter(Format formatter) {
		this.formatter = formatter;
	}

	/**
	 * Sets the string formatter for the object type that will be stored. Must
	 * be set prior to writing to buffer.
	 * 
	 * @return
	 */
	private Format getFormatter() {
		// TODO Auto-generated method stub
		return this.formatter;
	}

	public T getLast() {
		return buffer[last];
	};

	public String toString() {
		List<T> l = Arrays.asList(this.buffer);

		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		for (T item : l) {
			String formattedItem = getFormatter().format(item);
			sb.append(formattedItem + ","+System.lineSeparator());
		}
		sb.append(" ]");
		return sb.toString();
	}

}
