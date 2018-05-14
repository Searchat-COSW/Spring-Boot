/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author carlos.sanchez-v
 */
@Table(name = "SEARCHAT_LENGUAGES")
@Entity
public class Lenguage {
    
    private String lenguage;
    private List<ProfileInformation> profileInformations = new ArrayList<>();;
    private List<Activity> activities;

    public Lenguage(){}
    
    /**
     * @return the lenguage
     */
    @Id
    @Column(name = "lenguage",unique = true, nullable = true)
    public String getLenguage() {
        return lenguage;
    }

    /**
     * @param lenguage the lenguage to set
     */
    
    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    /**
     * @return the profileInformations
     */
    //@Column(name = "profile_information", nullable = true)
    @ManyToMany(mappedBy="languages", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<ProfileInformation> getProfileInformations() {
        return profileInformations;
    }

    /**
     * @param profileInformations the profileInformations to set
     */
    public void setProfileInformations(List<ProfileInformation> profileInformations) {
        this.profileInformations = profileInformations;
    }

    @Override
    public String toString() {
        return "Lenguage{" + "lenguage=" + lenguage + ", profileInformations=" + profileInformations + '}';
    }

    /**
     * @return the activities
     */
    @ManyToMany(mappedBy="lenguages",fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
    
}
