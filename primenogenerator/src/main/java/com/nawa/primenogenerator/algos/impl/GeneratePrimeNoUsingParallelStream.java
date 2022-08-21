package com.nawa.primenogenerator.algos.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nawa.primenogenerator.algos.PrimeNoGeneratorInterface;

/**
 * This class is responsible for generating the prime nos using Parallel Stream
 * API
 * 
 * @author nawaz
 *
 */
public class GeneratePrimeNoUsingParallelStream implements PrimeNoGeneratorInterface {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePrimeNoBasedOnIteration.class);

	private Long lngUpperLimit;
	private Long lngLowerLimit;

	public GeneratePrimeNoUsingParallelStream(Long lngUpperLimit) {
		/**
		 * Lower Limit is going to be 2 as the lowest prime no
		 */
		this.lngLowerLimit = 2L;
		this.lngUpperLimit = lngUpperLimit;
	}

	@Override
	public List<Long> generatePrimeNumbers() {
		/**
		 * 1. Create a Long Stream between the Lower Limit and Upper Limit
		 * 2. Mark the steam as parallel
		 * 3. Apply the filter to check prime no
		 * 4. Box it to Long.
		 * 5. Collect it as a List of Long
		 * 6. Return the same
		 * 
		 */
		logger.info("Upper Limit: " + lngUpperLimit);
		return LongStream.rangeClosed(lngLowerLimit, lngUpperLimit).parallel().filter(i -> isPrime(i)).boxed().collect(Collectors.toList());
	}

	@Override
	public Long getUpperLimit() {
		return this.lngUpperLimit;
	}
	
	private boolean isPrime(long number) {
        return LongStream
                .rangeClosed(2L, (long) (Math.sqrt(number)))
                .parallel()
                .allMatch(n -> number % n != 0);
    }

}
