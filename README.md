# Vocabulary Quiz: English Learner Application

## Short review
 
   I have to improve my English, so I have decided, that I create an application, that will be able to help me to build a huge vocabulary, and providing a platform to practice the collected words and expressions.
 
   This application can provide a possibility for the users to practice the English language. It contains a very enormous vocabulary with example sentences, each word and expression can be found in a database.
   
   The words and expressions are on an advanced level.  The users can get at this collection, and they can test their knowledge: this application namely has a quiz function.
   I have defined 7 categories for the words. In the application, next to the name of the category you can see, how many words can be found in the current category. The following categories are available in this application:
* phrasal verbs
* collocations
* nouns
* adjectives
* sentences
* adverbs
* informatic vocabulary
 
## The Main Page
 
   On the main page can be found some information and data about the application: It shows the size of the vocabulary and the categories of the words. Here can be found other buttons, that guide the visitor to other pages.
    
   The buttons are the following:
 
   **Registration:** If somebody wants to play the quiz, registration is compulsory. The user must create a username and a password. If the registration was successful, the user will be saved into the database. The registration is failed, if the database already contains the username, in this case, the user must choose another username. 
 
   **Login:** If the users have done the registration, they can enter into their own page. The application always checks the username and the password by login, if one of them is incorrect (can’t be found in the database), the login will be denied.
     After the successful login, the user is directed to his own page. On this page can be found every relevant information about the current user (for example how many times did the user play, how many points has been collected, number of the successful and failed attempts, etc). From the user’s page can be started a new quiz. For the mechanism and functions of the quiz see below.
 
   **Vocabulary:** The whole vocabulary is available for everyone without registration. After the selection of the category, every word with example sentences can be read.  The current version of this program doesn’t contain this function yet, but it will be available in a short time.
 
   **Admin:** see below
 
## The management of the vocabulary
 
   **Admin:** The admins don’t have to make a registration process, because they are registered in the database from the beginning, but they have to give their username and password to use the function of the administrators’ page. These data can be found in the database, if they are incorrect, the entrance to the administrator’s page will be denied.
   **Insert new words and expressions into the database:** The admins can insert new words and expressions into the database: they have to add the English and the Hungarian meaning of the word, and they have to give an example sentence in English. They have to select also the category.
   
   **Update the data (correcting or change earlier registered data):** the administrators can update the words (for example the given information was incorrect, and it is necessary to correct.
   The administrator has to select a category. The next page will show for the administrator all words (and all other data of the words,  so all meaning, id numbers, examples), that belong to the selected category. The administrator on this page can select the word by giving the identical number. If the admin gives a wrong id number (because the words of the given category don’t have it), the administrator can’t continue the process of the modification, must give another number.
    
   If the administrator has chosen the word (given the right id number), the word can be modified on another page: here can be read the actual data, and under every data can be given a new value (for example the administrator has given earlier an English word, but the word has been registered with a spelling mistake, so here can be registered the correction).
    
   So this function provides a possibility to change the values of the database, therefore the vocabulary can be updating, the mistakes can be overwritten.  
 
## The Quiz/Settings possibilities
 
  The users can test their knowledge using the quiz function of this application.
 
  The quiz is available only for registered users. It can be started from the own page of the logged users. If the users push the „Start a new quiz button” they will be directed to the settings of the current game.
 
  The following properties can be configured by the users:
 
  * **Difficulty Level (Easy, Medium, Hard)**
  * **Category Select**
  * **Initial language (English or Hungarian)**
 
 
	**Difficulty Level:** There are in the game three difficulty levels, these are the following:
    * **Easy:** the user has 10 seconds to write an answer
    * **Medium:** the user has 15 seconds to write an answer
    * **Hard:** the user has 20 seconds to write an answer
 
	**Category Select:** The users can select a category of words. The words of the selected category will appear during the quiz.
 
	**Initial Language:** There is two option: English, or Hungarian. If the users choose English, the words, that will appear on the screen during the quiz are English. In this case, the users have to write in the text field the Hungarian variation of the appearing words. If the users choose Hungarian, they have to write naturally the English meaning of the received Word.
 
## The Quiz/The Game

  If the users have selected the required properties of the quiz, they can start the current game. In the current version of this application, the users get 10 words. The appearance of the words from the selected category is coincidental. If the user has selected the English as initial language, the words, that appear on the screen are English, and the user has to write the Hungarian meaning of the received word, if the Hungarian language is the selected as initial language, so the user should write the English meaning.
  
  The users should be fast because they are pushed for time: The available time depends on the difficulty level (see above). The time is defined by a Cookie („timer”), which has a lifetime (setMaxAge).
  
  If the users give a wrong answer, or they are slow (because the time for a question is running out), they will lose a life point. Every user has three life points, and every wrong/slow answer decreases the number of life points. If the lives run out, the game is over. To win the current game the users should give at least 7 right answers to 10 questions. If the users pass the quiz, they get a point in the selected Category.
  
  The application register other information about every user: how many times have they started a game (attempts), how many times have they lost or won. All information and data are continuously recorded in a database.
 
 
## Coming soon (planned new features) :

**Ranks and awards:** the users can collect points and they can reach ranks and awards after the seized points. This isn’t part of the application currently, but it will come as soon as possible.

**Practice the mistaken words:** if the user has given a wrong answer, the application collects all these mistaken words. These words will be available for the user, who can start a new own quiz, where only these mistaken words will appear. If the user gives the right answer, the word will be removed from the collection.

**Claim the users:** If the current quiz is over, on the last Page will be seen all answers and solutions. If the users are not satisfied with the solution (for example in their mind the answer should be correctly accepted) they can make a sign to the administrator. The administrator will receive the claims, and he will make a correction if it is necessary. 
