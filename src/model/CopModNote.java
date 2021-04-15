package model;

import java.util.List;
import java.util.ArrayList;

import questions.Question;
import questions.FourthTestQuestion;

public class CopModNote implements Note {
	
	private String title;
	private List<String> bulletPoints, displayTexts;
	private List<Question> noteQuestions;
	
	public CopModNote() {
		bulletPoints = new ArrayList<>();
		displayTexts = new ArrayList<>();
		noteQuestions = new ArrayList<>();
		
		Question q1 = new FourthTestQuestion();
		
		// title
		title = "Copies Model";
		
		// bullet points
		String bp1 = "- For many people, a mental visualisation of what is happening in a recursive algorithm can greatly aid their understanding of recursion\n  generally";
		String bp2 = "- Many can confuse recursion as a form of ITERATION (while/for loops) as oppose to a series of calls to the same function";
		String bp3 = "- The COPIES MODEL describes recursion as:-\n"
				+ "       - 'a process that is capable of triggering new instantiations of itself, with control passing forward to successive instantiations and\n          back from terminating ones'";
		String bp4 = "- The Copies Model helps reinforce the fact that new parameters and/or local variables are created with new instantiations ('copies') of\n"
				+ "  recursive algorithms as well as effectively describing the active flow (caller to callee) and the passive flow (callee back to caller)";
		
		bulletPoints.add(bp1);
		bulletPoints.add(bp2);
		bulletPoints.add(bp3);
		bulletPoints.add(bp4);
		
		// display texts
		String dt1 = "Alg1(numList)\n      "
				+ "if numList empty then\n          "
				+ "1\n      "
				+ "else 2*head(numList) + Alg1(tail(numList))\n\n"
				+ "===============\n\n"
				+ "Note:-\n    "
				+ "- '+' = concatenation here (joining of two lists)\n    "
				+ "- The 'head' of a list is the first element of it\n    "
				+ "- The 'tail' of a list is the same list with the first\n      element removed";
		
		String dt2 = "Alg1([4, 1, 3, 5])\n\n"
				+ "// active flow\n"
				+ " = 2*4 + Alg1([1, 3, 5])    <--    new 'copy' of Alg1\n"
				+ " = 2*4 + 2*1 + Alg1([3, 5])\n"
				+ " = 2*4 + 2*1 + 2*3 + Alg1([5])\n"
				+ " = 2*4 + 2*1 + 2*3 + 2*5 + Alg1([])\n\n"
				+ "// passive flow\n"
				+ " = 2*4 + 2*1 + 2*3 + 2*5 + [1]\n"
				+ " = 2*4 + 2*1 + 2*3 + [10, 1]\n"
				+ " = 2*4 + 2*1 + [6, 10, 1]\n"
				+ " = 2*4 + [2, 6, 10, 1]\n"
				+ " = [8, 2, 6, 10, 1]";
		
		displayTexts.add(dt1);
		displayTexts.add(dt2);
		
		// note question
		noteQuestions.add(q1);
	}

	@Override
	public String getNoteID() {
		return "copModNoteID";
	}

	@Override
	public String getNoteTitle() {
		return title;
	}

	@Override
	public List<String> getBulletPoints() {
		return bulletPoints;
	}

	@Override
	public List<String> getDisplayTexts() {
		return displayTexts;
	}

	@Override
	public List<Question> getRelevantQuestions() {
		return noteQuestions;
	}

}
