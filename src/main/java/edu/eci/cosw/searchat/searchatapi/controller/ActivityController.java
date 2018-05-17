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
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.sql.SQLException;

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
            
            System.out.println(activity);
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
            System.out.println("Activity ID "+activityId);
            System.out.println("Username ->"+username);
            return new ResponseEntity<>(activityService.joinActivity(activityId,userService.getUser(username)), HttpStatus.ACCEPTED);
        }
        catch (ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }


    @CrossOrigin
    @RequestMapping( value = "/{activityId}/image",method = RequestMethod.POST)
    public ResponseEntity<?> updateImageProfileInformation(MultipartHttpServletRequest request, @PathVariable int activityId){
        try {
            
            activityService.addIMageActivity(request,activityId);
        } catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(value = "/{activityId}/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> imageProfileInformation(@PathVariable int activityId) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(new InputStreamResource(activityService.getImageActivity(activityId)));
        } catch (ServletException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (SQLException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @CrossOrigin
    @RequestMapping( value = "/owned/{username}", method = RequestMethod.GET )
    public ResponseEntity<?> getOwnActivities(@PathVariable String username){
        try{
            return new ResponseEntity<>(activityService.getOwnedActivities(username), HttpStatus.ACCEPTED);
        }catch(ServletException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @CrossOrigin
    @RequestMapping( value = "/joined/{username}", method = RequestMethod.GET )
    public ResponseEntity<?> getJoinActivities(@PathVariable String username){
        try{
            return new ResponseEntity<>(activityService.getJoinedActivities(username), HttpStatus.ACCEPTED);
        }catch(ServletException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
