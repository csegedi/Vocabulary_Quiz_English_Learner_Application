package Project.Vocabulary.Quiz_MVC.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table (name="user")
public class User {
	
	@Transient
	private final int initQuestionCounter=10; 
	
	@Transient
	private final int initNumberOfLives=3; 
	
	@Id
	@Column (name="id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column (name="username")
	private String username; 
	
	@Column (name="password")
	private String password; 
	
	@Column (name="phrasalVerbsPoints")
	private int phrasalVerbPoints; 
	
	@Column (name="collocationsPoints")
	private int collocationsPoints; 
	
	@Column (name="nounsPoints")
	private int nounsPoints; 
	
	@Column (name="adjectivesPoints")
	private int adjectivesPoints; 
	
	@Column (name="sentencesPoints")
	private int sentencesPoints; 
	
	@Column (name="adverbsPoints")
	private int adverbsPoints; 
	
	@Column (name="informaticVocabularyPoints")
	private int informaticVocabularyPoints; 
	
	@Transient
	private int rightAnswersCounter; 
	
	@Column (name="lives")
	private int lives=3;  
	
	@Column (name="attempts")
	private int attempts; 
	
	@Column (name="wins")
	private int wins;
	
	@Column (name="lost")
	private int lost; 
	
	@Column (name="actualQuestions")
	private int actualQuestions;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable (
			
			name="mistakenwords",
			joinColumns= @JoinColumn (name="user_id"),
			inverseJoinColumns=@JoinColumn (name="word_id")
			)
	
	private List<Word> listOfTheMistakenWords;

	public User() {
		
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.phrasalVerbPoints = 0;
		this.collocationsPoints = 0;
		this.nounsPoints = 0;
		this.adjectivesPoints = 0;
		this.sentencesPoints = 0;
		this.adverbsPoints = 0;
		this.informaticVocabularyPoints = 0;
		this.rightAnswersCounter = 0;
		this.actualQuestions=0; 
		this.attempts = 0;
		this.wins = 0;
		this.lost = 0;
		this.lives=initNumberOfLives; 
		this.actualQuestions=initQuestionCounter; 
		this.listOfTheMistakenWords = null;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}

	public int getPhrasalVerbPoints() {
		return phrasalVerbPoints;
	}

	public int getCollocationsPoints() {
		return collocationsPoints;
	}

	public int getNounsPoints() {
		return nounsPoints;
	}

	public int getAdjectivesPoints() {
		return adjectivesPoints;
	}

	public int getSentencesPoints() {
		return sentencesPoints;
	}

	public int getAdverbsPoints() {
		return adverbsPoints;
	}

	public int getInformaticVocabularyPoints() {
		return informaticVocabularyPoints;
	}

	public int getRightAnswersCounter() {
		return rightAnswersCounter;
	}

	public int getActualQuestions() {
		return actualQuestions;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getAttempts() {
		return attempts;
	}

	public int getWins() {
		return wins;
	}

	public int getLost() {
		return lost;
	}
	
	

	public void setRightAnswersCounter(int rightAnswersCounter) {
		this.rightAnswersCounter = rightAnswersCounter;
	}

	public void setActualQuestions(int actualQuestions) {
		this.actualQuestions = actualQuestions;
	}
	
	

	public List<Word> getListOfTheMistakenWords() {
		return listOfTheMistakenWords;
	}
	
	
	/**METHODS*/

	public void attemptsIncrease() {
		
		this.attempts++; 

	}
	
	public void rightAnswerIncrease() {
		
		this.rightAnswersCounter++; 
	}


	public void pointsIncrease(int categoryId) {
		
		if (categoryId==1) {
			 this.phrasalVerbPoints++;
		}
		else if (categoryId==2) {
			this.collocationsPoints++; 
		}
		else if (categoryId==3) {
			this.nounsPoints++;
		}
		else if (categoryId==4) {
			this.adjectivesPoints++; 
		}
		else if (categoryId==5) {
			this.sentencesPoints++; 
		}
		else if (categoryId==6) {
			this.adverbsPoints++; 
		}
		else if (categoryId==7) {
			this.informaticVocabularyPoints++; 
		}
		
	}

	public void LivesDecrease() {
		
		this.lives--; 
		
	}

	public void actualQuestionNumbersDecrease() {
		
		this.actualQuestions--; 
		
	}

	public void FailedQuizUpdate() {
		
		attemptsIncrease(); 
		this.actualQuestions=initQuestionCounter; 
		this.setRightAnswersCounter(0);
		this.setLives(initNumberOfLives);
		this.lost++; 
		
	}

	public void WinnerQuizUpdate( int id) {
		
		pointsIncrease (id); 
		attemptsIncrease(); 
		this.wins++; 
		this.actualQuestions=initQuestionCounter; 
		this.setLives(initNumberOfLives);
		this.setRightAnswersCounter(0);
		
	}

}
