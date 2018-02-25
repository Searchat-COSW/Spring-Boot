/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

/**
 *
 * @author JuanHerrera
 */
@Service
public class ActivityServiceImpl implements ActivityService{
    
    private List<Activity> activities = new ArrayList<>();
    
    @PostConstruct
    public void populateSampleData(){
        User u = new User("Juan", "juan.c.herrerav@hotmail.com", "Juan", "Juan", "Herrera", "http://notiweb.escuelaing.edu.co/wp-content/uploads/2018/01/JuanCamilo_Swat.jpg");
        List<User> part = new ArrayList<>();
        part.add(new User("Jhordy", "jhordy@hotmail.com", "Jhordy", "Jhordy", "Salinas", "http://www.escuelaing.edu.co/uploads/generica/810-2.jpg"));
        activities.add(new Activity("Climbing_Monserrate", "In this activity we'll be going all the way up to Monserrate by foot",u, 
                "Spanish, English" ,"Bogotá","22/02/2018 10:00",part, "40000"));
    }

    @Override
    public boolean createActivity(Activity activity) throws ServletException {
        if (activities.stream().anyMatch(h->h.getName().equals(activity.getName()))){
            throw new ServletException ("Username already exist");
        }
        
        return activities.add(activity);
    }

    @Override
    public List<Activity> getActivitiesByLocation(String location) {
        return activities.stream().filter(h-> h.getLocation().equals(location)).collect(Collectors.toList());
    }

    @Override
    public Activity getActivity(String activityName) {
        Optional<Activity> found = activities.stream().filter(h-> h.getName().equals(activityName)).findFirst();
        Activity ans = found.isPresent()?found.get():null;
        return ans;
    }

    @Override
    public boolean joinActivity(String activityName, User user) throws ServletException {
        Activity activity_tmp = getActivity(activityName);
        if(activity_tmp==null){
            throw new ServletException("Information profile can't update");
        }activity_tmp.joinActivity(user);
        return true;
    }
}