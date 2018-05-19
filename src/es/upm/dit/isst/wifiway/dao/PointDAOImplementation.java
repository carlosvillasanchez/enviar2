package es.upm.dit.isst.wifiway.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.wifiway.dao.model.Point;

public class PointDAOImplementation implements PointDAO{
	
	private static PointDAOImplementation instance;
	private PointDAOImplementation() {
		
	}
	public static PointDAOImplementation getInstance() {
		if (null == instance) instance = new PointDAOImplementation();
		return instance;
	}
	@Override
	public void createPoint(Point point) {
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	session.save(point);
	    	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}

		
	}

	@Override
	public Point readPoint(String id) {
		Point point = null;
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	point = session.get(Point.class, id);
	    	session.getTransaction().commit();
		} catch (Exception e) {
        	// manejar excepciones
		} finally {
        	session.close();
		}
		
		return point;
		
	}

	@Override
	public void updatePoint(Point point) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.saveOrUpdate(point);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

	@Override
	public void deletePoint(Point point) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.delete(point);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

	@Override
	public Point get(double latitud, double longitud) {
		Point point = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	
		            	point = (Point) session.createQuery("select p from Point p where p.longitud= :longitud and "
		            					+ "p.latitud= :latitud")
		            			.setParameter("longitud", longitud)
		            			.setParameter("latitud", latitud)
		            			.getSingleResult();
		            			
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
		return point;
	}

}
