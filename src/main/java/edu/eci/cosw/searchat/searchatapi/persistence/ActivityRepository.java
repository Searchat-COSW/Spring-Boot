/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.persistence;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author 2105534
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer>{
    
    @Query("SELECT a from Activity a where a.location=?1")
    List<Activity> getActivitiesByLocation(String location);

    @Query("SELECT a from Activity a where a.administrator.username=?1")
    List<Activity> getActivitiesOwnedByUsername(String username);

    /*@Query("SELECT a from Activity a  inner join User u where u") //missing a good query
    public List<Activity> getActivitiesJoinedByUsername(String username);*/
}
