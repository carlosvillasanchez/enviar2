package es.upm.dit.isst.wifiway.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.upm.dit.isst.wifiway.dao.model.User;

public class UserDAOImplementation implements UserDAO{
	
	private static UserDAOImplementation instance;
	private UserDAOImplementation() {
		
	}
	public static UserDAOImplementation getInstance() {
		if (null == instance) instance = new UserDAOImplementation();
		return instance;
	}
	@Override
	public void createUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	session.save(user);
	    	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		     session.close();
		}

		
	}

	@Override
	public User readUser(String email) {
		User user = null;
		Session session = SessionFactoryService.get().openSession();
		try {
	    	session.beginTransaction();
	    	// operaciones
	    	user = session.get(User.class, email);
	    	session.getTransaction().commit();
		} catch (Exception e) {
        	// manejar excepciones
		} finally {
        	session.close();
		}
		
		return user;
		
	}

	@Override
	public void updateUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.saveOrUpdate(user);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

	@Override
	public void deleteUser(User user) {
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	session.delete(user);
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}

		
	}

	@Override
	public User loginUser(String email, String password) {
		User user = null;
		Session session = SessionFactoryService.get().openSession();
		try {
		            	session.beginTransaction();
		            	
		            	user = (User) session.createQuery("select u from User u where u.email= :email and "
		            					+ "u.password= :password")
		            			.setParameter("email", email)
		            			.setParameter("password", password)
		            			.getSingleResult();
		            			
		            	session.getTransaction().commit();
		} catch (Exception e) {
		            	// manejar excepciones
		} finally {
		            	session.close();
		}
		
		return user;
	}

}