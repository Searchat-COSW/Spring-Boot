package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;

import javax.servlet.ServletException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    
    void addIMageProfileInformation(MultipartHttpServletRequest request, String username) throws ServletException;

    InputStream getImageProfileInformation(String username) throws SQLException,ServletException;
    
    boolean updateUser(String username, User profile) throws ServletException;

}
