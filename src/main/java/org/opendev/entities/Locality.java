package org.opendev.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Locality implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLocality;

	private double altitude;
	private double longitude;

	public Locality() {
		// TODO Auto-generated constructor stub
	}

	public Locality(float altitude, float longitude) {
		super();
		this.altitude = altitude;
		this.longitude = longitude;
	}

	public Long getIdLocality() {
		return idLocality;
	}

	public void setIdLocality(Long idLocality) {
		this.idLocality = idLocality;
	}

	public double  getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Locality [idLocality=" + idLocality + ", altitude=" + altitude + ", longitude=" + longitude + "]";
	}

}
