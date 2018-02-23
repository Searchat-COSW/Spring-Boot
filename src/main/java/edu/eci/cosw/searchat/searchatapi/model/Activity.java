package edu.eci.cosw.searchat.searchatapi.model;

import java.util.Arrays;

/**
 * Created by Juan on 21/02/2018
 **/

public class Activity{

    private String name;
    private String description;
    private User administrator;
    private String[] languages;
    private String location;
    private String date;
    private User[] participants;
    private String price;

    public Activity() {
    }

    public Activity(String name, String description, User administrator, String[] languages, String location, String date, User[] participants, String price) {
        this.name = name;
        this.description = description;
        this.administrator = administrator;
        this.languages = languages;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.price = price;
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAdministrator() {
        return administrator;
    }

    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User[] getParticipants() {
        return participants;
    }

    public void setParticipants(User[] participants) {
        this.participants = participants;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Activity{" + "name=" + name + ", description=" + description + ", administrator=" + administrator + ", languages=" + languages + ", location=" + location + ", date=" + date + ", participants=" + participants + ", price=" + price + '}';
    }

    
}