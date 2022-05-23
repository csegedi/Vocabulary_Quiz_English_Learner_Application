package Project.Vocabulary.Quiz_MVC.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Project.Vocabulary.Quiz_MVC.db.Database;
import Project.Vocabulary.Quiz_MVC.model.Category;
import Project.Vocabulary.Quiz_MVC.model.Difficulty_Level;
import Project.Vocabulary.Quiz_MVC.model.Language;
import Project.Vocabulary.Quiz_MVC.model.User;
import Project.Vocabulary.Quiz_MVC.model.Word;

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
		
		if ( (username.isEmpty()!=true) && (password.isEmpty()!=true)  ) {
		
			//Check the user in the database: if the username can be found in the database, the registration will be unsuccessful.
			User user=new User(username, password); 
			List <User> users=db.getUserByName(username); 
		
			if (users.isEmpty()==true) {
				
				//Saving the user into the database
				db.saveUser(user);
			
				returnPage="completedRegistration.html"; 
			}
		
			else {
			
				returnPage="failedRegistration.html"; 
			}
		}
		
		else {
			returnPage="failedRegistration.html"; 
		}
		
		db.close(); 
		
		return returnPage;
		
	}
	
	@PostMapping ("/login")
	public String login () {

		return "userLogin.html";
	}
	
	@PostMapping("/user")
	public String userMain(Model model,
		@RequestParam (required = false, name="username") String username,
		@RequestParam (required = false, name="password") String password,
		@CookieValue (required=false,  name="cookie_userId") String cookie_userId,
		HttpServletResponse response) {
		
		Database db=new Database(); 
		String returnPage=null; 
		User user=null; 
		List <Category> categories=db.getAllCategory(); 
		ArrayList<String>ranksInWords=null; 
		
		//First enter of the user after the login:
		if ( (username!=null) && (password!=null) ) {
			
			List <User> users=db.getUserByNameAndPassword(username, password); 
			
			
			if (users.size()==1) {
				
				user=users.get(0); 
				Integer userId=user.getId();
				String formatted_userId=userId.toString(); 
				
				Cookie cookie1=new Cookie("cookie_userId", formatted_userId); 
				response.addCookie(cookie1); 
				user.fillTheRanksToWordList(); 
				ranksInWords=user.getRanksToWord(); 
				
				
				model.addAttribute("user", user); 
				model.addAttribute("categoryList", categories); 
				model.addAttribute("ranksList", ranksInWords); 
			
				returnPage="user.html"; 
			}
			
			else {
				
				returnPage="failedLogin.html"; 
			}
		}
		
		//If the user step back from other pages, the application examine the cookie, that containing the userId
		else if ( (cookie_userId!=null) ) {
			
			int id=Integer.parseInt(cookie_userId); 
			
			user=db.getUserById(id); 
			user.fillTheRanksToWordList(); 
			ranksInWords=user.getRanksToWord(); 
			
			model.addAttribute("user", user); 
			model.addAttribute("categoryList", categories); 
			model.addAttribute("ranksList", ranksInWords); 
			
			returnPage="user.html"; 
			
			db.close(); 
			
		}

		return returnPage;
	}
	
}
