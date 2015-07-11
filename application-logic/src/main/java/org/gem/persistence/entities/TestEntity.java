package org.gem.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.gem.persistence.DAO;


@Entity
public class TestEntity implements DAO<Integer> {

	public TestEntity()
	{
		
	}
	public TestEntity(String msg, Integer id) {
		this.hello=msg;  
		this.id=id; 
	}

	private static final long serialVersionUID = 707856755935019037L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String hello;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}

	@Override
	public String toString() {
		return "TestEntity [id=" + id + ", hello=" + hello + "]";
	}

	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

}
