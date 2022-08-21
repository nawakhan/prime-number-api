package com.nawa.primenogenerator.algos;

import java.util.List;

/**
 * This interface defines the methods to be overridden by different alogorithem imlementations
 * 
 * @author nawaz
 *
 */
public interface PrimeNoGeneratorInterface {
	
	/**
	 * Method to return the Prime Numbers
	 * 
	 * @return - List of Prime Numbers till the upper limit
	 */
	List<Long> generatePrimeNumbers();
	
	/**
	 * Get the upper limit till which the Prime Number List should be provided
	 * 
	 * @return - Upper Limit
	 */
	Long getUpperLimit();
	
	
	
}
