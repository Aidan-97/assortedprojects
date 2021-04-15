package questions;

import java.util.ArrayList;
import java.util.List;

public class TestQuestion implements Question {
	
	private List<String> answers;
	private String question, incAnswer1, incAnswer2, incAnswer3, corAnswer, displayText, corExpl, incExpl;
	
	public TestQuestion() {
		answers = new ArrayList<>();
		question = "The algorithm below is written to find the Fibonnaci number for a given int i\n"
				+ "     Select the correct missing section of code to complete the algorithm\n"
				+ "     [NB: Fibonacci sequence = (0, 1, 1, 2, 3, 5, 8,...)  =>  fibRec(0) = 0, fibRec(1) = 1, ..., fibRec(6) = 8]";
		
		incAnswer1 = "fibRec(i-1, i-2)";
		incAnswer2 = "fibRec(i) + fibRec(i-1)";
		incAnswer3 = "fibRec(i+1) + fibRec(i+2)";
		corAnswer = "fibRec(i-1) + fibRec(i-2)";
		
		answers.add(incAnswer1);
		answers.add(incAnswer2);
		answers.add(incAnswer3);
		answers.add(corAnswer);
		
		displayText = "public static int fibRec(int i) {\n           if (i < 2)\n\treturn i;\nreturn ___________;\n}";
		
		incExpl = "Correct answer is " + corAnswer + " since Fibonacci numbers\nare found by adding the two previous numbers in the sequence";
		corExpl = "Since Fibonacci numbers are found by adding the two previous\nnumbers in the sequence";
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
	public boolean isMultipleChoice() {
		return true;
	}

	@Override
	public String getQuestionID() {
		return "ID";
	}

	@Override
	public String getCorrectExplanation() {
		return corExpl;
	}

	@Override
	public String getIncorrectExplanation() {
		return incExpl;
	}

}
