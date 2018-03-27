package edu.eci.cosw.searchat.searchatapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;

/**
 * Created by Desarrollo on 10/02/2018.
 */
@Table(name = "SEARCHAT_USER")
@Entity
public class User implements java.io.Serializable{

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String username;


    private ProfileInformation profileInformation;

    /**
     *
     */

    public User() {
    }

    /**
     *
     * @param username
     * @param email
     * @param password
     * @param firstname
     * @param lastname
     */
    public User(String username, String email, String password, String firstname, String lastname ) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     */
    @Id
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", profileInformation=" + profileInformation + '}';
    }

    /**
     * @return the profileInformation
     */
    public ProfileInformation getProfileInformation() {
        return profileInformation;
    }

    /**
     * @param profileInformation the profileInformation to set
     */
    public void setProfileInformation(ProfileInformation profileInformation) {
        this.profileInformation = profileInformation;
    }
    
    public void setImageProfileInformation(Blob img){
        this.profileInformation.setImage(img);
    }

    @JsonIgnore
    public Blob getImageProfileInformation(){return this.profileInformation.getImage();}
}
