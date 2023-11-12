package com.devrezaur.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "questions")
@Data
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer quesId;
	private String title;
	private String optionA;
	private String optionB;
	private String optionC;
	private Integer ans;
	private Integer chose;

	public Question() {
		super();
	}

	public Question(int quesId, String title, String optionA, String optionB, String optionC, int ans, int chose) {
		super();
		this.quesId = quesId;
		this.title = title;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.ans = ans;
		this.chose = chose;
	}


}
