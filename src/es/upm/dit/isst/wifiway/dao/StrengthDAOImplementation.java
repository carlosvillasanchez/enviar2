package es.upm.dit.isst.wifiway.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.wifiway.dao.model.Strength;

public class StrengthDAOImplementation implements StrengthDAO{
	
	private static StrengthDAOImplementation instance;
	private StrengthDAOImplementation() {
		
	}
	public static StrengthDAOImplementation getInstance() {
		if (null == instance) instance = new StrengthDAOImplementation();
		return instance;
	}
	@Override
	public void createStrength(Strength strength) {
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	session.save(strength);
	    	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}

		
	}

	@Override
	public Strength readStrength(String id) {
		Strength strength = null;
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	strength = session.get(Strength.class, id);
	    	session.getTransaction().commit();
		} catch (Exception e) {
        	// manejar excepciones
		} finally {
        	session.close();
		}
		
		return strength;
		
	}

	@Override
	public void updateStrength(Strength strength) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.saveOrUpdate(strength);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

	@Override
	public void deleteStrength(Strength strength) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.delete(strength);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

/*	@Override
	public Strength getStrength(String email, String password) {
		Strength strength = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	
		            	strength = (Strength) session.createQuery("select s from Strength s where s.email= :email and "
		            					+ "s.password= :password")
		            			.setParameter("email", email)
		            			.setParameter("password", password)
		            			.getSingleResult();
		            			
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
		return strength;
	}
*/
}
