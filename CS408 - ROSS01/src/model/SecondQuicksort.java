package model;

import java.util.ArrayList;
import java.util.List;

public class SecondQuicksort implements Algorithm {
	
	private String pseudocode, title, result, resultAnnotation;
	private List<String> dataSteps, pseudoHighlights, stepAnnotations;
	private List<List<String>> highlightedSteps;
	private Algorithm origAlgorithm;
	
	public SecondQuicksort() {
		dataSteps = new ArrayList<>();
		pseudoHighlights = new ArrayList<>();
		stepAnnotations = new ArrayList<>();
		highlightedSteps = new ArrayList<>();
		
		origAlgorithm = new QuicksortAlgorithm();
		
		// title
		title = "Quicksort (Worst Case Scenario)";
		
		// pseudocode
		String[] ps = { "quicksort(int left, int right)\n      ", "if (left < right)\n          ",
				"int p = partition(left, right)", "\n          ", "quicksort(left, p-1)", "\n          ",
				"quicksort(p+1, right)", "\n\n===================\n\n", "int partition(int left, int right)\n      ",
				"int pivot = A[left]\n      ",
				"for (int i=left+1; i < A.length; i++)\n          ",
				"if (A[i] < pivot)\n              ", "move A[i] to left of pivot\n          ", 
				"else if (A[i] >= pivot)\n              ", "move A[i] to right of pivot\n      ", "return pivot array position",
				"\n\n===================\n\n",
				"input array: A = [9, 5, 4, 3, 2, 1, 1]"
		};
		
		pseudocode = String.join("", ps);
		
		// highlighted pseudocode
		pseudoHighlights.add(ps[2]);
		pseudoHighlights.add(ps[4]);
		
		// data steps
		String[] ds = { "[9 5 4 3 2 1 1]", "[{9} 5 4 3 2 1 1]", "[1 5 4 3 2 1 {9}]\n", "[{1} 5 4 3 2 1]",
				"[{1} 5 4 3 2 1] ", "[{5} 4 3 2 1]", "[1 4 3 2 {5}]", "[{1} 4 3 2]", "[{1} 4 3 2] ",
				"[{4} 3 2]", "[2 3 {4}]", "[{2} 3]", "[{2} 3] ", "[3]", "[2 3]", "[2 3 4]", "[1 2 3 4]",
				"[1 2 3 4 5]", "[1 1 2 3 4 5]"
		};
		
		for (String s : ds){
			dataSteps.add(s);
		}
		
		// highlighted data steps
		List<String> hs1 = new ArrayList<>();
		List<String> hs2 = new ArrayList<>();
		
		hs1.add(ds[1]);
		hs1.add(ds[2]);
		
		for (int i=3; i < ds.length; i++){
			hs2.add(ds[i]);
		}
		
		highlightedSteps.add(hs1);
		highlightedSteps.add(hs2);
		
		// annotations
		String[] as = { "Begin algorithm breakdown for Quicksort, with left = 0, right = A.length - 1",
				"Pick the first element (9 here) as the pivot",
				"Partition the array, all elements < pivot moved to left sub-array, no elements >= pivot here so right sub-array empty. \nPivot now considered sorted",
				"Now do quicksort on left sub-array [1 5 4 3 2 1]. Pick first element (1 here) as the pivot",
				"Partition the array, no elements < pivot here so left sub-array empty, all elements >= pivot moved to right sub-array. \nPivot now considered sorted",
				"Now do quicksort on right sub-array [5 4 3 2 1]. Pick first element (5 here) as the pivot",
				"Partition the array, elements moved as described above (again, right sub-array empty here). Pivot now considered sorted",
				"Now do quicksort on left sub-array [1 4 3 2]. Pick first element (1 here) as the pivot",
				"Partition the array, elements moved as described above (again, left sub-array empty here). Pivot now considered sorted",
				"Now do quicksort on right sub-array [4 3 2]. Pick first element (4 here) as the pivot",
				"Partition the array, elements moved as described above (once again, right sub-array empty here). \nPivot now considered sorted",
				"Now do quicksort on left sub-array [2 3]. Pick first element (2 here) as the pivot",
				"Partition the array, elements moved as desribed above (once again, left sub-array empty here). \nPivot now considered sorted",
				"Now do quicksort on right sub-array [3]. Since only one element here, it is considered sorted",
				"Stepping back we see that the sub-array [2 3] is sorted",
				"Stepping back again we see that the sub-array [2 3 4] is sorted",
				"Stepping back again we see that the sub-array [1 2 3 4] is sorted",
				"Stepping back again we see that the sub-array [1 2 3 4 5] is sorted",
				"Stepping back again we see that the sub-array [1 1 2 3 4 5] is sorted",
		};
		
		for (String s : as){
			stepAnnotations.add(s);
		}
		
		// result annotation
		resultAnnotation = origAlgorithm.getResultAnnotation() + "\n" 
			     + "[This is the worst case scenario for Quicksort since at each step the pivot divides the list into two sub-lists of size 0\n"
			     + " and n-1, meaning Quicksort takes O(n^2) time here (This also occurs for Quicksort performed on an already sorted list)]";
		stepAnnotations.add(resultAnnotation);
		
		// result
		result = origAlgorithm.getResult();
	}

	@Override
	public String getAlgorithmID() {
		return "secondQuickID";
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
