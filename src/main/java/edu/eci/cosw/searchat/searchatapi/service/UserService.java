package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;

import javax.servlet.ServletException;
import java.util.List;

/**
 * Created by Desarrollo on 10/02/2018.
 */
public interface UserService {

    /**
     * 
     * @param user
     * @return
     * @throws ServletException 
     */
    boolean createUser(User user) throws ServletException;
    
    /**
     * 
     * @return 
     */
    List<User> getUsers() ;

    /**
     * 
     * @param username
     * @return 
     */
    User getUser( String username );

    /**
     * 
     * @param username
     * @param profile
     * @return
     * @throws ServletException 
     */
    boolean updateProfileInformation(String username, ProfileInformation profile)throws ServletException;

}
