/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.controller;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.service.ActivityService;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JuanHerrera
 */
@RestController
@RequestMapping( "activity" )
public class ActivityController {
    
    @Autowired
    private ActivityService activityService;
    
    @RequestMapping( value = "/items", method = RequestMethod.GET )
    public ResponseEntity<?> getActivities () {
        return new ResponseEntity<>(activityService.getActivities(), HttpStatus.ACCEPTED);
    }
    
    @RequestMapping( value = "/item", method = RequestMethod.POST )
    public ResponseEntity<?> createActivity (@RequestBody Activity activity) {
        try{
            return new ResponseEntity<>(activityService.createActivity(activity), HttpStatus.ACCEPTED);
        }
        catch (ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
}
