package org.keegsands.sportball.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNamedEntity {
	private String name;

	/**
	 * Get the name of the entity
	 * 
	 * @return String name of the entity
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the entity
	 * 
	 * @param name
	 *            String
	 */
	public void setName(String name) {
		this.name = name;
	}
}
