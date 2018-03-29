/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.controller;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.service.ActivityService;
import edu.eci.cosw.searchat.searchatapi.service.UserService;
import javax.servlet.ServletException;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private UserService userService;
    
    @CrossOrigin
    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public ResponseEntity<?> getActivity(@PathVariable int id){
        return new ResponseEntity<>(activityService.getActivity(id), HttpStatus.ACCEPTED);
    }
    
    @CrossOrigin
    @RequestMapping( value = "/", method = RequestMethod.GET )
    public ResponseEntity<?> allActivities(){
        return new ResponseEntity<>(activityService.getAllActivities(), HttpStatus.ACCEPTED);
    }
    
    @CrossOrigin
    @RequestMapping( value = "/location/{location}", method = RequestMethod.GET )
    public ResponseEntity<?> getActivitiesByLocation (@PathVariable String location) {
        return new ResponseEntity<>(activityService.getActivitiesByLocation(location), HttpStatus.ACCEPTED);
    }
    
    @CrossOrigin
    @RequestMapping( value = "/create", method = RequestMethod.POST )
    public ResponseEntity<?> createActivity (@RequestBody Activity activity) {
        
        try{
            //System.out.println("-------------------------------------creando actividad------------------------------");
            return new ResponseEntity<>(activityService.createActivity(activity), HttpStatus.ACCEPTED);
        }
        catch (ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    
    @CrossOrigin
    @RequestMapping( value = "/join/{activityId}", method = RequestMethod.POST )
    public ResponseEntity<?> joinActivity(@PathVariable int activityId,@RequestBody String username) {
        try{
            return new ResponseEntity<>(activityService.joinActivity(activityId,userService.getUser(username)), HttpStatus.ACCEPTED);
        }
        catch (ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    
}
