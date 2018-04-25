/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.InputStream;
import java.sql.SQLException;
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
    Activity createActivity(Activity activity) throws ServletException;

    /**
     * 
     * @param location
     * @return 
     */
    List<Activity> getActivitiesByLocation(String location);

    /**
     * 
     * @param activityId
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

    void addIMageActivity(MultipartHttpServletRequest request, int activityId) throws ServletException;

    InputStream getImageActivity(int activityId) throws SQLException,ServletException;
}
