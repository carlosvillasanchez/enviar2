package es.upm.dit.isst.wifiway.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.wifiway.dao.model.Wifi;

public class WifiDAOImplementation implements WifiDAO{
	
	private static WifiDAOImplementation instance;
	private WifiDAOImplementation() {
		
	}
	public static WifiDAOImplementation getInstance() {
		if (null == instance) instance = new WifiDAOImplementation();
		return instance;
	}
	@Override
	public void createWifi(Wifi wifi) {
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	session.save(wifi);
	    	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}

		
	}

	@Override
	public Wifi readWifi(String ssid) {
		Wifi wifi = null;
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	wifi = session.get(Wifi.class, ssid);
	    	session.getTransaction().commit();
		} catch (Exception e) {
        	// manejar excepciones
		} finally {
        	session.close();
		}
		
		return wifi;
		
	}

	@Override
	public void updateWifi(Wifi wifi) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.saveOrUpdate(wifi);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

	@Override
	public void deleteWifi(Wifi wifi) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.delete(wifi);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

}