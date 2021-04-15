package questions;

import java.util.List;
import java.util.ArrayList;

public class NinthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer, corAnswer, displayText, incExpl, corExpl;
	
	public NinthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "Is the following recursive algorithm written properly?";
		
		incAnswer = "Yes";
		corAnswer = "No";
		
		answers.add(incAnswer);
		answers.add(corAnswer);
		
		displayText = "int function(int a)\n      "
				+ "if (a == 0)\n          "
				+ "return 0\n      "
				+ "else\n          "
				+ "return function(a+1)";
		
		incExpl = "Since the algorithm's recursive case doesn't converge towards the base case";
		corExpl = incExpl;
	}

	@Override
	public String getQuestionID() {
		return "ninthID";
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
