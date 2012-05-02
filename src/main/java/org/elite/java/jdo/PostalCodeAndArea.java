package org.elite.java.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Created with IntelliJ IDEA.
 * User: Torben Vesterager
 */
@PersistenceCapable
public class PostalCodeAndArea {

	@PrimaryKey
	private short postalCode;

	@Persistent
	private String postalArea;

	public PostalCodeAndArea(short postalCode, String postalArea) {
		this.postalCode = postalCode;
		this.postalArea = postalArea;
	}

	public short getPostalCode() {
		return postalCode;
	}

	public String getPostalArea() {
		return postalArea;
	}

	@Override
	public String toString() {
		return postalCode + " " + postalArea;
	}
}
