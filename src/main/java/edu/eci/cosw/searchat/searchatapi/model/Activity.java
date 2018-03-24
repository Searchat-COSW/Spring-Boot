package edu.eci.cosw.searchat.searchatapi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;

/**
 * Created by Juan on 21/02/2018
 **/

public class Activity{

    private String name;
    private String description;
    private User administrator;
    private List<String> languages;
    private String location;
    private String date;
    private List<User> participants;
    private String price;

    /**
     * 
     */
    public Activity() {
    }

    /**
     * 
     * @param name
     * @param description
     * @param administrator
     * @param languages
     * @param location
     * @param date
     * @param participants
     * @param price 
     */
    public Activity(String name, String description, User administrator, ArrayList<String> languages, String location, String date, List<User> participants, String price) {
        this.name = name;
        this.description = description;
        this.administrator = administrator;
        this.languages = languages;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.price = price;
    }

    /**
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return 
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description 
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return 
     */
    public User getAdministrator() {
        return administrator;
    }

    /**
     * 
     * @param administrator 
     */
    public void setAdministrator(User administrator) {
        this.administrator = administrator;
    }

    /**
     * 
     * @return 
     */
    public List<String> getLanguages() {
        return languages;
    }

    /**
     * 
     * @param languages 
     */
    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }

    /**
     * 
     * @return 
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date 
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return 
     */
    public List<User> getParticipants() {
        return participants;
    }

    /**
     * 
     * @param participants 
     */
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    /**
     * 
     * @return 
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price 
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 
     * @return 
     */
    public String getLocation() {
        return location;
    }

    /**
     * 
     * @param location 
     */
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Activity{" + "name=" + name + ", description=" + description + ", administrator=" + administrator + ", languages=" + languages + ", location=" + location + ", date=" + date + ", participants=" + participants + ", price=" + price + '}';
    }
    
    /**
     * 
     * @param user
     * @throws ServletException 
     */
    public void joinActivity(User user) throws ServletException{
        Boolean tmp = false;
        for(int i = 0; i < participants.size() && !tmp; i++){
            if(participants.get(i).getUsername().equals(user.getUsername()) || administrator.getUsername().equals(user.getUsername())){
                tmp = true;
                
            }
        }
        if(!tmp){
            participants.add(user);
        }else{
            throw new ServletException("This user its already in the activity");
        }
    }

    
}