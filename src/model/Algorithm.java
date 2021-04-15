package model;

import java.util.List;
/**
 * Interface for definition of algorithms that will be analysed as part of the
 * Algorithm Breakdown section of the system.
 * 
 * @author AidanJoseph
 *
 */
public interface Algorithm {

	public String getAlgorithmID();
	
	public String getAlgorithmTitle();
	
	public String getPseudocode();
	
	public String getFinalDataDisplay();
	
	public String getFinalAnnotationDisplay();
	
	public List<String> getDataSteps();
	
	public List<List<String>> getHighlightedSteps();
	
	public List<String> getPseudoHighlights();
	
	public List<String> getStepAnnotations();
	
	public String getResult();
	
	public String getResultAnnotation();
	
	public boolean isMultiDisplay();
	
	public Algorithm getMultiDisplayAlgorithm();
	
	public List<String> getMultiDisplayButtonLabels();
	
}
