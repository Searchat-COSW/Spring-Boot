package edu.eci.cosw.searchat.searchatapi.controller;

import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping( "user" )
public class UserController
{

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

}