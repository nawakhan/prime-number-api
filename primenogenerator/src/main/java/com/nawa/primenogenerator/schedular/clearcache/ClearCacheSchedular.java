package com.nawa.primenogenerator.schedular.clearcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.nawa.primenogenerator.service.PrimeNoService;
import com.nawa.primenogenerator.service.PrimeNoServiceImpl;

/**
 * This schedular is responsible for clearing the cache based on a time interval
 * 
 * @author nawaz
 *
 */
@Configuration
@EnableScheduling
public class ClearCacheSchedular {

	private static final Logger logger = LoggerFactory.getLogger(PrimeNoServiceImpl.class);

	@Autowired
	PrimeNoService primeNoService;
	
    @Scheduled(cron = "0 0/15 * * * *")
	public void clearCache()
	{
    	logger.info("Clear results older than 15 mints");
    	 this.primeNoService.clearCacheOlderThanInterval(15L);
	}
	
}
