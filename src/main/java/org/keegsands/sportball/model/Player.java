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
@Table(name="tap_softball.player")
public class Player {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
	
	public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
    
    @Column(name = "first_name")
    private String firstName;
    
    public String getFirstName(){
    	return this.firstName;
    }
    
    public void setFirstName(final String firstName){
    	this.firstName = firstName;
    }
    
    @Column(name = "last_name")
    private String lastName;
    
    public String getLastName(){
    	return this.lastName;
    }
    
    public void setLastName(final String lastName){
    	this.lastName = lastName;
    }
    
    private String gender;
    
    public String getGender(){
    	return gender;
    }
    
    public void setGender(final String gender){
    	this.gender = gender;
    }
    
    
    private String status;
    
    public String getStatus(){
    	return status;
    }
    
    public void setStatus(final String status){
    	this.status = status;
    }
    
    private String email;
    
    public String getEmail(){
    	return email;
    }
    
    public void setEmail(final String email){
    	this.email = email;
    }
    
    public String getShortStatus() {
		final PlayerStatus theStatus = PlayerStatus.get(getStatus());
		if (null == theStatus) {
			return getStatus();
		} else {
			return theStatus.getShortValue();
		}

	}
    
    @ManyToOne
	@JoinColumn(name = "team_id")
    private Team team;
    
    public Team getTeam(){
    	return team;
    }
    
    public void setTeam(final Team team){
    	this.team = team;
    }
    
    public String getFullName(){
    	final String returnValue;
    	if(getFirstName() == null && getLastName() == null){
    		returnValue = "";
    	}else if (getFirstName() == null){
    		returnValue = getLastName();
    	}else if(getLastName() == null){
    		returnValue = getFirstName();
    	}else{
    		returnValue = getFirstName() + " " + getLastName();
    	}
    	
    	return returnValue;
    }
    
    public String getShortName(){
    	final String returnValue;
    	if(getFirstName() == null && getLastName() == null){
    		returnValue = "";
    	}else if (getFirstName() == null){
    		returnValue = getLastName();
    	}else if(getLastName() == null || getLastName().length() == 0){
    		returnValue = getFirstName();
    	}else{
    		returnValue = getFirstName() + " " + getLastName().substring(0, 1);
    	}
    	
    	return returnValue;
    }
    
    @Override
    public String toString(){
        return "id="+ getId()+
        		", firstname="+getFirstName() +
        		", lastname=" + getLastName() +
        		", gender=" + getGender() + 
        		", status=" + getStatus() +
        		", email=" + getEmail();
    }

}
