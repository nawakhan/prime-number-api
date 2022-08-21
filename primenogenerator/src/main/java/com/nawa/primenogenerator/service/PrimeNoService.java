package com.nawa.primenogenerator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.nawa.primenogenerator.algos.PrimeGenerationAlgo;
import com.nawa.primenogenerator.pojos.PrimeNoResults;

/**
 * This service contains the logic required to generated or deliver the Prime
 * Numbers
 * 
 * @author nawaz
 *
 */
@Service
public interface PrimeNoService {

	/**
	 * This method returns List of the results cached in the memory
	 * 
	 * @return List of Results Cached
	 */
	List<PrimeNoResults> getCachedPrimeResults();

	/**
	 * This method is responsible for generating the Prime Nos based on the Algo
	 * provided
	 * 
	 * @param upperlimit - Upper Limit till which the Prime numbers should be
	 *                   generated
	 * @param algorithm  - algorithm which should be used to the generate the Prime
	 *                   numbers
	 * @return Returns an Object of the PrimeNoResults class
	 */
	PrimeNoResults generatePrimeNo(Long upperLimit, Optional<PrimeGenerationAlgo> algorithm);

	/**
	 * This method returns the PrimeNoResult based on the resultid if still
	 * available in the cache
	 * 
	 * @param - resultid - UUID of the Response
	 * @return - Optional of the PrimeNoResults class
	 */
	Optional<PrimeNoResults> getResultByResultIdFromCache(final String resultid);

	/**
	 * Clears Cache based on a time interval
	 * 
	 * @param intervalInMinutes
	 */
	void clearCacheOlderThanInterval(long intervalInMinutes);

}
