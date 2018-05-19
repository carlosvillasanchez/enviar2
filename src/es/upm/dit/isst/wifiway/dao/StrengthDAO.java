package es.upm.dit.isst.wifiway.dao;

import java.util.List;

import es.upm.dit.isst.wifiway.dao.model.*;


public interface StrengthDAO {
	
	public void createStrength( Strength strength );
	
	public Strength readStrength( String id );
	
	public void updateStrength( Strength strength );
	
	public void deleteStrength( Strength point);
	
	/*
	 * PENASR
	 * 
	 * public Strength getStrength( String ssid, double longitud, double latitud );

	public Strength getStrength( String ssid, String punto );*/
}
