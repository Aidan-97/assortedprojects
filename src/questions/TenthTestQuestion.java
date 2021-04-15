package questions;

import java.util.List;
import java.util.ArrayList;

public class TenthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer, incAnswer2, incAnswer3, corAnswer, displayText, incExpl;
	
	public TenthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "The following algorithm is an example of what form of recursion?";
		
		incAnswer = "Linear Recursion";
		incAnswer2 = "Nested Recursion";
		incAnswer3 = "Mutual Recursion";
		corAnswer = "Binary Recursion";
		
		answers.add(incAnswer);
		answers.add(incAnswer2);
		answers.add(incAnswer3);
		answers.add(corAnswer);
		
		displayText = "int mystery(int n, int k)\n      "
				+ "if (k == 0 || n == k)\n          "
				+ "return 1\n      "
				+ "else\n          "
				+ "return mystery(n-1, k) + mystery(n-1, k-1)";
		
		incExpl = "Correct answer is Binary Recursion since the function makes\n"
				+ "2 calls to itself";
	}

	@Override
	public String getQuestionID() {
		return "tenthID";
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
		return true;
	}

}
