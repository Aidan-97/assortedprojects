package questions;

import java.util.List;
import java.util.ArrayList;

public class FourteenthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer1, incAnswer2, incAnswer3, corAnswer, displayText, incExpl, corExpl;
	
	public FourteenthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "Which one of the statements below is true?";
		
		displayText = "A: Tail Recursion is a form of Linear Recursion\n\n"
				+ "B: Tail Recursion is NOT a form of Linear Recursion\n\n"
				+ "C: Tail Recursion and Linear Recursion are identical\n\n"
				+ "D: None of the above";
		
		corAnswer = "A";
		incAnswer1 = "B";
		incAnswer2 = "C";
		incAnswer3 = "D";
		
		answers.add(corAnswer);
		answers.add(incAnswer1);
		answers.add(incAnswer2);
		answers.add(incAnswer3);
		
		incExpl = "Tail Recursion IS a form of Linear Recursion since only one call is made\n"
				+ "within a Tail Recursive function to the function itself";
		corExpl = incExpl;
	}

	@Override
	public String getQuestionID() {
		return "fourteenthID";
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
