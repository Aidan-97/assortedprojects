package questions;

import java.util.List;
import java.util.ArrayList;

public class EighthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer1, incAnswer2, incAnswer3, corAnswer, displayText, corExpl, incExpl;
	
	public EighthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "Which one of the following statements is true?";
		
		displayText = "A: It is always possible to write\na recursive function iteratively\n\n"
				+ "B: It is usually, but not always possible\nto write a recursive function iteratively\n\n"
				+ "C: It is almost never possible to write a\nrecursive function iteratively\n\n"
				+ "D: It is never possible to write a\nrecursive function iteratively";
		
		incAnswer1 = "B";
		incAnswer2 = "C";
		incAnswer3 = "D";
		corAnswer = "A";
		
		answers.add(incAnswer1);
		answers.add(incAnswer2);
		answers.add(incAnswer3);
		answers.add(corAnswer);
		
		incExpl = "";
		corExpl = "";
		
	}

	@Override
	public String getQuestionID() {
		return "eighthID";
	}

	@Override
	public String getQuestionText() {
		return question;
	}

	@Override
	public List<String> getAnswers() {
		return answers;
	}

	@Override
	public String getCorrectAnswer() {
		return corAnswer;
	}

	@Override
	public String getTextFromDisplay() {
		return displayText;
	}

	@Override
	public String getCorrectExplanation() {
		return corExpl;
	}

	@Override
	public String getIncorrectExplanation() {
		return incExpl;
	}

	@Override
	public boolean isMultipleChoice() {
		return true;
	}

}
