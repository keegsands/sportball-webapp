package org.keegsands.sportball.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tap_softball.division")
public class Division extends AbstractNamedEntity  implements Comparable<Division>{
	
	

	@ManyToOne
	@JoinColumn(name = "conference_id")
	private Conference conference;
	
	
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

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conference == null) ? 0 : conference.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Division other = (Division) obj;
		if (conference == null) {
			if (other.conference != null)
				return false;
		} else if (!conference.equals(other.conference))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public int compareTo(final Division d2) {
		final Division d1 = this;
		if(null == d2){
			return 1;
		}
		return d1.getConfDivName().compareTo(d2.getConfDivName());
		
	}
	
	public String getConfDivName(){
		if(null != getConference() && null != getConference().getName()){
			return getConference().getName() + " " + getName();
		}else{
			return getName();
		}
	}

}
