package model;

import java.util.List;
import java.util.ArrayList;

import questions.NinthTestQuestion;
import questions.Question;
import questions.TenthTestQuestion;
import questions.FourteenthTestQuestion;

public class RecGenNote implements Note {
	
	private String title;
	private List<String> bulletPoints, displayTexts;
	private List<Question> noteQuestions;
	
	public RecGenNote() {
		bulletPoints = new ArrayList<>();
		displayTexts = new ArrayList<>();
		noteQuestions = new ArrayList<>();
		
		// title
		title = "Recursion: General";
		
		// bullet points
		String bp1 = "- In Computer Science, RECURSION is a method where the solution to the problem depends on solutions to smaller instances of the same \n  problem";
		String bp2 = "- A recursive algorithm must contain the following:-\n"
				+ "       - A base case (input(s) for which the function produces a result trivially i.e. without recursing)\n"
				+ "       - A recursive case for which the function recurs (calls itself) for a simpler/smaller sub-problem\n"
				+ "              - [The recursive case must converge towards the base case to avoid INFINITE RECURSION]";
		String bp3 = "- For recursive algorithms containing local variables or parameters, a new instance of these variables/paramters is created\n"
				+ "  each time the algorithm recurs (calls itself)";
		String bp4 = "- Perhaps the most simple example of this type of algorithm is the mathematical factorial function, shown below";
		String bp5 = "- Types of Recursion:-";
		
		bulletPoints.add(bp1);
		bulletPoints.add(bp2);
		bulletPoints.add(bp3);
		bulletPoints.add(bp4);
		bulletPoints.add(bp5);
		
		// display texts
		String dt1 = "int factorial(int n)\n      "
				+ "if (n < 2)\n          "
				+ "return 1\n      "
				+ "else\n          "
				+ "return n * factorial(n-1)";
		
		String dt2 = "factorial(5)\n"
				+ " = 5 * factorial(4)\n"
				+ " = 5 * 4 * factorial(3)\n"
				+ " = 5 * 4 * 3 * factorial(2)\n"
				+ " = 5 * 4 * 3 * 2 * factorial(1)\n"
				+ " = 5 * 4 * 3 * 2 * 1\n"
				+ " = 5 * 4 * 3 * 2\n"
				+ " = 5 * 4 * 6\n"
				+ " = 5 * 24\n"
				+ " = 120";
		
		String dt3 = "\nint recSum(int x)\n      "
				+ "if (x == 1)\n          "
				+ "return x\n      "
				+ "return x + recSum(x-1)";
		
		String dt4 = "\nint tailRecSum(int x, int y=0)\n      "
				+ "if (x == 0)\n          "
				+ "return y\n      "
				+ "return tailRecSum(x-1, y+x)";
		
		String dt5 = "\nboolean isEven(int n)\n      "
				+ "if (x == 0)\n          "
				+ "return true\n      "
				+ "return isOdd(n-1)\n\n"
				+ "boolean isOdd(int n)\n      "
				+ "if (x == 0)\n          "
				+ "return false\n      "
				+ "return isEven(n-1)";
		
		Algorithm alg = new FibonacciAlgorithm();
		String dt6 = "\n" + alg.getPseudocode();
		
		String dt7 = "\nA(m, n) = n+1                                        if m==0\n"
				+ "A(m, n) = A(m-1, 1)                if m>0 && n==0\n"
				+ "A(m, n) = A(m-1, A(m, n-1))            if m, n > 0"
				+ "\n\n==========\n\n"
				+ "NB: The above is called the Ackermann\n        Function";
		
		String dt8 = "Recursion where only one call is made to the function\n"
				+ "from within the function";
		
		String dt9 = "Recursive procedure where the recursive call is the last\n"
				+ "action to be taken by the function (a specific type of \n"
				+ "linear recursion)\n\n"
				+ "[Note: A recursive call like 'return 1 + tailRecSum(x-1, y+x)'\n"
				+ " would not count as tail recursive since action of adding 1\n"
				+ " also needs to be taken]";
		
		String dt10 = "A set of functions that recursively call themselves\n"
				+ "INDIRECTLY by calling each other";
		
		String dt11 = "Recursive function which calls itself exactly twice during the\n"
				+ "course of its execution\n\n"
				+ "[Note: MULTIPLE RECURSION is a more general version\n"
				+ " of Binary Recursion where 2 or more calls to the function\n"
				+ " itself are made]";
		
		String dt12 = "Recursive function where the argument passed to the\n"
				+ "function is the function itself";
		
		displayTexts.add(dt1);
		displayTexts.add(dt2);
		displayTexts.add(dt3);
		displayTexts.add(dt4);
		displayTexts.add(dt5);
		displayTexts.add(dt6);
		displayTexts.add(dt7);
		displayTexts.add(dt8);
		displayTexts.add(dt9);
		displayTexts.add(dt10);
		displayTexts.add(dt11);
		displayTexts.add(dt12);
		
		// note question
		Question q1 = new NinthTestQuestion();
		Question q2 = new TenthTestQuestion();
		Question q3 = new FourteenthTestQuestion();
		
		noteQuestions.add(q1);
		noteQuestions.add(q2);
		noteQuestions.add(q3);
	}

	@Override
	public String getNoteID() {
		return "recGenNoteID";
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
