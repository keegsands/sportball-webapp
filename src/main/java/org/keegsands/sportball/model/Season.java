package org.keegsands.sportball.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tap_softball.season")
public class Season extends AbstractNamedEntity{
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
    
    @Column(name="standings_supported")
    private boolean standingsSupport;
    public boolean getStandingsSupport(){
    	return standingsSupport;
    }
    
    public void setStandingsSupport(final boolean standingsSupport){
    	this.standingsSupport = standingsSupport;
    }
    
    @Override
    public String toString(){
        return "id="+ getId()+", name="+getName();
    }
}
