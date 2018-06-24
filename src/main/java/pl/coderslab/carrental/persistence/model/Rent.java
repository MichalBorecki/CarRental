package pl.coderslab.carrental.persistence.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "rents")
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat
	private Date start;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat
	private Date end;

	private double distance;

	@ManyToOne
	private User user;

	@ManyToOne
	private Car car;

	@NotNull
	private double latStart;

	@NotNull
	private double lngStart;

	private double latEnd;

	private double lngEnd;

	public String getStart() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		String dateString = sdf.format(start);
		return dateString;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getEnd() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY HH:mm");
		if (end.equals(null)) {
			return "0";
		}
		String dateString = sdf.format(end);
		return dateString;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public long getRentTime() {
		if (this.end.equals(null)) {
			return 0;
		}
		long rentTime = getDateDiff(this.start,this.end,TimeUnit.MINUTES);
		return rentTime;
	}
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public double getLatStart() {
		return latStart;
	}

	public void setLatStart(double latStart) {
		this.latStart = latStart;
	}

	public double getLngStart() {
		return lngStart;
	}

	public void setLngStart(double lngStart) {
		this.lngStart = lngStart;
	}

	public double getLatEnd() {
		// !! we accept the initial position for simplicity
		return latEnd = this.latStart;
	}

	public void setLatEnd(double latEnd) {
		this.latEnd = latEnd;
	}

	public double getLngEnd() {
		// !!  we accept the initial position for simplicity
		return lngEnd = this.lngStart;
	}

	public void setLngEnd(double lngEnd) {
		this.lngEnd = lngEnd;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format(
			"Rent [id=%s, start=%s, end=%s, distance=%s, user=%s, car=%s, latStart=%s, lngStart=%s, latEnd=%s, lngEnd=%s]",
			id, start, end, distance, user, car, latStart, lngStart, latEnd, lngEnd);
	}

}
