package model;

import java.util.List;
import java.util.ArrayList;

import questions.EighthTestQuestion;
import questions.Question;
import questions.SeventhTestQuestion;

public class FibIteNote implements Note {
	
	private String title;
	private List<String> bulletPoints, displayTexts;
	private List<Question> noteQuestions;
	private Algorithm alg;
	
	public FibIteNote() {
		bulletPoints = new ArrayList<>();
		displayTexts = new ArrayList<>();
		noteQuestions = new ArrayList<>();
		
		// title
		title = "Fibonacci Sequence (Iterative Approach)";
		
		// bullet points
		String bp1 = "- As shown by the Recursive Fibonacci algorithm breakdown (for sufficiently high i), the same value needs to be calculated multiple times";
		String bp2 = "- So, for very large input the evaluation tree (shown below) gets bigger and bigger and is evaluating in EXPONENTIAL TIME (approx O(2^n))";
		String bp3 = "- The iterative approach, however, is evaluating in POLYNOMIAL TIME (O(n)) and is, therefore, more efficient (algorithm shown below)";
		String bp4 = "- Note: It is always possible to convert a recursive algorithm into an iterative one and vice versa";
	
		
		bulletPoints.add(bp1);
		bulletPoints.add(bp2);
		bulletPoints.add(bp3);
		bulletPoints.add(bp4);
		
		// display texts
		alg = new FibonacciAlgorithm();
		String dt1 = alg.getPseudocode();
		
		String dt2 = "int fibIte(int i)\n      "
				+ "if (i < 2)\n          "
				+ "return i\n      "
				+ "int f1=1, f2=1, fib=1\n      "
				+ "for (int j=3, j <= i, j++)\n          "
				+ "fib = f1 + f2\n          "
				+ "f1 = f2\n          "
				+ "f2 = fib\n      "
				+ "return fib";
		
		String dt3 = "\n                              fibRec(5)                 \n"
				+ "          fibRec(4)            +          fibRec(3)          \n"
				+ "fibRec(3) + fibRec(2) + fibRec(2) + fibRec(1)\n"
				+ "...\n...\n\n=================\n\n"
				+ "fibRec(3) is calculated 2 times\nfibRec(2) is calculated 3 times and so on";
		
		displayTexts.add(dt1);
		displayTexts.add(dt2);
		displayTexts.add(dt3);
		
		// note question
		Question q1 = new SeventhTestQuestion();
		Question q2 = new EighthTestQuestion();
		
		noteQuestions.add(q1);
		noteQuestions.add(q2);
	}

	@Override
	public String getNoteID() {
		return "fibNoteID";
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
