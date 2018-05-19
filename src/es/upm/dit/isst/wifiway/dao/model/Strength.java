package es.upm.dit.isst.wifiway.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



@Entity 
public class Strength implements Serializable{
	
	@Id
	private String id;
	private double stregth;
	
	@ManyToOne//PREGUNTAR
	private Point point;
	
	@ManyToOne//PREGUNTAR
	private Wifi wifi;

	public String getId() {
		return id;
	}

	public void setId() {
		this.id = wifi.getMAC() + point.getId();
	}

	public double getStregth() {
		return stregth;
	}

	public void setStregth(double stregth) {
		this.stregth = stregth;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Wifi getWifi() {
		return wifi;
	}

	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}

}
