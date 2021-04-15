package model;

import java.util.List;
import java.util.ArrayList;

public class SecondOddEven implements Algorithm {

	private String pseudocode, title, result, resultAnnotation;
	private List<String> dataSteps, pseudoHighlights, stepAnnotations;
	private List<List<String>> highlightedSteps;
	private Algorithm origAlgorithm;
	
	public SecondOddEven() {
		dataSteps = new ArrayList<>();
		pseudoHighlights = new ArrayList<>();
		stepAnnotations = new ArrayList<>();
		highlightedSteps = new ArrayList<>();
		
		origAlgorithm = new OddEvenAlgorithm();
		
		// title
		title = origAlgorithm.getAlgorithmTitle();
		
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
		String hp9 = ps[4];
		
		pseudoHighlights.add(hp1);
		pseudoHighlights.add(hp2);
		pseudoHighlights.add(hp3);
		pseudoHighlights.add(hp4);
		pseudoHighlights.add(hp5);
		pseudoHighlights.add(hp6);
		pseudoHighlights.add(hp7);
		pseudoHighlights.add(hp8);
		pseudoHighlights.add(hp9);
		
		// data steps
		String[] ds = { "isEven(9)\n","isOdd(8)\n", "isEven(7)\n", "isOdd(6)\n", "isEven(5)\n", "isOdd(4)\n", "isEven(3)\n",
				"isOdd(2)\n", "isEven(1)\n", "isOdd(0)"			
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
		List<String> hs9 = new ArrayList<>();
		
		hs1.add(ds[1]);
		hs2.add(ds[2]);
		hs3.add(ds[3]);
		hs4.add(ds[4]);
		hs5.add(ds[5]);
		hs6.add(ds[6]);
		hs7.add(ds[7]);
		hs8.add(ds[8]);
		hs9.add(ds[9]);
		
		highlightedSteps.add(hs1);
		highlightedSteps.add(hs2);
		highlightedSteps.add(hs3);
		highlightedSteps.add(hs4);
		highlightedSteps.add(hs5);
		highlightedSteps.add(hs6);
		highlightedSteps.add(hs7);
		highlightedSteps.add(hs8);
		highlightedSteps.add(hs9);
		
		// annotations
		String[] as = { "Begin algorithm breakdown for isEven/isOdd with n=9 passed into isEven method",
				"9 > 0, so isOdd method called with decremented n value (n=8)",
				"8 > 0, so isEven method called with decremented n value (n=7)",
				"7 > 0, so isOdd method called with decremented n value (n=6)",
				"6 > 0, so isEven method called with decremented n value (n=5)",
				"5 > 0, so isOdd method called with decremented n value (n=4)",
				"4 > 0, so isEven method called with decremented n value (n=3)",
				"3 > 0, so isOdd method called with decremented n value (n=2)",
				"2 > 0, so isEven method called with decremented n value (n=1)",
				"1 > 0, so isOdd method called with decremented n value (n=0)"
		};
		
		for (String s : as){
			stepAnnotations.add(s);
		}
		
		// result annotation
		resultAnnotation = "Base case reached in isOdd method, appropriate boolean result shown";
		stepAnnotations.add(resultAnnotation);
		
		// result
		result = "false";
	}
	
	@Override
	public String getAlgorithmID() {
		return "secondOddEvenID";
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
		return origAlgorithm;
	}

	@Override
	public List<String> getMultiDisplayButtonLabels() {
		return getMultiDisplayAlgorithm().getMultiDisplayButtonLabels();
	}

}
