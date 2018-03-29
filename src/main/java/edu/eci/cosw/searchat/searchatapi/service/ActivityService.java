/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author JuanHerrera
 */
public interface ActivityService {
    
    /**
     * 
     * @param activity
     * @return
     * @throws ServletException 
     */
    boolean createActivity(Activity activity) throws ServletException;

    /**
     * 
     * @param location
     * @return 
     */
    List<Activity> getActivitiesByLocation(String location);

    /**
     * 
     * @param activityName
     * @return 
     */
    Activity getActivity(int activityId);
    
    /**
     * 
     * @param activityId
     * @param user
     * @return
     * @throws ServletException 
     */
    boolean joinActivity(int activityId, User user)throws ServletException;
    
    List<Activity> getAllActivities();
}
