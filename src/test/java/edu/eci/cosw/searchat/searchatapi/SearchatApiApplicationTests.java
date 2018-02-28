package edu.eci.cosw.searchat.searchatapi;

import edu.eci.cosw.searchat.searchatapi.model.Activity;
import edu.eci.cosw.searchat.searchatapi.model.ProfileInformation;
import edu.eci.cosw.searchat.searchatapi.model.User;
import edu.eci.cosw.searchat.searchatapi.service.ActivityService;
import edu.eci.cosw.searchat.searchatapi.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchatApiApplicationTests {

        @Autowired
        UserService userservice;
    
        @Autowired
        ActivityService activitiservie;
        
	@Test
	public void CreateAndCheckNewUser() {
            User tmp = new User("Pipeeeee", "pip@mail.com", "password", "Felipe", "Losada", null);
            try{
                Assert.assertEquals(userservice.createUser(tmp),true);
                User newUserCheck = userservice.getUser("Pipeeeee");
                Assert.assertEquals(tmp.getEmail(), newUserCheck.getEmail());
                Assert.assertEquals(tmp.getFirstname(), newUserCheck.getFirstname());
                Assert.assertEquals(tmp.getLastname(), newUserCheck.getLastname());
                Assert.assertEquals(tmp.getPassword(), newUserCheck.getPassword());
                Assert.assertEquals(tmp.getUsername(), newUserCheck.getUsername());
            }catch(ServletException ex){
                fail("Fail in CreateUser");
            }
	}
        
        @Test
	public void CantBeTwoUserWithTheSameUserName() {
            User tmp = new User("Pipe", "pip@mail.com", "password", "Felipe", "Losada", null);
            try{
                Assert.assertEquals(userservice.createUser(tmp),true);
                userservice.createUser(tmp);
                fail("Fail in CantBeTwoUserWithTheSameUserName");
            }catch(ServletException ex){
                
            }
	}

        @Test
        public void updateProfileInformationUser(){
            User tmp = new User("Felipeee", "pip@mail.com", "password", "Felipe", "Losada", null);
            ProfileInformation tmp_profile = new ProfileInformation("Colombia", "Spanish English German", "This its a prove", "http://e00-marca.uecdn.es/assets/multimedia/imagenes/2018/01/26/15169827142899.jpg");
            try{
                userservice.createUser(tmp);
                Assert.assertEquals(userservice.updateProfileInformation("Felipeee", tmp_profile), true);
                User newUserCheck = userservice.getUser("Felipeee");
                Assert.assertEquals(tmp.getProfileInformation().getAboutYou(),newUserCheck.getProfileInformation().getAboutYou());
                Assert.assertEquals(tmp.getProfileInformation().getImage(),newUserCheck.getProfileInformation().getImage());
                Assert.assertEquals(tmp.getProfileInformation().getLanguages(),newUserCheck.getProfileInformation().getLanguages());
                Assert.assertEquals(tmp.getProfileInformation().getNationality(),newUserCheck.getProfileInformation().getNationality());
            }catch(ServletException e){
                fail("Fail in UpdateProfileInformationUser");
            }
        }
        
        @Test
        public void getAllUser(){
            User tmp = new User("Pipeeee", "pip@mail.com", "password", "Felipe", "Losada", null);
            User tmp2 = new User("Felipeeee", "fel@mail.com", "password", "Feliipe", "Calderon", null);
            List<User> tmp_users = new ArrayList<>();
            tmp_users.add(tmp);
            tmp_users.add(tmp2);
            try{
                Assert.assertEquals(userservice.createUser(tmp),true);
                Assert.assertEquals(userservice.createUser(tmp2),true);
                List<User> newUsersCheck = userservice.getUsers();
                //usuarios por defecto
                int int_defect = 0;
                for(int i = 5; i  < newUsersCheck.size(); i++){
                    Assert.assertEquals(tmp_users.get(int_defect).getEmail(), newUsersCheck.get(i).getEmail());
                    Assert.assertEquals(tmp_users.get(int_defect).getFirstname(), newUsersCheck.get(i).getFirstname());
                    Assert.assertEquals(tmp_users.get(int_defect).getLastname(), newUsersCheck.get(i).getLastname());
                    Assert.assertEquals(tmp_users.get(int_defect).getPassword(), newUsersCheck.get(i).getPassword());
                    Assert.assertEquals(tmp_users.get(int_defect).getUsername(), newUsersCheck.get(i).getUsername());
                    int_defect++;
                }
            }catch(ServletException ex){
                fail("Fail in getAllUser");
            }
        }
        
        
        @Test
        public void createNewActivity(){
            User tmp = new User("Hernan", "pip@mail.com", "password", "Felipe", "Losada", null);
            Activity activity_tmp = new Activity("pruebaaa", "its a prove", tmp, "English", "Bogota", null, new ArrayList<User>(), "10");
            try{
                userservice.createUser(tmp);
                Assert.assertEquals(activitiservie.createActivity(activity_tmp),true);
                Activity checkActivity = activitiservie.getActivity("pruebaaa");
                Assert.assertEquals(activity_tmp.getName(), checkActivity.getName());
                Assert.assertEquals(activity_tmp.getDate(), checkActivity.getDate());
                Assert.assertEquals(activity_tmp.getDescription(), checkActivity.getDescription());
                Assert.assertEquals(activity_tmp.getLanguages(), checkActivity.getLanguages());
                Assert.assertEquals(activity_tmp.getPrice(), checkActivity.getPrice());
                Assert.assertEquals(activity_tmp.getAdministrator().toString(), checkActivity.getAdministrator().toString());
            }catch(ServletException ex){
                fail("Fail in CreateNewActivity");
            }
        }
        
        @Test
        public void cantBeTwoActivityWithTheSameName(){
            User tmp = new User("Pipe", "pip@mail.com", "password", "Felipe", "Losada", null);
            Activity activity_tmp = new Activity("prueba", "its a prove", tmp, "English", "Bogota", null, new ArrayList<User>(), "10");
            try{
                userservice.createUser(tmp);
                Assert.assertEquals(activitiservie.createActivity(activity_tmp),true);
                activitiservie.createActivity(activity_tmp);
                fail("Fail in cantBeTwoActitvityWitTheSameName");
            }catch(ServletException ex){
            }
        }
        
        @Test
        public void addNewParticipant(){
            User tmp = new User("Pipe", "pip@mail.com", "password", "Felipe", "Losada", null);
            User tmp2 = new User("Felipe", "fel@mail.com", "password", "Feliipe", "Calderon", null);
            Activity activity_tmp = new Activity("prueba", "its a prove", tmp, "English", "Bogota", null, new ArrayList<User>(), "10");
            try{
                userservice.createUser(tmp);
                userservice.createUser(tmp2);
                Assert.assertEquals(activitiservie.createActivity(activity_tmp),true);
                Assert.assertEquals(activitiservie.joinActivity("prueba", tmp2),true);
                Activity checkActivity = activitiservie.getActivity("prueba");
                Assert.assertEquals(checkActivity.getParticipants().get(0).toString(), tmp2.toString());
            }catch(ServletException ex){
                fail("fail in addNewParticipant");
            }
        }
        
        @Test
        public void cantBeAddAdministratorToActivity(){
            User tmp = new User("Pipe", "pip@mail.com", "password", "Felipe", "Losada", null);
            Activity activity_tmp = new Activity("prueba", "its a prove", tmp, "English", "Bogota", null, new ArrayList<User>(), "10");
            try{
                userservice.createUser(tmp);
                Assert.assertEquals(activitiservie.createActivity(activity_tmp),true);
                Assert.assertEquals(activitiservie.joinActivity("prueba", tmp), true);
                activitiservie.joinActivity("prueba", tmp);
                fail("Fail in cantBeAddAdministratorToActivity");
            }catch(ServletException ex){
            }
        }
        
        @Test
        public void findActivityByLocation(){
            User u_tmp = new User("Juan", "juan@prueba.com", "password", "Juan", "Herrera", "http://notiweb.escuelaing.edu.co/wp-content/uploads/2018/01/JuanCamilo_Swat.jpg");
            Activity act = new Activity("testing", "this is a test", u_tmp, "Spanish, English", "Bogota", "Today", null, "cheap");
            try{
                userservice.createUser(u_tmp);
                activitiservie.createActivity(act);
                activitiservie.joinActivity("testing", u_tmp);
                Assert.assertEquals(activitiservie.getActivitiesByLocation("Bogota").get(0),act);
            }catch(Exception e){
                
            }
        }
     
}
