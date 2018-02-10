package edu.eci.cosw.searchat.searchatapi.service;



import edu.eci.cosw.searchat.searchatapi.model.User;

import javax.servlet.ServletException;
import java.util.List;

public interface UserService
{


    User getUser( String username );

    User createUser( User user ) throws ServletException;

}