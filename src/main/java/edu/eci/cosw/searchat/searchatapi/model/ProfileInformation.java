package edu.eci.cosw.searchat.searchatapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.sql.Blob;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Table(name = "SEARCHAT_PROFILE_INFORMATION")
@Entity
public class ProfileInformation implements java.io.Serializable{
    
    private String username;
    
    
    private String nationality;
    private List<Lenguage> languages = new ArrayList<>();
    private String aboutYou;
    private Blob image;

    @Override
    public String toString() {
        return "ProfileInformation{" + "username=" + username + ", nationality=" + nationality + ", languages=" + languages + ", aboutYou=" + aboutYou + ", image=" + image + '}';
    }

    /**
     * 
     */
    public ProfileInformation(){}

    /**
     * 
     * @param nationality
     * @param languages
     * @param aboutYou 
     */
    public ProfileInformation(String username,String nationality, ArrayList<Lenguage> languages, String aboutYou){
        this.username = username;
        this.nationality = nationality;
        //this.languages = languages;
        this.aboutYou = aboutYou;
    }

	/**
	 * @return the nationality
	 */
        @Column(name = "nationality", nullable = true)
	public String getNationality() {
		return nationality;
	}

	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * @return the languages
	 */
        //@ElementCollection
        //CollectionTable(name="searchat_lenguages", joinColumns=@JoinColumn(name="username"))
        //@Column(name="lenguage",nullable = true)
        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "SEARCHAT_PROFILE_INFORMATIONS_LENGUAGES",          
                joinColumns =              
                        @JoinColumn(name="PROFILE_username", referencedColumnName="username"),         
                inverseJoinColumns =              
                        @JoinColumn(name="LENGUAGE_lenguage", referencedColumnName="lenguage")
        )
	public List<Lenguage> getLanguages() {
		return languages;
	}

	/**
	 * @param languages the languages to set
	 */
        
	public void setLanguages(List<Lenguage> languages) {
		this.languages = languages;
	}

	/**
	 * @return the aboutYou
	 */
        @Column(name = "aboutYou", nullable = true)
	public String getAboutYou() {
		return aboutYou;
	}

	/**
	 * @param aboutYou the aboutYou to set
	 */
	public void setAboutYou(String aboutYou) {
		this.aboutYou = aboutYou;
	}

	/**
	 * @return the image
	 */
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

    /**
     * @return the username
     */
    @Id
    @Column(name = "username",unique = true, nullable = true)
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


}