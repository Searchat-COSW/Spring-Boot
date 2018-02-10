package edu.eci.cosw.searchat.searchatapi.controller;


import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

/**
 * Created by Desarrollo on 10/02/2018.
 */

@RestController
@RequestMapping( "user" )
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping( value = "/items", method = RequestMethod.GET )
    public ResponseEntity<?> getUsers () {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping( value = "/item", method = RequestMethod.POST )
    public ResponseEntity<?> createUser (@RequestBody User user) {
        try{
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.ACCEPTED);
        }
        catch (ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
}

