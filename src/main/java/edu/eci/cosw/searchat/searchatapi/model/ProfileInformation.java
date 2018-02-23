package edu.eci.cosw.searchat.searchatapi.model;

public class ProfileInformation{
    
    private String nationality;
    private String languages;
    private String aboutYou;
    private String image;

    public ProfileInformation(){}

    public ProfileInformation(String nationality, String languages, String aboutYou, String image){
        this.nationality = nationality;
        this.languages = languages;
        this.aboutYou = aboutYou;
        this.image = image;
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
	public String getLanguages() {
		return languages;
	}

	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(String languages) {
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
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

}