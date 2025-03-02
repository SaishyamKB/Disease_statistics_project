package com.upgrad.patterns.Strategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upgrad.patterns.Config.RestServiceGenerator;
import com.upgrad.patterns.Entity.JohnHopkinResponse;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JohnHopkinsStrategy implements IndianDiseaseStat {

	private Logger logger = LoggerFactory.getLogger(JohnHopkinsStrategy.class);

	private RestTemplate restTemplate;

	@Value("${config.john-hopkins-url}")
	private String baseUrl;

	public JohnHopkinsStrategy() {
		restTemplate = RestServiceGenerator.GetInstance();
	}

	@Override
	public String GetActiveCount() {


		//try block
			//get response from the getJohnHopkinResponses method
			//filter the data based such that country equals India (use getCountry() to get the country value)
			//Map the data to "confirmed" value (use getStats() and getConfirmed() to get stats value and confirmed value)
			//Reduce the data to get a sum of all the "confirmed" values
			//return the response after rounding it up to 0 decimal places
		//catch block
			//log the error
			//return null
			try {
				// Get response from the JSON file using the getJohnHopkinResponses method
				JohnHopkinResponse[] responses = getJohnHopkinResponses();
				
				// Process the array to filter for "India", map to confirmed cases, and sum them up
				double totalConfirmed = Arrays.stream(responses)
					.filter(response -> "India".equalsIgnoreCase(response.getCountry()))
					.mapToDouble(response -> response.getStats().getConfirmed())
					.sum();
				
				// Round the sum to 0 decimal places
				long roundedTotal = Math.round(totalConfirmed);
				
				// Return the rounded count as a String
				return String.valueOf(roundedTotal);
			} catch (IOException e) {
				// Log the error if any exception occurs during processing
				logger.error("Error fetching active count from JohnHopkins source", e);
				return null;
			}



	}

	private JohnHopkinResponse[] getJohnHopkinResponses() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		ClassPathResource resource = new ClassPathResource("JohnHopkins.json");
		return new JohnHopkinResponse[]{objectMapper.readValue(resource.getFile(), JohnHopkinResponse.class)};
	}
}
