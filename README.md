# Vocabulary Quiz: English Learner Application

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
* phrasal verbs: a group of words that combine a verb with an adverb or a preposition. Together, these words act as a single verb and take on a whole new meaning that's independent of the meanings of the individual words.
* collocations: a combination of closely related words (for example, „take a rest”, have a shover, „pay attention, etc.
* nouns: a collection of more complex or abstract nouns
* adjectives
* sentences: a collection of expressions often used, which consist of more words (for example: „obtain somebody's appointment to a post”. 
* adverbs
* informatic vocabulary: here, I would like to build a collection of the words used only in informatics and programming, even including the ordinary words and expressions that have special meaning in the above-named professional environment. 

 
## The Quiz/The Game

  If the users have selected the required properties of the quiz, they can start the current game. In the current version of this application, the users get 10 words. The appearance of the words from the selected category is coincidental. If the user has selected the English as initial language, the words, that appear on the screen are English, and the user has to write the Hungarian meaning of the received word, if the Hungarian language is the selected as initial language, so the user should write the English meaning.
  
  The users should be fast because they are pushed for time: The available time depends on the difficulty level (see above). The time is defined by a Cookie („timer”), which has a lifetime (setMaxAge).
  
  If the users give a wrong answer, or they are slow (because the time for a question is running out), they will lose a life point. Every user has three life points, and every wrong/slow answer decreases the number of life points. If the lives run out, the game is over. To win the current game the users should give at least 7 right answers to 10 questions. If the users pass the quiz, they get a point in the selected Category.
  
  The application register other information about every user: how many times have they started a game (attempts), how many times have they lost or won. All information and data are continuously recorded in a database.
 
 
## Coming soon (planned new features) :

**Ranks and awards:** the users can collect points and they can reach ranks and awards after the seized points. This isn’t part of the application currently, but it will come as soon as possible.

**Practice the mistaken words:** if the user has given a wrong answer, the application collects all these mistaken words. These words will be available for the user, who can start a new own quiz, where only these mistaken words will appear. If the user gives the right answer, the word will be removed from the collection.

**Claim the users:** If the current quiz is over, on the last Page will be seen all answers and solutions. If the users are not satisfied with the solution (for example in their mind the answer should be correctly accepted) they can make a sign to the administrator. The administrator will receive the claims, and he will make a correction if it is necessary. 
