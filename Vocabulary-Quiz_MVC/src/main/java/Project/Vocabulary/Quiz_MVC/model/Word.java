package Project.Vocabulary.Quiz_MVC.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name="word")
public class Word {
	
	@Id
	@Column (name="id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column (name="english")
	private String english; 
	
	@Column (name="hungarian")
	private String hungarian; 
	
	@Column (name="example")
	private String example; 
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn (name="categoryId")
	private int categoryId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getHungarian() {
		return hungarian;
	}

	public void setHungarian(String hungarian) {
		this.hungarian = hungarian;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	} 
	

}
