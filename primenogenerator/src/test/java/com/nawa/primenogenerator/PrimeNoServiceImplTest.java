package com.nawa.primenogenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nawa.primenogenerator.algos.PrimeGenerationAlgo;
import com.nawa.primenogenerator.pojos.PrimeNoResults;
import com.nawa.primenogenerator.service.PrimeNoService;

@SpringBootTest
public class PrimeNoServiceImplTest {

	@Autowired
	PrimeNoService primeNoService;

	@Test
	public void generatePrimeNoTestValid() {
		PrimeNoResults primeNoResult = this.primeNoService.generatePrimeNo(2L,
				Optional.of(PrimeGenerationAlgo.ITERATIVE));
		assertEquals(2L, primeNoResult.getPrimeNos().get(0));
	}

	@Test
	public void generatePrimeNoTestInvalid() {
		PrimeNoResults primeNoResult = this.primeNoService.generatePrimeNo(1L,
				Optional.of(PrimeGenerationAlgo.ITERATIVE));
		assertTrue(primeNoResult.getPrimeNos().isEmpty());
	}

	@Test
	public void generatePrimeNoTestValidParallel() {
		PrimeNoResults primeNoResult = this.primeNoService.generatePrimeNo(2L,
				Optional.of(PrimeGenerationAlgo.PARALLELSTREAM));
		primeNoResult.getPrimeNos();
		assertEquals(2L, primeNoResult.getPrimeNos().get(0));
	}
	
	@Test
	public void genePrimeNoTestValidParallelList() {
		PrimeNoResults primeNoResult = this.primeNoService.generatePrimeNo(5L,
				Optional.of(PrimeGenerationAlgo.PARALLELSTREAM));
		assertTrue(Arrays.asList(2L,3L,5L).containsAll(primeNoResult.getPrimeNos()));
	}

	@Test
	public void generatePrimeNoTestInvalidParallel() {
		PrimeNoResults primeNoResult = this.primeNoService.generatePrimeNo(1L,
				Optional.of(PrimeGenerationAlgo.PARALLELSTREAM));
		primeNoResult.getPrimeNos();
		assertTrue(primeNoResult.getPrimeNos().isEmpty());
	}

	@Test
	public void getResultFromCacheByResultIdTest() {
		PrimeNoResults primeNoResult = this.primeNoService.generatePrimeNo(5L,
				Optional.of(PrimeGenerationAlgo.ITERATIVE));
		Optional<PrimeNoResults> primeNoResultOptional = this.primeNoService
				.getResultByResultIdFromCache(primeNoResult.getUnqiueResultId().toString());
		assertFalse(!primeNoResultOptional.isPresent());
		assertTrue(primeNoResultOptional.get().getUnqiueResultId().toString()
				.equals(primeNoResult.getUnqiueResultId().toString()));

	}

	@Test
	public void getCachedPrimeResultTest()
	{
		this.primeNoService.generatePrimeNo(1L,
				Optional.of(PrimeGenerationAlgo.ITERATIVE));
		List<PrimeNoResults> listOfPrimeNoResults =  this.primeNoService.getCachedPrimeResults();
		assertTrue(!listOfPrimeNoResults.isEmpty());
		
	}

	
}
