package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.User;

import javax.servlet.ServletException;
import java.util.List;

/**
 * Created by Desarrollo on 10/02/2018.
 */
public interface UserService {

    boolean createUser(User user) throws ServletException;
    
    List<User> getUsers() ;

    User getUser( String username );

}
