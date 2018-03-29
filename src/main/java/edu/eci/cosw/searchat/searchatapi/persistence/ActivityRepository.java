/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.persistence;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 2105534
 */
public interface ActivityRepository extends JpaRepository<Activity, Integer>{
    
}
