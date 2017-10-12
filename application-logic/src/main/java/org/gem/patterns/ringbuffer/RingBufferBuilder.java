package org.gem.patterns.ringbuffer;

import java.text.Format;

public class RingBufferBuilder <T> {

	private int bufferSize=100;
	private Format formatter = null; 
 	private Class <T> clazz = null ; 
 	
 	public RingBufferBuilder<T> setFormatter(Format formatter){
 		this.formatter=formatter; 
 		return this;
 	}
	public RingBufferBuilder<T> setClassType(Class<T> c){
		this.clazz = c; 
		return this;
	}
	public RingBufferBuilder<T> bufferSize(int size)
	{
		this.bufferSize=size; 
		return this; 
	}
	RingBuffer<T> build(){
		if(this.clazz==null){
			throw new InstantiationError("Class Type must be specified");
		}
		if(this.formatter==null){
			throw new InstantiationError("Formatter must be specified");
		}
			RingBuffer<T> rb =  new RingBuffer<T>(this.clazz,this.bufferSize);	
			rb.setFormatter(this.formatter);
			return rb; 

	};
}
