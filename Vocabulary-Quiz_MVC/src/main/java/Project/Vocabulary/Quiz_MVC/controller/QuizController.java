package Project.Vocabulary.Quiz_MVC.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Project.Vocabulary.Quiz_MVC.db.Database;
import Project.Vocabulary.Quiz_MVC.model.Category;
import Project.Vocabulary.Quiz_MVC.model.Difficulty_Level;
import Project.Vocabulary.Quiz_MVC.model.Language;
import Project.Vocabulary.Quiz_MVC.model.User;
import Project.Vocabulary.Quiz_MVC.model.Word;

@Controller
public class QuizController {

	/** QUIZ SETTINGS */

	// I: Setting the difficulty level, the quiz:

	@PostMapping("/user/settings")
	public String initSettings(Model model) {

		Database db = new Database();
		List<Category> categories = db.getAllCategory();

		model.addAttribute("categoryList", categories);
		
		db.close(); 

		return "initSettings.html";

	}

	@PostMapping("/user/quiz")
	public String quiz(Model model,
			@RequestParam(required = false, name = "selectedCategory") Integer categoryId,
			@RequestParam(required = false, name = "init") String initLanguage,
			@RequestParam(required = false, name = "difficulty") String difficulty_Level,
			@CookieValue(required = false, name = "cookie_init") String cookie_initLangue,
			@CookieValue(required = false, name = "cookie_categoryId") String cookie_categoryId,
			@CookieValue(required = false, name = "cookie_difficulty_Level") String cookie_difficulty_Level,
			HttpServletResponse response, HttpServletRequest request) {

		Database db = new Database();
		String returnPage = null;
		Integer categoryInteger = null;
		String wordToQuiz = null;
		int maxRandomNumber = 0;

		if (categoryId != null) {
			categoryInteger = categoryId;

			String formattedInt = categoryId.toString();
			Cookie cookie1 = new Cookie("cookie_categoryId", formattedInt);
			Cookie cookie2 = new Cookie("cookie_init", initLanguage);
			Cookie cookie3 = new Cookie("cookie_difficulty_Level", difficulty_Level);
			response.addCookie(cookie1);
			response.addCookie(cookie2);
			response.addCookie(cookie3);
		}

		else {

			int formatted_CookieCatId = Integer.parseInt(cookie_categoryId);
			categoryInteger = formatted_CookieCatId;
		}

		Cookie cookieTime = new Cookie("timer", "x");

		// The period of the cookie ("time) will define the difficulty level:

		if ((difficulty_Level != null && difficulty_Level.equals(Difficulty_Level.EASY.toString()))
				|| (cookie_difficulty_Level != null
						&& cookie_difficulty_Level.equals(Difficulty_Level.EASY.toString()))) {
			cookieTime.setMaxAge(15);
			response.addCookie(cookieTime);
		} else if ((difficulty_Level != null && difficulty_Level.equals(Difficulty_Level.MEDIUM.toString()))
				|| (cookie_difficulty_Level != null
						&& cookie_difficulty_Level.equals(Difficulty_Level.MEDIUM.toString()))) {
			cookieTime.setMaxAge(12);
			response.addCookie(cookieTime);
		} else if (((difficulty_Level != null && difficulty_Level.equals(Difficulty_Level.HARD.toString()))
				|| (cookie_difficulty_Level != null
						&& cookie_difficulty_Level.equals(Difficulty_Level.HARD.toString())))) {
			cookieTime.setMaxAge(10);
			response.addCookie(cookieTime);
		}

		List<Word> words = db.getTheWordByCategoryId(categoryInteger);

		maxRandomNumber = words.size();
		Word word = words.get(new Random().nextInt(maxRandomNumber));
		Integer wordId = word.getId();
		String formatted_CookieWordId = wordId.toString();

		Cookie cookie3 = new Cookie("cookie_WordId", formatted_CookieWordId);
		response.addCookie(cookie3);

		if (((initLanguage != null && initLanguage.equals(Language.ENGLISH.toString()))
				|| (cookie_initLangue != null && cookie_initLangue.equals(Language.ENGLISH.toString())))) {

			wordToQuiz = word.getEnglish();

		}

		else {
			wordToQuiz = word.getHungarian();
		}

		model.addAttribute("quizWord", wordToQuiz);
		model.addAttribute("init", initLanguage);
		model.addAttribute("category", categoryId);

		returnPage = "quiz.html";

		db.close();

		return returnPage;
	}

