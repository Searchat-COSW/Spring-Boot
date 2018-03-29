/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.persistence.ActivityRepository;
import edu.eci.cosw.searchat.searchatapi.persistence.UserRepository;
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
    
    @Override
    public boolean createActivity(Activity activity) throws ServletException {
        User admin=ur.findOne(activity.getAdministrator().getUsername());
        activity.setAdministrator(admin);
        ar.save(activity);
        return true;
    }

    @Override
    public List<Activity> getActivitiesByLocation(String location) {
        return null;
    }

    @Override
    public Activity getActivity(String activityName) {
        return null;
    }

    @Override
    public boolean joinActivity(String activityName, User user) throws ServletException {
        return true;
    }

    @Override
    public List<Activity> getAllActivities() {
        System.out.println("------------------------------EN TODAS--------------------");
        return ar.findAll();
    }
    
}
