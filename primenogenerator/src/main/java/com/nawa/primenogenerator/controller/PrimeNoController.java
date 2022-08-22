package com.nawa.primenogenerator.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nawa.primenogenerator.algos.PrimeGenerationAlgo;
import com.nawa.primenogenerator.pojos.PrimeNoResults;
import com.nawa.primenogenerator.service.PrimeNoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * This REST controller is responsible for the Request related to the Prime
 * Number Generation
 * 
 * TODO: Need more swagger documentation
 * 
 * @author nawaz
 *
 */

@RestController
@RequestMapping("/api/v1/primenos")
@Api(tags = "Prime Number Controller, It contains end point for generating the Prime numbers")
public class PrimeNoController {

	@Autowired
	PrimeNoService primeNoService;

	/**
	 * This endpoint returns the type of the algos supported by this API for
	 * generating the prime numbers
	 * 
	 * @return - List of Algos
	 */
	@RequestMapping(value = "/algorithms", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Get the alorithms which can be used to generate the Prime Numbers")
	public ResponseEntity<List<String>> getAlgos() {
		return new ResponseEntity<>(
				Stream.of(PrimeGenerationAlgo.values()).map(PrimeGenerationAlgo::name).collect(Collectors.toList()),
				HttpStatus.OK);
	}
	
	/**
	 * This End point returns the Prime Number generated till the upper Limit provided
	 * This end point uses ITERATIVE algo as default
	 * 
	 * @param upperLimit -  - Upper limit till which the prime numbers should be
	 *                      generated
	 * @return
	 */
	@RequestMapping(value = "/upperLimit/{upperLimit}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "Generate prime numbers till the upper limit privided, Algo will be defaulted to the ITERATIVE")
	public ResponseEntity<PrimeNoResults> generatePrimes(
			@ApiParam(value = "the upper limit till which the prime numbers should be generated") @PathVariable("upperLimit") Long upperLimit) {
		return new ResponseEntity<>(this.primeNoService.generatePrimeNo(upperLimit, Optional.of(PrimeGenerationAlgo.ITERATIVE)),
				HttpStatus.OK);
	}

	/**
	 * This method is mapped to HTTP get and responsible for generating the prime
	 * numbers and return the same in the response
	 * 
	 * @param algorithmName - Algorithm which can be used to generate the prime
	 *                      numbers
	 * @param upperLimit    - Upper limit till which the prime numbers should be
	 *                      generated
	 * @return - Returns a ResponseEntity containing PrimeNoResults Object
	 */
	@RequestMapping(value = "/algorithmName/{algorithmName}/upperLimit/{upperLimit}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Generate prime numbers till the upper limit privided, Please check the /algorithms end point for the algos supported")
	public ResponseEntity<PrimeNoResults> generatePrimesByAlgo(
			@ApiParam(value = "The Algorithm which should be used to calculate the Prime Nos") @PathVariable("algorithmName") PrimeGenerationAlgo algorithmName,

			@ApiParam(value = "the upper limit till which the prime numbers should be generated") @PathVariable("upperLimit") Long upperLimit) {
		return new ResponseEntity<>(this.primeNoService.generatePrimeNo(upperLimit, Optional.of(algorithmName)),
				HttpStatus.OK);
	}
	

	/**
	 * This method takes the result id as parameter and if a result is still
	 * available in the memory cache it will fetch that and return the result from
	 * the cache
	 * 
	 * @param resultId
	 * @return Returns a ResponseEntity containing PrimeNoResults Object
	 */
	@RequestMapping(value = "/{uuid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Return the result from Cache by the Result Id")
	public ResponseEntity<PrimeNoResults> getPrimeResultByResultId(
			@ApiParam(value = "UUID of the Result") @PathVariable("uuid") String resultId) {
		Optional<PrimeNoResults> resultOptional = this.primeNoService.getResultByResultIdFromCache(resultId);
		PrimeNoResults primeNoResults;
		if (resultOptional.isPresent()) {
			primeNoResults = resultOptional.get();
		} else {
			primeNoResults = new PrimeNoResults(null);
			primeNoResults.setErrorMessage("Result not found for the UUID: " + resultId);
		}
		return new ResponseEntity<>(primeNoResults, HttpStatus.OK);
	}

}
