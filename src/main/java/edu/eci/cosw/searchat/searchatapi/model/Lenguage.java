/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.searchat.searchatapi.model;

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
    private List<ProfileInformation> profileInformations;
    //private List<Activity> profileInformations;

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
    @ManyToMany(mappedBy="languages",fetch= FetchType.EAGER, cascade = CascadeType.ALL)
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
    
}
