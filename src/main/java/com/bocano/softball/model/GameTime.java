package com.bocano.softball.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "tap_softball.game_time")
public class GameTime {
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("h:mm a");

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Column
	@Type(type = "time")
	private Date timeSlot;

	public Date getTimeSlot() {
		return timeSlot;
	}

	public String getFormattedTime() {
		if (null != getTimeSlot()) {
			return DATE_FORMAT.format(getTimeSlot());
		}
		return "";
	}

	public void setTimeSlot(final Date timeSlot) {
		this.timeSlot = timeSlot;
	}

	@Override
	public String toString() {
		return "id=" + getId() + ", timeslot=" + getTimeSlot();
	}
}