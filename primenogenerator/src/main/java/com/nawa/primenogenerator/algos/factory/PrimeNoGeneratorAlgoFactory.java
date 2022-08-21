package com.nawa.primenogenerator.algos.factory;

import com.nawa.primenogenerator.algos.PrimeGenerationAlgo;
import com.nawa.primenogenerator.algos.PrimeNoGeneratorInterface;
import com.nawa.primenogenerator.algos.impl.GeneratePrimeNoBasedOnIteration;
import com.nawa.primenogenerator.algos.impl.GeneratePrimeNoUsingParallelStream;

/**
 * This is a Factory class which provides impl of the PrimeNoGeneratorInterface
 * 
 * @author nawaz
 *
 */
public class PrimeNoGeneratorAlgoFactory {

	public static PrimeNoGeneratorInterface getAlgorithem(Long upperLimit, PrimeGenerationAlgo algorithm) {
		PrimeNoGeneratorInterface primeNoGeneratorInterface = null;
		switch (algorithm) {

		case ITERATIVE:
			primeNoGeneratorInterface = new GeneratePrimeNoBasedOnIteration(2L, upperLimit);
			break;

		case PARALLELSTREAM:
			primeNoGeneratorInterface = new GeneratePrimeNoUsingParallelStream(upperLimit);
			break;

		default:
			primeNoGeneratorInterface = new GeneratePrimeNoBasedOnIteration(2L, upperLimit);
			break;

		}
		return primeNoGeneratorInterface;

	}

}