	@PostMapping("/user/quiz/solution")
	public String solution(Model model,
		@CookieValue(required = false, name = "cookie_userId") String cookie_userId,
		@CookieValue(required = false, name = "cookie_WordId") String cookie_WordId,
		@CookieValue(required = false, name = "cookie_categoryId") String cookie_categoryId,
		@RequestParam(name = "answer") String answer,
		@RequestParam(name = "wordFromQuiz") String wordFromQuiz,
		HttpServletRequest request,
		HttpServletResponse response) {

		Database db = new Database();
		String returnPage = null;
		String text = null;

		int categoryId = Integer.parseInt(cookie_categoryId);
		List<Category> categories = db.getAllCategory();

		int userId = Integer.parseInt(cookie_userId);
		User user = db.getUserById(userId);

		int wordId = Integer.parseInt(cookie_WordId);
		Word word = db.getTheWordById(wordId);

		Cookie[] cookies = request.getCookies();
		Cookie incomingTimer = null;

		if (cookies != null) {

			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getValue().equals("x")) {
					incomingTimer = cookies[i];
				}
			}
		}

		if (incomingTimer != null) {
			// checking if the starter language is the English
			if (word.getEnglish().equals(wordFromQuiz)) {
				if (word.getHungarian().contains(answer) && answer.isEmpty() == false) {

					user.rightAnswerIncrease();
					text = "Correct!";

					model.addAttribute("word", word);
					model.addAttribute("answer", answer);
					model.addAttribute("text", text);

					returnPage = "answer.html";
				}

				else {

					user.LivesDecrease();
					db.insertMistakenWord(user.getId(), word.getId());
					text = "Incorrect answer!";

					model.addAttribute("word", word);
					model.addAttribute("answer", answer);
					model.addAttribute("text", text);

					returnPage = "answer.html";
				}
			}

			// checking if the starter language is the Hungarian
			else if (word.getHungarian().equals(wordFromQuiz)) {

				if (word.getEnglish().contains(answer) && answer.isEmpty() == false) {

					user.rightAnswerIncrease();
					text = "Correct!";

					model.addAttribute("word", word);
					model.addAttribute("answer", answer);
					model.addAttribute("text", text);

					returnPage = "answer.html";
				}

				else {

					db.insertMistakenWord(user.getId(), word.getId());
					user.LivesDecrease();
					text = "Incorrect answer!";

					model.addAttribute("word", word);
					model.addAttribute("answer", answer);
					model.addAttribute("text", text);

					returnPage = "answer.html";
				}

			}

		} 
		
		else {

			db.insertMistakenWord(user.getId(), word.getId());
			user.LivesDecrease();
			text = "Your time is out!!";

			model.addAttribute("word", word);
			model.addAttribute("answer", answer);
			model.addAttribute("text", text);
			
			returnPage = "answer.html";
		}

		user.actualQuestionNumbersDecrease();

		if ((user.getActualQuestions() == 0) && (user.getLives() > 0)) {

			String victory = "You have completed the quiz!";
			
			user.WinnerQuizUpdate(categoryId); 

			model.addAttribute("text", victory);
			returnPage = "quizCompleted.html";

		}

		else if (user.getLives() == 0) {

			String lost = "You have failed the quiz!";
			
			user.FailedQuizUpdate(); 

			model.addAttribute("text", lost);
			returnPage = "quizCompleted.html";
		}

		db.updateUser(user);

		db.close();

		return returnPage;

	}

}
