package edu.eci.cosw.searchat.searchatapi.controller;


import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Created by Desarrollo on 10/02/2018.
 */

@RestController
    @RequestMapping( "user" )
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping( value = "/login", method = RequestMethod.POST )
    public ResponseEntity<?> login( @RequestBody User login )

    {
        String jwtToken = "";

        if ( login.getUsername() == null || login.getPassword() == null )
        {
            return new ResponseEntity<>("Please fill in username and password",HttpStatus.FORBIDDEN);
        }

        String username = login.getUsername();
        String password = login.getPassword();

        User user = userService.getUser( login.getUsername() );

        if ( user == null )
        {
            return new ResponseEntity<>("User username not found." ,HttpStatus.FORBIDDEN);
        }

        String pwd = user.getPassword();

        if ( !password.equals( pwd ) )
        {
            return new ResponseEntity<>("Invalid login. Please check your name and password." ,HttpStatus.FORBIDDEN);
        }

        jwtToken = Jwts.builder().setSubject( username ).claim( "roles", "user" ).setIssuedAt( new Date() ).signWith(
                SignatureAlgorithm.HS256, "secretkey" ).compact();

        return new ResponseEntity<>(new Token( jwtToken ),HttpStatus.ACCEPTED);
    }

    public class Token
    {

        String access_token;


        public Token( String access_token )
        {
            this.access_token = access_token;
        }


        public String getAccessToken()
        {
            return access_token;
        }

        public void setAccessToken( String access_token )
        {
            this.access_token = access_token;
        }
    }

    @RequestMapping( value = "/items", method = RequestMethod.GET )
    public ResponseEntity<?> getUsers () {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @RequestMapping( value = "/{username}", method = RequestMethod.GET )
    public ResponseEntity<?> getUser (@PathVariable String username) {
        System.out.println("---------------------------");
        System.out.println(username);
        return new ResponseEntity<>(userService.getUser(username),HttpStatus.ACCEPTED);
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

    @CrossOrigin
    @RequestMapping( value = "/{username}",method = RequestMethod.POST)
    public ResponseEntity<?> updateProfileInformation(@PathVariable String username, @RequestBody ProfileInformation profile){
        try{
            return new ResponseEntity<>(userService.updateProfileInformation(username, profile), HttpStatus.ACCEPTED);
        }catch(ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    
    @CrossOrigin
    @RequestMapping( value = "/update/{username}",method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User profile){
        try{
            System.out.println("----------------");
            System.out.println(profile.toString());
            System.out.println(profile.getProfileInformation().toString());
            return new ResponseEntity<>(userService.updateUser(username, profile), HttpStatus.ACCEPTED);
        }catch(ServletException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    
    @CrossOrigin
    @RequestMapping( value = "/{username}/image",method = RequestMethod.POST)
    public ResponseEntity<?> updateImageProfileInformation(MultipartHttpServletRequest request,@PathVariable String username){
        try {

            userService.addIMageProfileInformation(request,username);
        } catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    
    }

    @CrossOrigin
    @RequestMapping(value = "/{username}/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> imageProfileInformation(@PathVariable String username) {
        
        System.out.println("-------------------------------TEST---------------------------");
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(new InputStreamResource(userService.getImageProfileInformation(username)));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

