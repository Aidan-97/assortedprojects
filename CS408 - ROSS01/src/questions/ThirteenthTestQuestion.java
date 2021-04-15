package questions;

import java.util.List;
import java.util.ArrayList;

public class ThirteenthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, corAnswer, displayText, incExpl;
	
	public ThirteenthTestQuestion() {
		answers = new ArrayList<>();
		
		question = "What does fibRec(8) return?\n"
				+ "[This algorithm is analysed in greater detail in the Fibonacci Sequence Algorithm Breakdown]";
		
		displayText = "int fibRec(int n)\n      "
				+ "if (i < 2)\n          "
				+ "return i\n      "
				+ "return fibRec(i-1) + fibRec(i-2)";
		
		corAnswer = "21";
		
		incExpl = "View the Fibonacci Sequence Algorithm Breakdown for further insight\n"
				+ "into this particular algorithm";
	}

	@Override
	public String getQuestionID() {
		return "thirteenthID";
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
