package edu.eci.cosw.searchat.searchatapi.model;

import java.sql.Blob;
import java.time.LocalDateTime;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

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
    private String date;
    private List<User> participants;
    private Long price;
    private Blob image;
    private Double longitude;
    private Double latitude;


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
    public Activity(String name, String description, User administrator, ArrayList<Lenguage> languages, String location, String date, List<User> participants, Long price) {
        this.name = name;
        this.description = description;
        this.administrator = administrator;
        this.lenguages = languages;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.price = price;
    }

    public Activity(int id, String name, String description, User administrator, List<Lenguage> lenguages, String location, String date, List<User> participants, Long price, Blob image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.administrator = administrator;
        this.lenguages = lenguages;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.price = price;
        this.image = image;
    }

    public Activity(int id, String name, String description, User administrator, List<Lenguage> lenguages, String location, String date, List<User> participants, Long price, Blob image, Double longitude, Double latitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.administrator = administrator;
        this.lenguages = lenguages;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.price = price;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
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
    public List<Lenguage> getLenguages() {
        return lenguages;
    }

    /**
     * 
     * @param languages 
     */
    
    public void setLenguages(List<Lenguage> languages) {
        this.lenguages = languages;
    }

    /**
     * 
     * @return 
     */

    @Column(name = "date", nullable = true)
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
    public Long getPrice() {
        return price;
    }

    /**
     * 
     * @param price 
     */
    public void setPrice(Long price) {
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


    @JsonIgnore
    @Column(name = "image", nullable = true)
    public Blob getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Blob image) {
        this.image = image;
    }


    @Column(name = "longitude", nullable = true)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude", nullable = true)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", administrator=" + administrator +
                ", lenguages=" + lenguages +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", participants=" + participants +
                ", price=" + price +
                ", image=" + image +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
