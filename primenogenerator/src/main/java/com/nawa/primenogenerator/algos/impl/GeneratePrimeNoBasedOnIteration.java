package com.nawa.primenogenerator.algos.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nawa.primenogenerator.algos.PrimeNoGeneratorInterface;

/**
 * This class is responsible for generating and returning Prime Nos based on the
 * Iterative approach.
 * 
 * 
 * @author nawaz
 *
 */
public class GeneratePrimeNoBasedOnIteration implements PrimeNoGeneratorInterface {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePrimeNoBasedOnIteration.class);

	private Long lngUpperLimit;
	private Long lngLowerLimit;

	public GeneratePrimeNoBasedOnIteration(Long lngLowerLimit, Long lngUpperLimit) {
		/**
		 * Lower Limit is going to be 2 as the lowest prime no
		 */
		this.lngLowerLimit = lngLowerLimit;
		this.lngUpperLimit = lngUpperLimit;
	}

	@Override
	public List<Long> generatePrimeNumbers() {
		List<Long> primesNos = new ArrayList<>();
		logger.info("Upper Limit: " + lngUpperLimit);
		for (Long current = lngLowerLimit; current <= lngUpperLimit; current++) {
			/**
			 * If a no is not prime if will always be divisible by a no smaller than the sqroot of itself
			 * Also using Sqrt avoids the check where we need to check if the no is divisible only by itself
			 */
			long sqrRoot = (long) Math.sqrt(current);
			boolean isPrime = true;
			for (long i = 2L; i <= sqrRoot; i++) {
				if (current % i == 0) {
					isPrime = false;
				}
			}
			if (isPrime) {
				primesNos.add(current);
			}
		}

		return primesNos;
	}

	@Override
	public Long getUpperLimit() {
		return this.lngUpperLimit;
	}

}
