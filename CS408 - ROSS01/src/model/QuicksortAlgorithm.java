package model;

import java.util.List;
import java.util.ArrayList;

// import javax.swing.text.*;

public class QuicksortAlgorithm implements Algorithm {
	
	private String pseudocode, title, result, resultAnnotation;
	private List<String> dataSteps, pseudoHighlights, stepAnnotations, buttonLabels;
	private List<List<String>> highlightedSteps;
	
	public QuicksortAlgorithm() {
		dataSteps = new ArrayList<>();
		pseudoHighlights = new ArrayList<>();
		stepAnnotations = new ArrayList<>();
		buttonLabels = new ArrayList<>();
		highlightedSteps = new ArrayList<>();
		
		// title
		title = "Quicksort";
		
		// pseudocode
		String[] ps = { "quicksort(int left, int right)\n      ", "if (left < right)\n          ",
				"int p = partition(left, right)", "\n          ", "quicksort(left, p-1)", "\n          ",
				"quicksort(p+1, right)", "\n\n===================\n\n", "int partition(int left, int right)\n      ",
				"int pivot = A[left]\n      ",
				"for (int i=left+1; i < A.length; i++)\n          ",
				"if (A[i] < pivot)\n              ", "move A[i] to left of pivot\n          ", 
				"else if (A[i] >= pivot)\n              ", "move A[i] to right of pivot\n      ", "return pivot array position",
				"\n\n===================\n\n",
				"input array: A = [3, 1, 4, 1, 5, 9, 2]"
		};
		
		pseudocode = String.join("", ps);
		
		// highlighted pseudocode
		String hp1 = ps[2];
		String hp2 = ps[4];
		String hp3 = ps[6];
		
		pseudoHighlights.add(hp1);
		pseudoHighlights.add(hp2);
		pseudoHighlights.add(hp3);
		
		// data steps
		String[] ds = { "[3 1 4 1 5 9 2]", "[{3} 1 4 1 5 9 2]", "[2 1 1 {3} 5 9 4]\n", "[{2} 1 1]",
				"[1 1 {2}]", "[{1} 1]", "[{1} 1] ", "[1]", "[1 1]", "[1 1 2]\n",
				"[{5} 9 4]", "[4 {5} 9]", "[4]", "[9]", "[4 5 9]"
		};
		
		for (String s : ds){
			dataSteps.add(s);
		}
		
		// highlighted data steps
		List<String> hs1 = new ArrayList<>();
		List<String> hs2 = new ArrayList<>();
		List<String> hs3 = new ArrayList<>();
		
		hs1.add(ds[1]);
		hs1.add(ds[2]);
		
		for (int i=3; i < 10; i++){
			hs2.add(ds[i]);
		}
		
		for (int i=10; i < 15; i++){
			hs3.add(ds[i]);
		}
		
		highlightedSteps.add(hs1);
		highlightedSteps.add(hs2);
		highlightedSteps.add(hs3);
		
		// annotations
		String[] as = { "Begin algorithm breakdown for Quicksort, with left = 0, right = A.length - 1",
				"Pick the first element (3 here) as the pivot",
				"Partition the array, all elements < pivot moved to left sub-array, all elements >= pivot moved to right sub-array. \nPivot now considered sorted",
				"Now do quicksort for left sub-array [2, 1, 1]. Pick first element (2 here) as the pivot",
				"Partition the sub-array, elements moved as described above. Pivot now considered sorted",
				"Now do quicksort for left sub-array [1, 1]. Pick first element (1 here) as the pivot",
				"Partition the sub-array, no elements < pivot here, elements >= pivot moved to right sub-array. Pivot now considered sorted",
				"Now do quicksort on right sub-array [1]. Since only one element here, it is considered sorted",
				"Stepping back, we see that this sub-array [1, 1] is sorted",
				"Stepping back further, we see that this sub-array [1, 1, 2] is sorted",
				"Now do quicksort on the right sub-array [5, 9, 4]. Pick the first element (5 here) as the pivot",
				"Partition the sub-array, elements moved as described previously. Pivot now considered sorted",
				"Now do quicksort on left sub-array [4]. Since only one element here, it is considered sorted",
				"Now do quicksort on right sub-array [9]. Since only one element here, it is considered sorted",
				"Stepping back we see that this sub-array [4, 5, 9] is sorted",
				
		};
		
		for (String s : as){
			stepAnnotations.add(s);
		}
		
		// result annotation
		resultAnnotation = "Stepping back fully, we see that the full input array, A, is now sorted, result shown";
		stepAnnotations.add(resultAnnotation);
		
		// result
		result = "[1 1 2 3 4 5 9]";
		
		// multi-display button labels
		String lb1 = "Arbitrary List";
		String lb2 = "Worst Case List";
		
		buttonLabels.add(lb1);
		buttonLabels.add(lb2);
	}

	@Override
	public String getAlgorithmID() {
		return "quickID";
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
		Algorithm a = new SecondQuicksort();
		return a;
	}

	@Override
	public List<String> getMultiDisplayButtonLabels() {
		return buttonLabels;
	}

}
