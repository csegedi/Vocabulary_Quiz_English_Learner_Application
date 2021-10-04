package Project.Vocabulary.Quiz_MVC.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Project.Vocabulary.Quiz_MVC.db.Database;
import Project.Vocabulary.Quiz_MVC.model.User;

@Controller
public class UserController {
	
	/**I. REGISTRATION PROCESS*/
	
	@GetMapping("signIn")
	public String registration() {
		
		return "signIn.html";
	}
	
	
	@PostMapping("/signIn/result" )
	public String registrationResult (@RequestParam (name="username") String username,
								@RequestParam (name="password") String password) {
		
		Database db=new Database(); 
		String returnPage=null; 
		
		
		//Check the user in the database: if the username can be found in the database, the registration will be unsuccessful.
		User user=new User(username, password); 
		List <User> users=db.getUserByName(username); 
		
		if (users.isEmpty()==true) {
			
			//Saving the user into the database
			db.insertUser(username, password, user.getPhrasalVerbPoints(), user.getCollocationsPoints(),
					user.getNounsPoints(), user.getAdjectivesPoints(),
					user.getSentencesPoints(), user.getAdverbsPoints(),
					user.getInformaticVocabularyPoints(),
					user.getAttempts(), user.getWins(), user.getLost()); 
			
			returnPage="completedRegistration.html"; 

		}
		
		else {
			returnPage="failedRegistration.html"; 
		}
		
		
		db.close(); 
		
		return returnPage;
		
	}

}
