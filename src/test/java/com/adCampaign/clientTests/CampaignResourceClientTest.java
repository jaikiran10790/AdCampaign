package com.adCampaign.clientTests;


import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.adCampaign.model.Campaign;
public class CampaignResourceClientTest {

	private static String URI = "http://localhost:8080/AdCampaign/webapi/ads";
	@BeforeClass
	public static void setup(){
		Campaign campaign = new Campaign();
		campaign.setCreated(new Date());
		campaign.setAd_content("Jaikiran's Ad");
		campaign.setDuration(100000);
		campaign.setPartner_id("jaikiran");
		Date time = new Date((campaign.getCreated().getTime()) + campaign.getDuration());
		campaign.setActiveUntil(time);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(URI,campaign, Campaign.class);
		System.out.println("Campaign added before running a test case "+campaign.toString());
	}
	
	@Test
	public void startCampaign() {
		Campaign campaign = new Campaign();
		campaign.setCreated(new Date());
		campaign.setAd_content("Jaikiran's 2nd Ad ");
		campaign.setDuration(100000);
		campaign.setPartner_id("jaikiran");
		Date time = new Date((campaign.getCreated().getTime()) + campaign.getDuration());
		campaign.setActiveUntil(time);
		RestTemplate restTemplate = new RestTemplate();
		Campaign result = restTemplate.postForObject(URI,campaign, Campaign.class);
		Assert.assertNotNull(result);
		Assert.assertEquals("partner id not matching", campaign.getPartner_id(), result.getPartner_id());
		System.out.println("Campaign successfully added"+campaign.toString());
		
	}
	
	@Test
	public void getAllActiveCampaigns(){
			RestTemplate restTemplate = new RestTemplate();
			
			List<Campaign> campaigns = restTemplate.getForObject(URI, List.class);
			assertFalse(campaigns.isEmpty());
			System.out.println("Active campaigns are: "+campaigns.toString());
	}
	
	@Test
	public void getActiveCampaignsForPartnerId(){
			RestTemplate restTemplate = new RestTemplate();
			
			List<Campaign> campaigns = restTemplate.getForObject(URI+"/jaikiran", List.class);
			assertFalse(campaigns.isEmpty());
			System.out.println("Campaigns associated with the partner Id are : "+campaigns.toString());
	}
	
	@Test(expected=HttpClientErrorException.class)
	public void getActiveCampaignsForInvalidInput() {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List> result = 
				    restTemplate.exchange(URI+"/jaikiran1",  HttpMethod.GET, null, List.class, "jaikiran1");
			System.out.println("Exception has occured");

	}
	
}
