package org.elite.java.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * Created with IntelliJ IDEA.
 * User: Torben Vesterager
 */
@PersistenceCapable
public class Person {

	@Persistent
	private String name;

	@Persistent
	private short height;

	@Persistent
	private short weight;

	public Person(String name, short height, short weight) {
		this.name = name;
		this.height = height;
		this.weight = weight;
	}

	@SuppressWarnings("unused")
	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	public short getHeight() {
		return height;
	}

	@SuppressWarnings("unused")
	public short getWeight() {
		return weight;
	}
}
