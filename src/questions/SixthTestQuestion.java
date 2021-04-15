package questions;

import java.util.List;
import java.util.ArrayList;

public class SixthTestQuestion implements Question {
	
	private List<String> answers;
	private String question, corAnswer, displayText, incExpl;
	
	public SixthTestQuestion(){
		answers = new ArrayList<>();
		
		question = "What does Alg3(8) return?\n"
				+ "     [NB: '/' = truncated division (no remainder) i.e. 6/2 = 7/2 = 3]";
		
		corAnswer = "8";
		
		answers.add(corAnswer);
		
		displayText = "Alg3(1) = 1\n\n"
				+ "Alg3(i) = Alg3((i+1)/2) + Alg3(i/2)";
		
		incExpl = "Correct answer is 8, carefully check the call trace of Alg3\n"
				+ "and the meaning of '/' (shown in question)";
	}

	@Override
	public String getQuestionID() {
		return "sixthID";
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
