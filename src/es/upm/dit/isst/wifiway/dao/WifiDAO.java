package es.upm.dit.isst.wifiway.dao;

import java.util.List;

import es.upm.dit.isst.wifiway.dao.model.*;


public interface WifiDAO {
	
	public void createWifi( Wifi wifi );
	
	public Wifi readWifi( String ssid );
	
	public void updateWifi( Wifi wifi );
	
	public void deleteWifi( Wifi wifi);
	
	//public Wifi get( double latitud, double longitud );


}
