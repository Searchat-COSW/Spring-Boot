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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean createActivity(Activity activity) throws ServletException {
        User admin=ur.findOne(activity.getAdministrator().getUsername());
        List<Lenguage> ll=new ArrayList();
        
        for (int i=0; i<activity.getLanguages().size();i++) {
            if(lr.findOne(activity.getLanguages().get(i).getLenguage())!=null){
                
                ll.add(lr.findOne(activity.getLanguages().get(i).getLenguage()));
            }
            else{
                ll.add(activity.getLanguages().get(i));
            }
        }
        activity.setLanguages(ll);
        activity.setAdministrator(admin);
        ar.save(activity);
        return true;
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
        ja.getParticipants().add(user);
        ar.save(ja);
        return true;
    }

    @Override
    public List<Activity> getAllActivities() {
        return ar.findAll();
    }
    
}
