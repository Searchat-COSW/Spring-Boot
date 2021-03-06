/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.Lenguage;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.persistence.ActivityRepository;
import edu.eci.cosw.searchat.searchatapi.persistence.LenguageRepository;
import edu.eci.cosw.searchat.searchatapi.persistence.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *
 * @author 2105534
 */
@Service
public class ActivityServicePersistenceImpl implements ActivityService{
    
    @Autowired
    private UserRepository ur;
    
    @Autowired
    private ActivityRepository ar;
    
    @Autowired
    private LenguageRepository lr;
    
    @Override
    public Activity createActivity(Activity activity) throws ServletException {
        User admin=ur.findOne(activity.getAdministrator().getUsername());
        List<Lenguage> ll=new ArrayList();
        
        for (int i=0; i<activity.getLenguages().size();i++) {
            if(lr.findOne(activity.getLenguages().get(i).getLenguage())!=null){
                
                ll.add(lr.findOne(activity.getLenguages().get(i).getLenguage()));
            }
            else{
                ll.add(activity.getLenguages().get(i));
            }
        }
        activity.setLenguages(ll);
        activity.setAdministrator(admin);
        ar.save(activity);
        return activity; //problems with ID of the activity, maybe solving with the last activity created by the user
    }

    @Override
    public List<Activity> getActivitiesByLocation(String location) {
        
        return ar.getActivitiesByLocation(location);
    }

    @Override
    public Activity getActivity(int activityId) {
        return ar.findOne(activityId);
    }

    @Override
    public boolean joinActivity(int activityId, User user) throws ServletException {
        Activity ja=ar.findOne(activityId);
        List<User> participants = ja.getParticipants();
        boolean check = false;
        String username = user.getUsername();
        for(int i = 0; i < participants.size() && !check; i++){
            if(participants.get(i).getUsername().equals(username)){
                check = true;
            }
        }if(!check && !ja.getAdministrator().getUsername().equals(username)){
            ja.getParticipants().add(user);
            ar.save(ja);
        }        
        return true;
    }

    @Override
    public List<Activity> getAllActivities() {
        return ar.findAll();
    }

    @Override
    public void addIMageActivity(MultipartHttpServletRequest request, int activityId) throws ServletException {
        Iterator<String> itr = request.getFileNames();
        Activity a=null;
        while (itr.hasNext()) {
            String uploadedFile = itr.next();
            MultipartFile file = request.getFile(uploadedFile);
            //obtain the user
            a = ar.findOne(activityId);
            if(a==null){
                throw new ServletException("Activity can't update");
            }

            try {
                a.setImage(new SerialBlob(StreamUtils.copyToByteArray(file.getInputStream())));
            } catch (SQLException | IOException ex) {
                throw new ServletException("Activity can't update");
            }
        }
        ar.save(a);
    }

    @Override
    public InputStream getImageActivity(int activityId) throws SQLException, ServletException {
        Activity a = ar.getOne(activityId);
        if(a==null){
            throw new ServletException("Can't get activity image");
        }
        return a.getImage().getBinaryStream();
    }

    @Override
    public List<Activity> getOwnedActivities(String username) throws ServletException {
        return ar.getActivitiesOwnedByUsername(username);
    }

    @Override
    public List<Activity> getJoinedActivities(String username) throws ServletException {
        return ar.getActivitiesJoinedByUsername(username);
    }

}
