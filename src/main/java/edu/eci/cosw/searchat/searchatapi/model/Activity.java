package edu.eci.cosw.searchat.searchatapi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.servlet.ServletException;

/**
 * Created by Juan on 21/02/2018
 **/
@Table(name = "SEARCHAT_ACTIVITY")
@Entity
public class Activity implements java.io.Serializable{

    private int id;
    private String name;
    private String description;
    private User administrator;
    private List<Lenguage> lenguages;
    private String location;
    private Date date;
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
    public Activity(String name, String description, User administrator, ArrayList<Lenguage> languages, String location, Date date, List<User> participants, String price) {
        this.name = name;
        this.description = description;
        this.administrator = administrator;
        this.lenguages = languages;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.price = price;
    }

    /**
     * 
     * @return 
     */
    @Column(name = "name", nullable = true)
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
    @Column(name = "description", nullable = true)
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
    @ManyToOne(cascade=ALL)
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
    @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "SEARCHAT_ACTIVITIES_LENGUAGES",          
                joinColumns =              
                        @JoinColumn(name="ACTIVITIES_id", referencedColumnName="id"),         
                inverseJoinColumns =              
                        @JoinColumn(name="LENGUAGE_lenguage", referencedColumnName="lenguage")
        )
    public List<Lenguage> getLanguages() {
        return lenguages;
    }

    /**
     * 
     * @param languages 
     */
    
    public void setLanguages(List<Lenguage> languages) {
        this.lenguages = languages;
    }

    /**
     * 
     * @return 
     */
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date 
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 
     * @return 
     */
    
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        @JoinTable(name = "SEARCHAT_ACTIVITIES_USERS",          
                joinColumns =              
                        @JoinColumn(name="ACTIVITIES_id", referencedColumnName="id"),         
                        //@JoinColumn(name="USER_username", referencedColumnName="username"),
                inverseJoinColumns =  
                        //@JoinColumn(name="ACTIVITIES_id", referencedColumnName="id")
                        @JoinColumn(name="USER_username", referencedColumnName="profile_information_username")
        )
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
    @Column(name = "price", nullable = true)
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
    @Column(name = "location", nullable = true)
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
        return "Activity{" + "name=" + name + ", description=" + description + ", administrator=" + administrator + ", languages=" + lenguages + ", location=" + location + ", date=" + date + ", participants=" + participants + ", price=" + price + '}';
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

    /**
     * @return the id
     */
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
}