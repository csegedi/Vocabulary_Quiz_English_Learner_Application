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
	
	@Transient
	private int wrongAnswersCounter;
	
	@Transient
	private int lives; 
	
	@Column (name="attempts")
	private int attempts; 
	
	@Column (name="wins")
	private int wins;
	
	@Column (name="lost")
	private int lost; 
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable (
			
			name="mistakenwords",
			joinColumns= @JoinColumn (name="user_id"),
			inverseJoinColumns=@JoinColumn (name="word_id")
			)
	
	private List<Word> listOfTheMistakenWords;
	

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
		this.wrongAnswersCounter = 0;
		this.lives = 3;
		this.attempts = 0;
		this.wins = 0;
		this.lost = 0;
		this.listOfTheMistakenWords = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhrasalVerbPoints() {
		return phrasalVerbPoints;
	}

	public void setPhrasalVerbPoints(int phrasalVerbPoints) {
		this.phrasalVerbPoints = phrasalVerbPoints;
	}

	public int getCollocationsPoints() {
		return collocationsPoints;
	}

	public void setCollocationsPoints(int collocationsPoints) {
		this.collocationsPoints = collocationsPoints;
	}

	public int getNounsPoints() {
		return nounsPoints;
	}

	public void setNounsPoints(int nounsPoints) {
		this.nounsPoints = nounsPoints;
	}

	public int getAdjectivesPoints() {
		return adjectivesPoints;
	}

	public void setAdjectivesPoints(int adjectivesPoints) {
		this.adjectivesPoints = adjectivesPoints;
	}

	public int getSentencesPoints() {
		return sentencesPoints;
	}

	public void setSentencesPoints(int sentencesPoints) {
		this.sentencesPoints = sentencesPoints;
	}

	public int getAdverbsPoints() {
		return adverbsPoints;
	}

	public void setAdverbsPoints(int adverbsPoints) {
		this.adverbsPoints = adverbsPoints;
	}

	public int getInformaticVocabularyPoints() {
		return informaticVocabularyPoints;
	}

	public void setInformaticVocabularyPoints(int informaticVocabularyPoints) {
		this.informaticVocabularyPoints = informaticVocabularyPoints;
	}

	public int getRightAnswersCounter() {
		return rightAnswersCounter;
	}

	public void setRightAnswersCounter(int rightAnswersCounter) {
		this.rightAnswersCounter = rightAnswersCounter;
	}

	public int getWrongAnswersCounter() {
		return wrongAnswersCounter;
	}

	public void setWrongAnswersCounter(int wrongAnswersCounter) {
		this.wrongAnswersCounter = wrongAnswersCounter;
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

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLost() {
		return lost;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public List<Word> getListOfTheMistakenWords() {
		return listOfTheMistakenWords;
	}

	public void setListOfTheMistakenWords(List<Word> listOfTheMistakenWords) {
		this.listOfTheMistakenWords = listOfTheMistakenWords;
	} 
	

}
