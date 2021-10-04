package Project.Vocabulary.Quiz_MVC.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
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
							@CookieValue (required=false,  name="cookie_username") String cookie_username,
							@CookieValue (required = false, name="cookie_password") String cookie_password,
							HttpServletResponse response) {
		
		Database db=new Database(); 
		String returnPage=null; 
		User user=null; 
		List <Category> categories=db.getAllCategory(); 
		
		if ( (username!=null) && (password!=null) ) {
			
			List <User> users=db.getUserByNameAndPassword(username, password); 
			
			if (users.size()==1) {
				
				user=users.get(0); 
				
				Cookie cookie1=new Cookie("cookie_username", cookie_username); 
				Cookie cookie2=new Cookie ("cookie_password", cookie_password); 
				response.addCookie(cookie1); 
				response.addCookie(cookie2); 
				
				model.addAttribute(user); 
				
				model.addAttribute("user", user); 
				model.addAttribute("categoryList", categories); 
			
				returnPage="user.html"; 
			}
			
			else {
				
				returnPage="failedLogin.html"; 
			}
		}
		
		else if ( (cookie_username!=null) && (cookie_password!=null) ) {
			
			List <User> users=db.getUserByNameAndPassword(cookie_username, cookie_password);
			user=users.get(0); 
			
			model.addAttribute("user", user); 
			model.addAttribute("categoryList", categories); 
			
			returnPage="user.html"; 
			
		}

		return returnPage;
	}
	
	@PostMapping("/user/settings")
	public String initSettings(Model model) {
		
		Database db=new Database(); 
		List<Category> categories=db.getAllCategory(); 
		
		
		model.addAttribute("categoryList", categories); 
		
		return "initSettings.html";
		
	}
	
	@PostMapping ("/user/quiz")
	public String quiz(Model model,
			@RequestParam (name="selectedCategory") Integer categoryId,
			@RequestParam (name="difficulty") String difficultyLevel,
			@RequestParam (name="init") String initLanguage,
			HttpServletResponse response) {
		
		Cookie cookie4=new Cookie ("time", "x"); 
		if (difficultyLevel.equals(Difficulty_Level.EASY.toString())) {
			cookie4.setMaxAge(20); 
		}
		else if(difficultyLevel.equals(Difficulty_Level.MEDIUM.toString()) ) {
			cookie4.setMaxAge(12); 
		}
		else if (difficultyLevel.equals(Difficulty_Level.HARD.toString())) {
			cookie4.setMaxAge(8); 
		}
		response.addCookie(cookie4);
		
		
		Database db=new Database(); 
		String wordToQuiz=null;
		int maxRandomNumber=0; 
		
		if ( (categoryId!=null) && (difficultyLevel!=null) && (initLanguage!=null) ) {
			
			String formattedInt=categoryId.toString(); 
			Cookie cookie1=new Cookie ("cookie_categoryId", formattedInt);
			Cookie cookie2=new Cookie ("cookie_difficultyLevel", difficultyLevel); 
			Cookie cookie3=new Cookie ("cookie_init", initLanguage);
			
			response.addCookie(cookie1); 
			response.addCookie(cookie2); 
			response.addCookie(cookie3); 
			 //itt tartok
			
		
			List<Word>words=db.getTheWordByCategoryId(categoryId); 
		
			maxRandomNumber=words.size(); 
			Word word=words.get(new Random().nextInt(maxRandomNumber)); 
		
				if (initLanguage.equals(Language.ENGLISH.toString())) {
					wordToQuiz=word.getEnglish(); 	
					}
		
				else {
					wordToQuiz=word.getHungarian(); 
				}
			}
		
		model.addAttribute("quizWord", wordToQuiz); 
		model.addAttribute("diff", difficultyLevel); 
		model.addAttribute("init", initLanguage); 
		model.addAttribute("category",categoryId); 
		
	
		
		return "quiz.html";
		
	}
	
}
