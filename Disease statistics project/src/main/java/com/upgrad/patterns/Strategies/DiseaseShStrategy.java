package com.upgrad.patterns.Strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.patterns.Config.RestServiceGenerator;
import com.upgrad.patterns.Entity.DiseaseShResponse;
import com.upgrad.patterns.Interfaces.IndianDiseaseStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Service
public class DiseaseShStrategy implements IndianDiseaseStat {

    private Logger logger = LoggerFactory.getLogger(DiseaseShStrategy.class);

    private RestTemplate restTemplate;

    @Value("${config.diseaseSh-io-url}")
    private String baseUrl;

    public DiseaseShStrategy()
    {
        restTemplate = RestServiceGenerator.GetInstance();
    }

    @Override
    public String GetActiveCount() {
    	//write a try catch block here
    	
    	//try block
    	
	    	//obtain response from the getDiseaseShResponseResponses() method
	    	//store it in an object
	    	
    		//get the response using the getCases() method
	    	//return the response after rounding it up to 0 decimal places
    	
    	
    	//catch block
    		//log the error
    	
    		//return null
    	
    	
    }

    private DiseaseShResponse getDiseaseShResponseResponses() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("DiseaseSh.json");
        return objectMapper.readValue(resource.getFile(), DiseaseShResponse.class);

    }
}
