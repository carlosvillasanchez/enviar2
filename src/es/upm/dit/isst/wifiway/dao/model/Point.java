package es.upm.dit.isst.wifiway.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity 
public class Point implements Serializable{
	
	@Id
	private String id;
	private double longitud;
	private double latitud;
	
	@OneToMany(mappedBy="point", fetch = FetchType.EAGER)
	private List<Strength> wifis;
	/*@ManyToMany(fetch = FetchType.EAGER, mappedBy="points", cascade = {CascadeType.MERGE, CascadeType.PERSIST} )//PREGUNTAR
	private List<Wifi> wifis;*/
	
	public Point() {
		this.wifis = new ArrayList<Strength>();
	}
	
	public String getId() {
		return id;
	}

	public void setId() {
		this.id = "" + longitud + latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public List<Strength> getWifis() {
		return wifis;
	}

	public void setWifis(List<Strength> wifis) {
		this.wifis = wifis;
	}
	
	
	/*public Strength getStrength() {
		return strength;
	}

	public void setStrength(Strength strength) {
		this.strength = strength;
	}
	*/
}
