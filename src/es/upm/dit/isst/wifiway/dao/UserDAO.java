package es.upm.dit.isst.wifiway.dao;
import java.util.List;

import es.upm.dit.isst.wifiway.dao.model.*;


public interface UserDAO {
	
	public void createUser( User user );
	
	public User readUser( String email );
	
	public void updateUser( User user );
	
	public void deleteUser( User user);
	

	
	public User loginUser( String email, String password );
	
	
	


}
