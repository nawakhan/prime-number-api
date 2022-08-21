package com.nawa.primenogenerator.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nawa.primenogenerator.algos.PrimeGenerationAlgo;
import com.nawa.primenogenerator.algos.PrimeNoGeneratorInterface;
import com.nawa.primenogenerator.algos.factory.PrimeNoGeneratorAlgoFactory;
import com.nawa.primenogenerator.pojos.PrimeNoResults;

/**
 * This service contains the logic required to generated or deliver the Prime
 * Numbers
 * 
 * @author nawaz
 *
 */
@Service
public class PrimeNoServiceImpl implements PrimeNoService {
	
	private static final Logger logger = LoggerFactory.getLogger(PrimeNoServiceImpl.class);


	/**
	 * This is in memory implementation of the Cache
	 */
	Map<String, PrimeNoResults> resultCached = new HashMap<String, PrimeNoResults>();

	/**
	 * This method returns List of the results cached in the memory
	 * 
	 * @return List of Results Cached
	 */
	public List<PrimeNoResults> getCachedPrimeResults() {
		return this.resultCached.values().stream().collect(Collectors.toList());
	}

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
	public PrimeNoResults generatePrimeNo(Long upperLimit, Optional<PrimeGenerationAlgo> algorithm) {
		PrimeNoResults primeNoResult = new PrimeNoResults(upperLimit);
		if (algorithm != null && algorithm.get() != null) {
			PrimeNoGeneratorInterface primeNoGeneratorInterfaceAlgo = PrimeNoGeneratorAlgoFactory
					.getAlgorithem(upperLimit, algorithm.get());
			List<Long> listOfPrimeNos = primeNoGeneratorInterfaceAlgo.generatePrimeNumbers();

			primeNoResult.addPrimeNos(listOfPrimeNos);
			/**
			 * Caching the result in the memory
			 */
			logger.info("Result was cached with the resultid : " +  primeNoResult.getUnqiueResultId().toString());
			resultCached.put(primeNoResult.getUnqiueResultId().toString(), primeNoResult);

		}
		return primeNoResult;
	};

	/**
	 * This method returns the PrimeNoResult based on the resultid if still
	 * available in the cache
	 * 
	 * @param - resultid - UUID of the Response
	 * @return - Optional of the PrimeNoResults class
	 */
	public Optional<PrimeNoResults> getResultByResultIdFromCache(final String resultid) {
		return this.resultCached.values().stream()
				.filter(result -> result.getUnqiueResultId().toString().equals(resultid)).findAny();
	}

}
