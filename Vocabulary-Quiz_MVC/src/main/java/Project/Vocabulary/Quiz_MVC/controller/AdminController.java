package Project.Vocabulary.Quiz_MVC.controller;

import java.util.ArrayList;
import java.util.List;

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
import Project.Vocabulary.Quiz_MVC.model.Admin;
import Project.Vocabulary.Quiz_MVC.model.Category;
import Project.Vocabulary.Quiz_MVC.model.Word;

@Controller
public class AdminController {
	
	
	@GetMapping ("/")
	public String index (Model model, HttpServletRequest request, HttpServletResponse response) {
		
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		
		/**INITIAL INFORMATIONS OF THE MAIN PAGE*/
		
		Database db=new Database(); 
		
		List<Category> categories=db.getAllCategory(); 
		ArrayList<Integer> piecesOfWords=new ArrayList<Integer>(); 
		
		int vocabularySize=0; 
		
		//collecting of the size of the categories
	
		for (int i=1; i<=categories.size(); i++) {
			int assistant=0; 
			List<Word> words=db.getTheWordByCategoryId(i); 
			assistant=words.size();
			piecesOfWords.add(assistant); 	
			
			}
		
		//query the size of the vocabulary
		
		for (int piecesOfWordsIndex=0; piecesOfWordsIndex<piecesOfWords.size(); piecesOfWordsIndex++) {
			vocabularySize+=piecesOfWords.get(piecesOfWordsIndex); 
		}
		
		model.addAttribute("categoryList", categories); 
		model.addAttribute("numberOftheWords", piecesOfWords);
		model.addAttribute("allWords", vocabularySize); 
	
		db.close(); 
		
		return "index.html";
		
	}
	
	/**ADMIN LOGIN*/
	
	@GetMapping("/adminLogin")
	public String adminLogin() {

		return "adminLogin.html";
		
	}
	
	@PostMapping ("/admin")
	public String admin(Model model,
						@RequestParam (required = false, name="username") String username,
						@RequestParam (required = false, name="password") String password,
						@CookieValue (required = false, name="cookieUsernameAdmin") String cookieUsername,
						@CookieValue (required = false, name="cookiePasswordAdmin") String cookiePassword,
						HttpServletResponse response) {
		
		Database db=new Database(); 
		String returnPage=null; 
		
		//Check the admin in the database:
		
		//First enter: checking the requested parameters
		if( (username!=null) && (password!=null)  ) {
			
			List <Admin> admins=db.getTheAdmin(username, password); 
			
				if (admins.isEmpty()==false) {
			
					List<Category> categories=db.getAllCategory(); 
					
					Cookie cokkie1=new Cookie("cookieUsernameAdmin", username);
					Cookie cookie2=new Cookie ("cookiePasswordAdmin", password); 
					
					response.addCookie(cokkie1); 
					response.addCookie(cookie2); 
					
					model.addAttribute("categoryList", categories); 
			
					returnPage="admin.html"; 
				}
		
				else {
			
					returnPage="redirect:/adminLogin"; 
				}
		}
		
		//If the user step back from another page, checking the cookies
		else {
			
			if ( (cookieUsername!=null) && (cookiePassword!=null) ) {
				
				List<Category> categories=db.getAllCategory(); 
				
				model.addAttribute("categoryList", categories); 
				
				returnPage="admin.html";
			}
			
			else {
				
				returnPage="redirect:/adminLogin"; 
			}
			
		}
		
		db.close(); 
		
		return returnPage;	
	}
	
	/**INSERT NEW WORD TO THE DATABASE*/
	
	@PostMapping("/admin/insert")
	public String insertNewWord(Model model,
								@RequestParam (required = false, name="english") String english,
								@RequestParam (required = false, name="hungarian") String hungarian,
								@RequestParam (required = false, name="example") String example,
								@RequestParam (required = false, name="selectedCategory") Integer categoryId) {
		
		Database db=new Database(); 
		Word word=new Word(); 
		word.setEnglish(english); 
		word.setHungarian(hungarian); 
		word.setExample(example); 
	
		db.insertNewWord(word.getEnglish(), word.getHungarian(), word.getExample(), categoryId);  
		
		model.addAttribute("word", word); 
		
		return "newWord.html";
		
	}
	
	/**CORRECTING OR UPDATE THE EARLIER GIVEN WORDS AND EXPRESSIONS*/
	
	//Select a category
	@PostMapping ("/admin/change")
	public String updateSelect(Model model,
			@RequestParam (name="selectedCategory") int categoryId) {
		
		Database db=new Database(); 
		
		List<Word>words=db.getTheWordByCategoryId(categoryId); 
		
		model.addAttribute("wordsList", words); 
		model.addAttribute("category_id", categoryId); 
		
		return "update.html";
		
	}
	
	//Select a word and insert a modified value
	@PostMapping("/admin/change/selected")
	public String updateSelectedWord (Model model, 
			@RequestParam (name="wordId") int wordId,
			@RequestParam (name="selectedCategory") int categoryId) {
		
		Database db=new Database(); 
		Word word=db.getTheWordById(wordId); 
		String returnPage=null; 
		List<Word> words=db.getTheWordByCategoryId(categoryId);
		boolean categorySign=false;
		
		for (int wordsIndex=0; wordsIndex<words.size(); wordsIndex++) {
			Word current=words.get(wordsIndex); 
			if ( current.getId()==wordId) {
				categorySign=true; 
			}
		}
		
		
		if ( (word!=null) && categorySign==true ) {
			
			model.addAttribute("word", word); 
			model.addAttribute("selectedCategory", categoryId);
			returnPage="insertModifications.html"; 
		}
		
		else {
			
			returnPage="wrongInput.html"; 
			
		}

		return returnPage;
		
	}
	
	//Update the word in the database
	@PostMapping ("/admin/change/selected/completed")
	public String updateSelectedWordExecut(Model model,
			@RequestParam (name="wordId") Integer wordId,
			@RequestParam (required = false, name="newEnglish") String newEnglish,
			@RequestParam (required = false, name="newHungarian") String newHungarian,
			@RequestParam (required = false, name="newExample") String newExample
			) {
		
		Database db=new Database(); 
		Word word=db.getTheWordById(wordId);
	
		if (newEnglish.isEmpty()!=true) {
			word.setEnglish(newEnglish);  
		}
		
		if (newHungarian.isEmpty()!=true) {
			word.setHungarian(newHungarian); 
		
		}
		
		if(newExample.isEmpty()!=true) {
			word.setExample(newExample);
		}
		
		db.updateWord(word); 
		
		model.addAttribute("updatedWord", word); 
		
		return "completedUpdate.html";
		
	}
	
}
