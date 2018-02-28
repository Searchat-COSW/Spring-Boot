package edu.eci.cosw.searchat.searchatapi.model;

/**
 * Created by Desarrollo on 10/02/2018.
 */
public class User
{

    private String email;

    private String password;

    private String firstname;

    private String lastname;

    private String username;

    private String image;

    private ProfileInformation profileInformation;


    /**
     * 
     */
    public User()
    {
    }

    /**
     * 
     * @param username
     * @param email
     * @param password
     * @param firstname
     * @param lastname
     * @param image 
     */
    public User( String username, String email, String password, String firstname, String lastname,String image )
    {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.image = image;
        this.username = username;
    }

    /**
     * 
     * @return 
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * 
     * @param email 
     */
    public void setEmail( String email )
    {
        this.email = email;
    }

    /**
     * 
     * @return 
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * 
     * @param password 
     */
    public void setPassword( String password )
    {
        this.password = password;
    }

    /**
     * 
     * @return 
     */
    public String getFirstname()
    {
        return firstname;
    }

    /**
     * 
     * @param firstname 
     */
    public void setFirstname( String firstname )
    {
        this.firstname = firstname;
    }

    /**
     * 
     * @return 
     */
    public String getLastname()
    {
        return lastname;
    }

    /**
     * 
     * @param lastname 
     */
    public void setLastname( String lastname )
    {
        this.lastname = lastname;
    }

    /**
     * 
     * @return 
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * 
     * @param username 
     */
    public void setUsername( String username )
    {
        this.username = username;
    }

    /**
     * 
     * @return 
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image 
     */
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                '}';
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
}
