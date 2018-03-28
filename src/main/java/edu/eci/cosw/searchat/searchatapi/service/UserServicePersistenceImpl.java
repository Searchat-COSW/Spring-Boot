package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.persistence.UserRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServicePersistenceImpl implements UserService {

    @Autowired
    private UserRepository ur;

    @Override
    public boolean createUser(User user) throws ServletException {
        ur.save(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return ur.findAll();
    }

    @Override
    public User getUser(String username) {
        return ur.findOne(username);
    }

    @Override
    public boolean updateProfileInformation(String username, ProfileInformation profile) throws ServletException {
        User u = ur.findOne(username);
        if(u==null){throw new ServletException("Information profile can't update");}
        u.setProfileInformation(profile);
        ur.save(u);
        return true;
    }

    @Override
    public void addIMageProfileInformation(MultipartHttpServletRequest request, String username) throws ServletException {
        Iterator<String> itr = request.getFileNames();
        User u=null;
        while (itr.hasNext()) {
            String uploadedFile = itr.next();
            MultipartFile file = request.getFile(uploadedFile);
            //obtain the user 
            u = ur.findOne(username);
            if(u==null){
                throw new ServletException("Information profile can't update");
            }
           
            try {
                u.setImageProfileInformation(new SerialBlob(StreamUtils.copyToByteArray(file.getInputStream())));
            } catch (SQLException | IOException ex) {
                throw new ServletException("Information profile can't update");
            }
        }
        ur.save(u);
        

    }

    @Override
    public InputStream getImageProfileInformation(String username) throws SQLException, ServletException {
        User user = ur.getOne(username);
        if(user==null){
            throw new ServletException("Can't get profile image");
        }
        return user.obtainImageProfileInformation().getBinaryStream();
    }
}
