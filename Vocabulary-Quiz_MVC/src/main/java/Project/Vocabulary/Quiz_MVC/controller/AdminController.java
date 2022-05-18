package Project.Vocabulary.Quiz_MVC.controller;

import java.util.ArrayList;
import java.util.Comparator;
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

	@GetMapping("/")
	public String index(Model model, 
		HttpServletRequest request, 
		HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}

		return "index.html";

	}
	
	/** ADMIN LOGIN */

	@GetMapping("/adminLogin")
	public String adminLogin() {

		return "adminLogin.html";

	}

	@PostMapping("/admin")
	public String admin(Model model, 
			@RequestParam(required = false, name = "username") String username,
			@RequestParam(required = false, name = "password") String password,
			@CookieValue(required = false, name = "cookieUsernameAdmin") String cookieUsername,
			@CookieValue(required = false, name = "cookiePasswordAdmin") String cookiePassword,
			HttpServletResponse response) {

		Database db = new Database();
		String returnPage = null;

		// Check the admin in the database:

		// First enter: checking the requested parameters
		if ((username != null) && (password != null)) {

			List<Admin> admins = db.getTheAdmin(username, password);

			if (admins.isEmpty() == false) {

				List<Category> categories = db.getAllCategory();

				Cookie cokkie1 = new Cookie("cookieUsernameAdmin", username);
				Cookie cookie2 = new Cookie("cookiePasswordAdmin", password);

				response.addCookie(cokkie1);
				response.addCookie(cookie2);

				model.addAttribute("categoryList", categories);

				returnPage = "admin.html";
			}

			else {

				returnPage = "redirect:/adminLogin";
			}
		}

		// If the user step back from another page, checking the cookies
		else {

			if ((cookieUsername != null) && (cookiePassword != null)) {

				List<Category> categories = db.getAllCategory();

				model.addAttribute("categoryList", categories);

				returnPage = "admin.html";
			}

			else {

				returnPage = "redirect:/adminLogin";
			}

		}

		db.close();

		return returnPage;
	}

	/** INSERT NEW WORD TO THE DATABASE */

	@PostMapping("/admin/insert")
	public String insertNewWord(Model model, 
			@RequestParam(required = false, name = "english") String english,
			@RequestParam(required = false, name = "hungarian") String hungarian,
			@RequestParam(required = false, name = "example") String example,
			@RequestParam(required = false, name = "selectedCategory") Integer categoryId) {

		Database db = new Database();
		Word word = new Word();
		word.setEnglish(english);
		word.setHungarian(hungarian);
		word.setExample(example);
		String returnPage=null; 
		
		if ((english.isBlank()) || (hungarian.isBlank()) || (example.isBlank() )) {
			
			returnPage="wrongInput.html"; 
		}
		else {
		
		db.insertNewWord(word.getEnglish(), word.getHungarian(), word.getExample(), categoryId);
		model.addAttribute("word", word);
		returnPage="newWord.html"; 
		}

		return returnPage;
	}

	/** CORRECTING OR UPDATE THE EARLIER GIVEN WORDS AND EXPRESSIONS */

	// Select a category
	@PostMapping("/admin/change")
	public String updateSelect(Model model,
	@RequestParam(name = "selectedCategory") Integer categoryId) {

		Database db = new Database();

		List<Word> words = db.getTheWordByCategoryId(categoryId);
		words.sort(Comparator.comparing(Word::getEnglish));

		model.addAttribute("wordsList", words);
		model.addAttribute("selectedCategory", categoryId);

		db.close();

		return "update.html";

	}
	// Select a word and insert a modified value
	@PostMapping("/admin/change/selected")
	public String updateSelectedWord(Model model, 
			@RequestParam( required = false, name= "wordId") Integer wordId,
			@RequestParam(name = "selectedCategory") int categoryId) {

		Database db = new Database();
		String returnPage = null;
		Word word=null; 
	
		if (wordId!=null) {
			
			List<Word> words = db.getTheWordByIdandCategoryId(wordId, categoryId); 
		
			if (words.size()!=0) {
			word=words.get(0); 
			
			model.addAttribute("word", word);
			model.addAttribute("selectedCategory", categoryId);
			returnPage = "insertModifications.html";
			}
			else {
			returnPage="wrongInput.html"; 
			}
		
		}

		else {
			returnPage="wrongInput.html";
		}
			

		db.close();

		return returnPage;

	}

	// Update the word in the database
	@PostMapping("/admin/change/selected/completed")
	public String updateSelectedWordExecut(Model model, 
			@RequestParam(required=false, name = "wordId") Integer wordId,
			@RequestParam(required = false, name = "newEnglish") String newEnglish,
			@RequestParam(required = false, name = "newHungarian") String newHungarian,
			@RequestParam(required = false, name = "newExample") String newExample) {

		Database db = new Database();
		Word word = db.getTheWordById(wordId);
		String returnPage=null; 
		
		if (word==null) {
			
			returnPage="wrongInput.html"; 
		}
		
		else {
			
		if (newEnglish.isEmpty() != true) {
			word.setEnglish(newEnglish);
		}

		if (newHungarian.isEmpty() != true) {
			word.setHungarian(newHungarian);

		}

		if (newExample.isEmpty() != true) {
			word.setExample(newExample);
		}

		db.updateWord(word);

		model.addAttribute("updatedWord", word);
		returnPage="completedUpdate.html"; 
		}

		db.close();

		return returnPage;

	}
	
	@GetMapping ("/vocabulary")
	public String vocabulary (Model model) {
		
		Database db = new Database();

		List<Category> categories = db.getAllCategory();
		ArrayList<Integer> piecesOfWords = new ArrayList<Integer>();
		int vocabularySize = 0;
		
		for (int i = 1; i <= categories.size(); i++) {
			List<Word> words = db.getTheWordByCategoryId(i);
			piecesOfWords.add(words.size());
		}

		for (int piecesOfWordsIndex = 0; piecesOfWordsIndex < piecesOfWords.size(); piecesOfWordsIndex++) {
			vocabularySize += piecesOfWords.get(piecesOfWordsIndex);
		}
		
		 
		List<Word>phrasalVerbs=db.getTheWordByCategoryId(1); 
		phrasalVerbs.sort(Comparator.comparing(Word::getEnglish));
		List<Word>collocations=db.getTheWordByCategoryId(2); 
		collocations.sort(Comparator.comparing(Word::getEnglish));
		List<Word>nouns=db.getTheWordByCategoryId(3); 
		nouns.sort(Comparator.comparing(Word::getEnglish));
		List<Word>adjectives=db.getTheWordByCategoryId(4); 
		adjectives.sort(Comparator.comparing(Word::getEnglish));
		List<Word>sentences=db.getTheWordByCategoryId(5); 
		sentences.sort(Comparator.comparing(Word::getEnglish));
		List<Word>adverbs=db.getTheWordByCategoryId(6); 
		adverbs.sort(Comparator.comparing(Word::getEnglish));
		List<Word>informatics=db.getTheWordByCategoryId(7); 
		informatics.sort(Comparator.comparing(Word::getEnglish));
	
		model.addAttribute("phENG", phrasalVerbs);
		model.addAttribute("collENG", collocations);
		model.addAttribute("nounsENG", nouns);
		model.addAttribute("adjENG", adjectives);
		model.addAttribute("senENG", sentences);
		model.addAttribute("advENG", adverbs);
		model.addAttribute("infoENG", informatics);
		model.addAttribute("allWords", vocabularySize);
		
		ArrayList<Word>phrasalVerbsHun=new ArrayList<Word>(phrasalVerbs); 
		phrasalVerbsHun.sort(Comparator.comparing(Word::getHungarian));
		ArrayList<Word>collocationsHun=new ArrayList<Word>(collocations); 
		collocationsHun.sort(Comparator.comparing(Word::getHungarian));
		ArrayList<Word>nounsHun=new ArrayList<Word>(nouns); 
		nounsHun.sort(Comparator.comparing(Word::getHungarian));
		ArrayList<Word>adjectivesHun=new ArrayList<Word>(adjectives); 
		adjectivesHun.sort(Comparator.comparing(Word::getHungarian));
		ArrayList<Word>sentencesHun=new ArrayList<Word>(sentences); 
		sentencesHun.sort(Comparator.comparing(Word::getHungarian));
		ArrayList<Word>adverbsHun=new ArrayList<Word>(adverbs); 
		adverbsHun.sort(Comparator.comparing(Word::getHungarian));
		ArrayList<Word>informaticsHun=new ArrayList<Word>(informatics); 
		informaticsHun.sort(Comparator.comparing(Word::getHungarian));
		
		model.addAttribute("phHUN", phrasalVerbsHun);
		model.addAttribute("collHUN", collocationsHun);
		model.addAttribute("nounsHUN", nounsHun);
		model.addAttribute("adjHUN", adjectivesHun);
		model.addAttribute("senHUN", sentencesHun);
		model.addAttribute("advHUN", adverbsHun);
		model.addAttribute("infoHUN", informaticsHun);
		
		return "vocabulary.html"; 
	}
	
	@GetMapping ("/vocabulary/result")
	public String result (Model model, @RequestParam (required = false, name="text") String text) {
		
		Database db=new Database (); 
		List<Word> results=db.getTheWordByText(text); 
		
		model.addAttribute("list", results); 
		
		
		return "searchResult.html"; 
	}

}
