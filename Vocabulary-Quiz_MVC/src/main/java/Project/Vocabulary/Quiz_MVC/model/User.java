package Project.Vocabulary.Quiz_MVC.model;

import java.util.ArrayList;
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
	private final int goldRank=10;
	
	@Transient
	private final int difficultyRank=30; 
	
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
	
	@Column (name="winsEasy")
	private int winsEasy;
	
	@Column (name="winsMedium")
	private int winsMedium;
	
	@Column (name="winsHard")
	private int winsHard;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable (
			
			name="mistakenwords",
			joinColumns= @JoinColumn (name="user_id"),
			inverseJoinColumns=@JoinColumn (name="word_id")
			)
	
	private List<Word> listOfTheMistakenWords;
	
	@Column (name="phrasalVerbs_Gold")
	private boolean phrasalVerbs_Gold; 
	
	@Column (name="collocations_Gold")
	private boolean collocations_Gold;
	
	@Column (name="nouns_Gold")
	private boolean nouns_Gold;
	
	@Column (name="adjectives_Gold")
	private boolean adjectives_Gold;
	
	@Column (name="sentences_Gold")
	private boolean sentences_Gold;
	
	@Column (name="adverbs_Gold")
	private boolean adverbs_Gold;
	
	@Column (name="informaticVocabulary_Gold")
	private boolean informaticVocabulary_Gold;
	
	@Column (name="noble")
	private boolean noble;
	
	@Column (name="baron")
	private boolean baron;
	
	@Column (name="king")
	private boolean king;
	
	@Column (name="demigod")
	private boolean demigod;
	
	@Transient
	ArrayList<String> ranksToWord; 

	public User() {
		this.ranksToWord=new ArrayList<String>(); 
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
		this.winsEasy=0;
		this.winsMedium=0;
		this.winsHard=0; 
		this.lives=initNumberOfLives; 
		this.actualQuestions=initQuestionCounter; 
		this.listOfTheMistakenWords = null;
	}
	
	/**GETTERS AND SETTERS*/

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}

	public int getActualQuestions() {
		return actualQuestions;
	}

	public int getLives() {
		return lives;
	}
	
	
	public int getInitQuestionCounter() {
		return initQuestionCounter;
	}

	public int getGoldRank() {
		return goldRank;
	}

	public int getDifficultyRank() {
		return difficultyRank;
	}

	public int getInitNumberOfLives() {
		return initNumberOfLives;
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

	public int getWinsEasy() {
		return winsEasy;
	}

	public int getWinsMedium() {
		return winsMedium;
	}

	public int getWinsHard() {
		return winsHard;
	}

	public boolean isPhrasalVerbs_Gold() {
		return phrasalVerbs_Gold;
	}

	public boolean isCollocations_Gold() {
		return collocations_Gold;
	}

	public boolean isNouns_Gold() {
		return nouns_Gold;
	}

	public boolean isAdjectives_Gold() {
		return adjectives_Gold;
	}

	public boolean isSentences_Gold() {
		return sentences_Gold;
	}

	public boolean isAdverbs_Gold() {
		return adverbs_Gold;
	}

	public boolean isInformaticVocabulary_Gold() {
		return informaticVocabulary_Gold;
	}

	public boolean isNoble() {
		return noble;
	}

	public boolean isBaron() {
		return baron;
	}

	public boolean isKing() {
		return king;
	}

	public boolean isDemigod() {
		return demigod;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setLost(int lost) {
		this.lost = lost;
	}

	public void setListOfTheMistakenWords(List<Word> listOfTheMistakenWords) {
		this.listOfTheMistakenWords = listOfTheMistakenWords;
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
	
	
	public ArrayList<String> getRanksToWord() {
		return ranksToWord;
	}

	/**METHODS*/
	
	public void fillTheRanksToWordList() {
		
		if (phrasalVerbs_Gold==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No"); 
		}
		if (collocations_Gold==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No");
		}
		if (nouns_Gold==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No");
		}
		if(adjectives_Gold==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No");
		}
		if (sentences_Gold==true) {
			ranksToWord.add("Yes"); 
		}
		else {
			ranksToWord.add("No"); 
		}
		if (adverbs_Gold==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No");
		}
		if (informaticVocabulary_Gold==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No"); 
		}
		if (noble==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No");
		}
		if(baron==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No");
		}
		if (king==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No"); 
		}
		if (demigod==true) {
			ranksToWord.add("Yes");
		}
		else {
			ranksToWord.add("No"); 
		}
	}


	public void attemptsIncrease() {
		
		this.attempts++; 

	}
	
	public void rightAnswerIncrease() {
		
		this.rightAnswersCounter++; 
	}
	
	
	private void setRanks() {
		
		if (phrasalVerbPoints==goldRank) {
			
			phrasalVerbs_Gold=true; 
		}
		
		if (collocationsPoints==goldRank) {
			
			collocations_Gold=true; 
		}
		
		if (nounsPoints==goldRank) {
			
			nouns_Gold=true; 
		}
		
		if (adjectivesPoints==goldRank) {
			
			adjectives_Gold=true; 
		}
		
		if (sentencesPoints==goldRank) {
			
			sentences_Gold=true; 
		}
		
		if (adverbsPoints==goldRank) {
			
			adverbs_Gold=true; 
		}
		
		if (informaticVocabularyPoints==goldRank) {
			
			informaticVocabulary_Gold=true; 
		}
		
		if (winsEasy==difficultyRank) {
			
			noble=true; 
		}
		if (winsMedium==goldRank) {
			
			baron=true; 
		}
		
		if (winsHard==goldRank) {
			
			king=true; 
		}
		if (phrasalVerbs_Gold && collocations_Gold && nouns_Gold && adjectives_Gold 
				&& sentences_Gold && adverbs_Gold && informaticVocabulary_Gold &&
				noble && baron && king) {
			demigod=true; 
		}

	}
		private void difficultyRankIncrease(String difficultyLevel) {
		
		if (difficultyLevel.equals(Difficulty_Level.EASY.toString())) {
			winsEasy++;
		}
		else if(difficultyLevel.equals(Difficulty_Level.MEDIUM.toString())) {
			winsMedium++;
		}
		else {
			winsHard++; 
		}
		
	}

	private void pointsIncrease(int categoryId) {
		
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

	public void WinnerQuizUpdate( int id, String difficulty) {
		
		difficultyRankIncrease (difficulty); 
		pointsIncrease (id); 
		attemptsIncrease(); 
		this.wins++; 
		this.actualQuestions=initQuestionCounter; 
		this.setLives(initNumberOfLives);
		this.setRightAnswersCounter(0);
		setRanks(); 
		
	}

}
