package edu.eci.cosw.searchat.searchatapi.service;


import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

}
