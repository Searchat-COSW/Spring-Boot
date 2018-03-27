package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServicePersistenceImpl implements UserService {

    @Autowired
    private UserRepository ur;

    @Override
    public boolean createUser(User user) throws ServletException {
        return false;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser(String username) {
        return ur.findOne(username);
    }

    @Override
    public boolean updateProfileInformation(String username, ProfileInformation profile) throws ServletException {
        return false;
    }

    @Override
    public void addIMageProfileInformation(MultipartHttpServletRequest request, String username) throws ServletException {

    }

    @Override
    public InputStream getImageProfileInformation(String username) throws SQLException, ServletException {
        return null;
    }
}
