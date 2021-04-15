package model;

import java.util.List;
import java.util.ArrayList;

public class OddEvenAlgorithm implements Algorithm {
	
	private String pseudocode, title, result, resultAnnotation;
	private List<String> dataSteps, pseudoHighlights, stepAnnotations, buttonLabels;
	private List<List<String>> highlightedSteps;
	
	public OddEvenAlgorithm() {
		dataSteps = new ArrayList<>();
		pseudoHighlights = new ArrayList<>();
		stepAnnotations = new ArrayList<>();
		buttonLabels = new ArrayList<>();
		highlightedSteps = new ArrayList<>();
		
		// title
		title = "Odd or Even Number (Mutual Recursion)";
		
		// pseudocode
		String[] ps = { "boolean isEven(int n)\n      ", "if (n=0)\n          ", "return true\n      ",
				"else\n          ", "return isOdd(n-1)", "\n\nboolean isOdd(int n)\n      ",
				"if (n=0)\n          ", "return false\n      ", "else\n          ", "return isEven(n-1)",
				"\n\n===================\n\n", "NB: This is an example of MUTUAL recursion"
		}; 
		
		pseudocode = String.join("", ps);
		
		// highlighted pseudocode
		String hp1 = ps[4];
		String hp2 = ps[9];
		String hp3 = ps[4];
		String hp4 = ps[9];
		String hp5 = ps[4];
		String hp6 = ps[9];
		String hp7 = ps[4];
		String hp8 = ps[9];
		
		pseudoHighlights.add(hp1);
		pseudoHighlights.add(hp2);
		pseudoHighlights.add(hp3);
		pseudoHighlights.add(hp4);
		pseudoHighlights.add(hp5);
		pseudoHighlights.add(hp6);
		pseudoHighlights.add(hp7);
		pseudoHighlights.add(hp8);
		
		// data steps
		String[] ds = { "isEven(8)\n", "isOdd(7)\n", "isEven(6)\n", "isOdd(5)\n", "isEven(4)\n", "isOdd(3)\n",
				"isEven(2)\n", "isOdd(1)\n", "isEven(0)"			
		};
		
		for (String s : ds){
			dataSteps.add(s);
		}
		
		// highlighted data steps
		List<String> hs1 = new ArrayList<>();
		List<String> hs2 = new ArrayList<>();
		List<String> hs3 = new ArrayList<>();
		List<String> hs4 = new ArrayList<>();
		List<String> hs5 = new ArrayList<>();
		List<String> hs6 = new ArrayList<>();
		List<String> hs7 = new ArrayList<>();
		List<String> hs8 = new ArrayList<>();
		
		hs1.add(ds[1]);
		hs2.add(ds[2]);
		hs3.add(ds[3]);
		hs4.add(ds[4]);
		hs5.add(ds[5]);
		hs6.add(ds[6]);
		hs7.add(ds[7]);
		hs8.add(ds[8]);
		
		highlightedSteps.add(hs1);
		highlightedSteps.add(hs2);
		highlightedSteps.add(hs3);
		highlightedSteps.add(hs4);
		highlightedSteps.add(hs5);
		highlightedSteps.add(hs6);
		highlightedSteps.add(hs7);
		highlightedSteps.add(hs8);
		
		// annotations
		String[] as = { "Begin algorithm breakdown for isEven/isOdd with n=8 passed into isEven method",
				"8 > 0, so isOdd method called with decremented n value (n=7)",
				"7 > 0, so isEven method called with decremented n value (n=6)",
				"6 > 0, so isOdd method called with decremented n value (n=5)",
				"5 > 0, so isEven method called with decremented n value (n=4)",
				"4 > 0, so isOdd method called with decremented n value (n=3)",
				"3 > 0, so isEven method called with decremented n value (n=2)",
				"2 > 0, so isOdd method called with decremented n value (n=1)",
				"1 > 0, so isEven method called with decremented n value (n=0)"
		};
		
		for (String s : as){
			stepAnnotations.add(s);
		}
		
		// result annotation
		resultAnnotation = "Base case reached in isEven method, appropriate boolean result shown";
		stepAnnotations.add(resultAnnotation);
		
		// result
		result = "true";
		
		// multi-display button labels
		String lb1 = "isEven(8)";
		String lb2 = "isEven(9)";
		
		buttonLabels.add(lb1);
		buttonLabels.add(lb2);
	}

	@Override
	public String getAlgorithmID() {
		return "oddEvenID";
	}

	@Override
	public String getAlgorithmTitle() {
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
	public List<List<String>> getHighlightedSteps() {
		return highlightedSteps;
	}

	@Override
	public List<String> getPseudoHighlights() {
		return pseudoHighlights;
	}

	@Override
	public List<String> getStepAnnotations() {
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
		return true;
	}

	@Override
	public Algorithm getMultiDisplayAlgorithm() {
		Algorithm a = new SecondOddEven();
		return a;
	}

	@Override
	public List<String> getMultiDisplayButtonLabels() {
		return buttonLabels;
	}
	
	

}
