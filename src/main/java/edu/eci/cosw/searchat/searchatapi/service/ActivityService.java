/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.service;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author JuanHerrera
 */
public interface ActivityService {
    boolean createActivity(Activity activity) throws ServletException;

    List<Activity> getActivitiesByLocation(String location);

    Activity getActivity(String activityName);
}
