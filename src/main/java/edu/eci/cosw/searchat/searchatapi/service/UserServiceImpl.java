package edu.eci.cosw.searchat.searchatapi.service;


import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//@Service
public class UserServiceImpl  implements UserService
{
    private List<User> users = new ArrayList<>();

    @PostConstruct
    private void populateSampleData()
    {
        users.add( new User( "xyz","test@mail.com", "password", "Andres", "Perez") );
    }

    @Override
    public User getUser( String username )
    {
        Optional<User> found = users.stream().filter(h-> h.getUsername().equals(username)).findAny();
        User ans = found.isPresent()?found.get():null;
        return ans;
    }

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

	@Override
	public boolean updateProfileInformation(String username, ProfileInformation profile) throws ServletException {
        User userUpdate = getUser(username);
        if(userUpdate==null){
            throw new ServletException("Information profile can't update");
        }
        userUpdate.setProfileInformation(profile);
        return true;
	}

    @Override
    public void addIMageProfileInformation(MultipartHttpServletRequest request, String username) throws ServletException {
        Iterator<String> itr = request.getFileNames();

        while (itr.hasNext()) {
            String uploadedFile = itr.next();
            MultipartFile file = request.getFile(uploadedFile);
            //obtain the user 
            User userUpdate = getUser(username);
            if(userUpdate==null){
                throw new ServletException("Information profile can't update");
            }
           
            try {
                userUpdate.setImageProfileInformation(new SerialBlob(StreamUtils.copyToByteArray(file.getInputStream())));
            } catch (SQLException | IOException ex) {
                throw new ServletException("Information profile can't update");
            }
        }
    }

    @Override
    public InputStream getImageProfileInformation(String username) throws SQLException, ServletException {
        User user = getUser(username);
        if(user==null){
            throw new ServletException("Can't get profile image");
        }
        return user.obtainImageProfileInformation().getBinaryStream();
    }

    @Override
    public boolean updateUser(String username, User profile) throws ServletException {
        User user = getUser(username);
        if(user==null){
            throw new ServletException("Can't get profile image");
        }user.setProfileInformation(profile.getProfileInformation());
        user.setFirstname(profile.getFirstname());
        user.setLastname(profile.getLastname());
        return true;
    }

}
