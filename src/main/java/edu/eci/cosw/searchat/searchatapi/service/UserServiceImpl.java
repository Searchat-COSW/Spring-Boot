package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.User;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Desarrollo on 10/02/2018.
 */

@Service
public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();

    @Override
    public boolean createUser( User user ) throws ServletException {
        if (users.stream().anyMatch(h->h.getUsername().equals(user.getUsername()))){
            throw new ServletException ("Username already exist");
        }
        return users.add(user);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
