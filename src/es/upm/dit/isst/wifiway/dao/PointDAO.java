package es.upm.dit.isst.wifiway.dao;

import java.util.List;

import es.upm.dit.isst.wifiway.dao.model.*;


public interface PointDAO {
	
	public void createPoint( Point point );
	
	public Point readPoint( String id );
	
	public void updatePoint( Point point );
	
	public void deletePoint( Point point);
	
	public Point get( double longitud, double latitud );


}
