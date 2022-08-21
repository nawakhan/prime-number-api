package com.nawa.primenogenerator.pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PrimeNoResults {

	private final Long upperLimit;
	private final List<Long> primeNos;
	private final UUID unqiueResultId;
	private String errorMessage;
	@JsonIgnore
    private final LocalDateTime crtTime;


	public PrimeNoResults(Long upperLimit) {
		this.unqiueResultId = UUID.randomUUID();
		this.upperLimit = upperLimit;
		this.primeNos = new ArrayList<>();
		this.crtTime = LocalDateTime.now();
	}

	public LocalDateTime getCrtTime() {
		return crtTime;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Long getUpperLimit() {
		return upperLimit;
	}

	public List<Long> getPrimeNos() {
		return primeNos;
	}

	public UUID getUnqiueResultId() {
		return unqiueResultId;
	}

	public boolean addPrimeNo(Long primeNo) {
		return this.primeNos.add(primeNo);
	}

	public boolean addPrimeNos(List<Long> primeNos) {
		return this.primeNos.addAll(primeNos);
	}
}
