package questions;

import java.util.List;
import java.util.ArrayList;

public class FifteenthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, corAnswer, displayText, incExpl;
	
	public FifteenthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "What does abc(2) return? Algorithm shown below";
		
		displayText = "int abc(int k)\n      "
				+ "if (n == 4)\n          "
				+ "return n\n      "
				+ "return 2 * abc(n+1)";
		
		corAnswer = "16";
		
		incExpl = "Correct answer is 16";
	}

	@Override
	public String getQuestionID() {
		return "fifteenthID";
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
		return "";
	}

	@Override
	public String getIncorrectExplanation() {
		return incExpl;
	}

	@Override
	public boolean isMultipleChoice() {
		return false;
	}

}
