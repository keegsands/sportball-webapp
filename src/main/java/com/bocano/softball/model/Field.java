package com.bocano.softball.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tap_softball.field")
public class Field extends AbstractNamedEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * Get the id of the entity
	 * 
	 * @return int id of the entity
	 */
	public int getId() {
		return id;
	}

	/**
	 * Set the id on the entity
	 * 
	 * @param id
	 *            int
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns a String with the id and name
	 * 
	 * @return String
	 */
	@Override
	public String toString() {
		return "id=" + getId() + ", name=" + getName();
	}

}
