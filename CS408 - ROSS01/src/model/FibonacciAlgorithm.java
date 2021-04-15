package model;

import java.util.List;
import java.util.ArrayList;
/**
 * Implementation of class for breakdown of Fibonacci number calculator algorithm.
 * 
 * @author AidanJoseph
 *
 */
public class FibonacciAlgorithm implements Algorithm {
	
	private String pseudocode, title, result, resultAnnotation;
	private List<String> dataSteps, pseudoHighlights, stepAnnotations;
	private List<List<String>> highlightedSteps;
	
	public FibonacciAlgorithm() {
		dataSteps = new ArrayList<>();
		highlightedSteps = new ArrayList<>();
		pseudoHighlights = new ArrayList<>();
		stepAnnotations = new ArrayList<>();
		
		// title
		title = "Fibonacci Number Calculator";

		// pseudocode
		String[] ps = { "int fibRec(int i)\n      ", "if (i < 2)\n           ",
				"return i\n      ", "return ", "fibRec(i-1) ", "+ ", "fibRec(i-2)",
				"\n\n===================\n\n", "NB: Fibonacci Sequence = 0, 1, 1, 2, 3, 5, 8, ..."
		};
		
		pseudocode = String.join("", ps);
		
		// highlighted pseudocode
		String hp1 = ps[4];
		String hp2 = ps[6];
		
		pseudoHighlights.add(hp1);
		pseudoHighlights.add(hp2);
		
		// data steps
		String[] steps = { " fibRec(5) ", " fibRec(4) + fibRec(3) \n",
				
				" fibRec(3) + fibRec(2) ", " (fibRec(2) + fibRec(1)) + fibRec(2) ",
				" ((fibRec(1) + fibRec(0)) + fibRec(1)) + fibRec(2) ", " ((1 + fibRec(0)) + fibRec(1)) + fibRec(2) ",
				" (1 + 0 + fibRec(0)) + (fibRec(2)) ", " (1 + 0 + 1) + (fibRec(2)) ", " (2) + (fibRec(1) + fibRec(0)) ",
				" (2) + (1 + fibRec(0)) ", " (2) + (1 + 0) ", " 3 \n",
				
				" fibRec(2) + fibRec(1) ", " (fibRec(1) + fibRec(0)) + fibRec(1) ",
				" (1 + fibRec(0)) + fibRec(1) ", " (1 + 0) + fibRec(1) ", " (1 + 0) + 1 ", " 2 "
		};
		
		for (String step : steps){
			dataSteps.add(step);
		}
		
		// highlighted data steps
		List<String> hs1 = new ArrayList<>();
		List<String> hs2 = new ArrayList<>();
		
		for (int i=2; i < 12; i++){
			hs1.add(dataSteps.get(i));
		}
		
		for (int i=12; i < 18; i++){
			hs2.add(dataSteps.get(i));
		}
		
		highlightedSteps.add(hs1);
		highlightedSteps.add(hs2);
		
		// annotations
		String[] anno = { "Begin algorithm breakdown for fibRec, with i=5",
				"5 >= 2 so problem broken down into two new instances of fibRec with new parameters (i=4, i=3 respectively) created",
				"Begin solving fibRec(4) first. 4 >= 2 so problem broken down as above with more new parameters created",
				"Solving fibRec(3), 3 >= 2 so problem broken down as above with more new parameters created",
				"Solving fibRec(2), 2 >= 2 so problem broken down as above with more new parameters created",
				"Base case reached, value passed back up call chain",
				"Another base case reached, value passed back up call chain as before",
				"Base case reached again, values passed back up call chain as before",
				"Again solving fibRec(2), 2 >= 2 so problem broken down as above with more new parameters created",
				"A base case reached here, as before value passed back up call chain",
				"Another base case reached here, value passed back up call chain",
				"fibRec(4) now solved (=3)",
				"Now begin solving fibRec(3). 3 >= 2 so problem broken down as above with more new parameters created",
				"Once again solving fibRec(2), 2 >= 2 so problem broken down as above with more new parameters created",
				"Base case reached, appropriate value passed back up call chain",
				"Another base case reached in this step, value passed up call chain as before",
				"Solving fibRec(1), base case satisfied here so value of 1 is returned",
				"fibRec(3) now solved (=2)",
		
		};
		
		for (String s : anno){
			stepAnnotations.add(s);
		}
		
		// result annotation
		resultAnnotation = "fibRec(5) now solved, result shown";
		stepAnnotations.add(resultAnnotation);
				
		// result
		result = "3 + 2 = 5";		
	}

	@Override
	public String getAlgorithmID() {
		return "fibID";
	}
	
	@Override
	public String getAlgorithmTitle(){
		return title;
	}

	@Override
	public String getPseudocode() {
		return pseudocode;
	}

	@Override
	public String getFinalDataDisplay() {
		String s = String.join("\n", dataSteps);
		return s;
	}
	
	@Override
	public String getFinalAnnotationDisplay() {
		String s = String.join("\n", stepAnnotations);
		return s;
	}

	@Override
	public List<String> getDataSteps() {
		return dataSteps;
	}
	
	@Override
	public List<List<String>> getHighlightedSteps(){
		return highlightedSteps;
	}
	
	@Override
	public List<String> getPseudoHighlights(){
		return pseudoHighlights;
	}
	
	@Override
	public List<String> getStepAnnotations(){
		return stepAnnotations;
	}

	@Override
	public String getResult() {
		return result;
	}

	@Override
	public String getResultAnnotation() {
		return resultAnnotation;
	}

	@Override
	public boolean isMultiDisplay() {
		return false;
	}

	@Override
	public Algorithm getMultiDisplayAlgorithm() {
		return null;
	}

	@Override
	public List<String> getMultiDisplayButtonLabels() {
		return null;
	}

}
