package edu.eci.cosw.searchat.searchatapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.sql.Blob;
import java.util.List;

public class ProfileInformation{
    
    private String nationality;
    private List<String> languages;
    private String aboutYou;
    private Blob image;

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
    public ProfileInformation(String nationality, ArrayList<String> languages, String aboutYou){
        this.nationality = nationality;
        this.languages = languages;
        this.aboutYou = aboutYou;
    }

	/**
	 * @return the nationality
	 */
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
	public List<String> getLanguages() {
		return languages;
	}

	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}

	/**
	 * @return the aboutYou
	 */
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
	public Blob getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

}