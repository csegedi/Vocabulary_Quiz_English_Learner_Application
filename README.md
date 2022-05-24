# Vocabulary Quiz: English Learner Application (Tudor)

## Short review
 
I have to improve my English, so I have decided to create an application that will help me build a huge vocabulary and provide a platform to practice the collected words and expressions.
This application can allow the users to practice the English language. It contains an enormous vocabulary with example sentences; each word and expression can be found in a database.

The words and expressions are on an advanced level. The users can get at this collection and test their knowledge: this application has a quiz function.
This application has been built on the grounds of the MVC pattern, where the control logic consist of three classes. The AdminController class contains the methods which are able to operate the activities of the administrator. The UserController class is responsible for the registration and login processes of the users. The QuizController class operates all functions of the quiz game. This class is responsible for saving every statistic and result to the database. 

This application uses cookies to track the customers' activity and ensure the quiz game's eligible work.

 
## The Main Page
 
Here can be found some menu items, that guide the visitor to other pages.
The items are the following:

**Registration**: If somebody wants to play the quiz, registration is compulsory. The user must create a username and a password. The user will be saved into the database if the registration is successful. The registration is failed if the database already contains the username; in this case, the user must choose another username.

**Login**: If the users have done the registration, they can enter their page. The application continuously checks the username and the password by login; if one of them is incorrect (can’t be found in the database), the login will be denied. After the successful login, the user is directed to his page. The initial player page contains the following: all relevant information about the current user (for example, how many times the user played, how many points have been collected, the number of successful and failed attempts, etc.). From the user’s page can be started a new quiz. For the mechanism and functions of the quiz, see below.

**Vocabulary**: The whole vocabulary is available for everyone without registration. After selecting the category, every word with example sentences can be read. This page contains a search bar to search for the words. The vocabulary is being updated continuously; the actual stand of the vocabulary ( for example, how many words are contained currently) can be read on the vocabulary page.

**Admin**: See below.

 
## The management of the vocabulary
 
**Admin**: The admin doesn’t have to make a registration process because he is registered in the database from the beginning, but they have to give their username and password to use the function of the administrators’ page. These data can be found in the database; if they are incorrect, the entrance to the administrator’s page will be denied. 

**Insert new words and expressions into the database**: The admin can insert new words and expressions into the database: he has to add the English and the Hungarian meaning of the word and give an example sentence in English. He has to select also the category.

**Update the data (correcting or changing earlier registered data)**: the administrators can update the words (for example, the given information was incorrect and necessary to correct. The administrator has to select a category. The next page will show for the administrator all words (and all other data of the words, so all meaning, id numbers, examples) that belong to the selected category. The administrator on this page can select the word by giving its identical number. Suppose the admin gives a wrong id number (because the words of the given category don’t have it). In that case, the administrator can’t continue the modification process and must give another number.
If the administrator has chosen the word (given the right id number), the word can be modified on another page: here can be read the actual data, and under every data can be given a new value (for example, the administrator has given earlier an English word, but the word has been registered with a spelling mistake, so here can be registered the correction).
So this function provides a possibility to change the values of the database. Therefore the vocabulary can be updated; the mistakes can be overwritten.

 
## The Rules of the quiz
 
 **Members-only!**
 
The quiz game is available only for the registered user. It can be started from the own page of the logged users. If the users push the "Start a new quiz button” they will be directed to the settings of the current game.
The players can configure the following properties:
* Difficulty Level (Easy, Medium, Hard)
* Category Select
* Initial language (English or Hungarian)

**Categories**:

The players can select a category of words. Only the words of the selected category will appear during the quiz. I have defined seven categories for the words, which are the following:
* **phrasal verbs**: a group of words that combine a verb with an adverb or a preposition. Together, these words act as a single verb and take on a whole new meaning that's independent of the meanings of the individual words.
* **collocations**: a combination of closely related words (for example, „take a rest”, have a shover, „pay attention, etc.
* **nouns**: a collection of more complex or abstract nouns
* **adjectives**
* **sentences**: a collection of expressions often used, which consist of more words (for example: „obtain somebody's appointment to a post”. 
* **adverbs**
* **informatic vocabulary**: here, I would like to build a collection of the words used only in informatics and programming, even including the ordinary words and expressions that have special meaning in the above-named professional environment. 

**Difficulty Levels**

The player can set the level of the game’s difficulty. There are in the game three difficulty levels; these are the following:

* **Easy**: the player has 15 seconds to pick the correct answer 
* **Medium**: the player has 12 seconds to find the right an answer
* **Hard**: the player has just 10 seconds

**Initial language**

There are two options: English or Hungarian. If the players choose English, the words that appear on the screen during the quiz are English. In this case, the players must find the Hungarian meaning of the currently appearing word from the four possible solutions. If the players choose Hungarian, they must select the correct English meaning of the received Word.

**The Quiz/The Game**

If the players have selected the required properties of the quiz, they can start the current game. In the current version of this application, the player gets ten words. The appearance of the words from the selected category is coincidental. If the user has selected the English as the initial language, the word that appears on the screen will be English, and the player must choose the Hungarian meaning of the received word; if the Hungarian language is the selected as initial language, so the player must find the English meaning. 

The player gets four possible answers; one of them is the correct meaning of the word appearing. 

The players should be fast because they are pushed for time: The available time depends on the difficulty level (see above). The time is defined by a Cookie („timer”), which has a lifetime (setMaxAge).

If the players have given a wrong answer or were slow (because the time for a question is running out), they will lose a life point. Every player has three life points, and every wrong/slow answer decreases the number of life points. If the lives run out, the game is over. The player should give at least seven correct answers to 10 questions to win the current round. If the player passes the quiz, they get a point in the selected category and the difficulty level. 

**Player Statistics and Ranks**

The application saves all information about every player: how many times they have started a game (attempts) and how many times they have lost or won. It contains all player's wins in the different categories, and it contains how many wins the player has in the different difficulty levels. All information and data are continuously recorded in a database. 
The player can read all this information on his initial personal page after login so that they can track the progress. 

There are two kinds of ranks: category awards and difficulty ranks. If the player has completed ten quizzes in a specific category, they get to earn the category award so that the players can get the following category awards:
* **Phrasal Verbs Gold Award**: complete ten quizzes in the following category: phrasal verbs
* **Collocation Gold Award**: complete ten quizzes in the following category: collocations
* **Nouns Gold Award**: complete ten quizzes in the following category: nouns
* **Adjectives Gold Award**: complete ten quizzes in the following category: adjectives
* **Sentences Gold Award**: complete ten quizzes in the following category: sentences
* **Adverbs Gold Award**: complete ten quizzes in the following category: adverbs
* **Informatic Vocabulary Gold Award**: complete ten quizzes in the following category: informatic vocabulary.

The difficulty ranks are the following:
* **Noble Award**: win 30 games on easy mode.
* **Baron Award**: win 30 games on medium mode.
* **King Award**: win 30 games on hard mode:
* **Demigod Award**: This is the ultimate award the player can earn. The player must get every seven category gold awards and every three difficulty awards to get this rank.

**List of the false Words**:

If the player has given a wrong answer, the application saves the word to the database. After the game, the player can check these words: a menu item can be found on the initial personal page, which shows the player every wrong word from all their games. This list contains every meaning and the example sentence(s) of the false words. The players can read it, and if they feel that they have already memorized that, it can be removed from this list.








