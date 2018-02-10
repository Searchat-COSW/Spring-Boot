package edu.eci.cosw.searchat.searchatapi.service;


import edu.eci.cosw.searchat.searchatapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl  implements UserService
{

    private List<User> users = new ArrayList<>();

    @PostConstruct
    private void populateSampleData()
    {
        users.add( new User( "xyz","test@mail.com", "password", "Andres", "Perez", "https://ams.educause.edu/eweb/upload/60283746.jpg" ) );
    }


    @Override
    public User getUser( String username )
    {
        User ans = null;
        for (int i =0;i<users.size() ;i++){
            if(users.get(i).getUsername().equals(username)){
                ans=users.get(i);
            }
        }
        return ans;
    }

    @Override
    public User createUser( User user ) throws ServletException
    {

        if(yaEstaRegistrado(user.getUsername())){
            throw new ServletException("El username ya se encuentra registrado en la base de datos");
        }
        users.add(user);
        return user;
    }

    private boolean yaEstaRegistrado(String username) {
        boolean ans = false;
        for (int i =0;i<users.size() && !ans ;i++){
            ans=users.get(i).getUsername().equals(username);
        }
        return ans;
    }



}