package es.upm.dit.isst.wifiway.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity 
public class User implements Serializable{
	
	@Id
	private String email;
	private String name;
	private String password;
	
	private String puntos[];
	
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE } )//PREGUNTAR
	private List<Punto> puntos;*/
	
	public User() {
		this.puntos = new String[5];
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getPuntos() {
		return puntos;
	}

	public void setPuntos(String[] puntos) {
		this.puntos = puntos;
	}
	public void guardar(String punto) {
		for (int i = 0; i<this.puntos.length; i++) {
			if (this.puntos[i] == null) {
				this.puntos[i] = punto;
				return;
			}
		}
		for (int i = 0; i<this.puntos.length - 1; i++) {
			this.puntos[i] = this.puntos[i+1];
		}
		
		this.puntos[puntos.length] = punto;
		
	}

}
