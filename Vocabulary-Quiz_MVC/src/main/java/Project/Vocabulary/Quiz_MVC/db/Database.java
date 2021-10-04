package Project.Vocabulary.Quiz_MVC.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import Project.Vocabulary.Quiz_MVC.model.Admin;
import Project.Vocabulary.Quiz_MVC.model.Category;
import Project.Vocabulary.Quiz_MVC.model.User;
import Project.Vocabulary.Quiz_MVC.model.Word;

public class Database {
	
	private SessionFactory sessionFactory;

	public Database() {

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		
	}
	
	public List <Category>getAllCategory (){
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		List<Category> categories=null; 
		Query query=session.createNativeQuery("SELECT * FROM category", Category.class); 
		categories=query.getResultList(); 
		
		session.getTransaction().commit();; 
		session.close(); 
		
		return categories;
		
	}
	
		public Category getCategoryById(int categoryId) {
			
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Category category=session.get(Category.class, categoryId); 
		
		session.getTransaction().commit();
		session.close(); 

		return category;
	}
		
	public Word getTheWordById(int wordId) {
			
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
			
		Word word=session.get(Word.class, wordId); 
			
		session.getTransaction().commit();
		session.close(); 

		return word;
			
	}
	
	public List <Word> getTheWordByIdandCategoryId(int wordId, int categoryId) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		List<Word> words=null; 
		
		Query query=session.createNativeQuery("SELECT * FROM word WHERE id=:incomingId AND categoryID=:incomingCatId", Word.class); 
		query.setParameter("incomingId", wordId); 
		query.setParameter("incomingCatId", categoryId); 
		
		words=query.getResultList(); 
		
		session.getTransaction(); 
		session.close();
		
		
		return words;
	}

	public List <Word> getTheWordByCategoryId(int categoryId){
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		List <Word> words=null;
		Query query=session.createNativeQuery("SELECT * FROM word WHERE categoryID=:incomingCategoryId", Word.class); 
		query.setParameter("incomingCategoryId", categoryId); 
		words=query.getResultList(); 
		
		session.getTransaction(); 
		session.close();
		
		return words; 
		
	}
	
	public void updateWord (Word word) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		session.update(word);
		
		session.getTransaction().commit();
		session.close();
		
	}

	/**METHODS FOR USER*/
	
	public List <User> getUserByName (String name){
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		List<User> users=null; 
		
		Query query=session.createNativeQuery("SELECT * FROM user WHERE username LIKE :incomingName", User.class); 
		query.setParameter("incomingName", name); 
		users=query.getResultList(); 
		
		session.getTransaction(); 
		session.close();
		
		return users;
		
	}
	
	public void insertUser(String username, String password, int a, int b, int c, int d, int e, int f, int g, int h, int i, int j) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("INSERT INTO user (username, password, phrasalVerbsPoints,"
				+ " collocationsPoints, nounsPoints, adjectivesPoints, "
				+ "sentencesPoints, adverbsPoints, informaticVocabularyPoints, attempts, wins, lost ) "
				+ "VALUES (:newUsername, :newPassword, :newPhV, :newColl, :newNouns, :newAdj, :newSen, :newAdv, :newInfo,"
				+ ":newAttempts, :newWins, :newLost )"); 
		
		query.setParameter("newUsername", username); 
		query.setParameter("newPassword", password); 
		query.setParameter("newPhV", a); 
		query.setParameter("newColl", b); 
		query.setParameter("newNouns", c); 
		query.setParameter("newAdj", d); 
		query.setParameter("newSen", e); 
		query.setParameter("newAdv", f); 
		query.setParameter("newInfo", g); 
		query.setParameter("newAttempts", h); 
		query.setParameter("newWins", i); 
		query.setParameter("newLost", j);
		
		query.executeUpdate(); 
	
		session.getTransaction(); 
		session.close();
	}
	
	public List <Admin> getTheAdmin(String username, String password){
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		List<Admin> admins=null; 
		
		Query query=session.createNativeQuery("SELECT * FROM admin WHERE name=:incomingUsername "
				+ "AND password=:incomingPassword",Admin.class); 
		query.setParameter("incomingUsername", username); 
		query.setParameter("incomingPassword", password); 
		admins=query.getResultList(); 
		
		session.getTransaction(); 
		session.close();
		
		return admins;
			
	}
	
	public void insertNewWord(String english, String hungarian, String example, int categoryInt) {
		
		Session session=sessionFactory.openSession(); 
		session.beginTransaction(); 
		
		Query query=session.createNativeQuery("INSERT INTO word (english, hungarian, example, categoryID) "
				+ "VALUES(:newEnglish, :newHungarian, :newExample, :newCatId)"); 
		
		query.setParameter("newEnglish", english); 
		query.setParameter("newHungarian", hungarian); 
		query.setParameter("newExample", example); 
		query.setParameter("newCatId", categoryInt); 
		
		query.executeUpdate(); 
		
		session.getTransaction(); 
		session.close();

	}
	
	
	
	public void close() {
		
		sessionFactory.close();
	}

	

	


}
