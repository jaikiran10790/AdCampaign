package com.adCampaign.clientTests;

import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.adCampaign.exception.CampaignNotActiveException;
import com.adCampaign.exception.DataNotFoundException;
import com.adCampaign.model.Campaign;
import com.adCampaign.service.CampaignService;

public class CampaignUnitTests {
	
	static CampaignService campaignService = new CampaignService();
	static Campaign campaign = new Campaign();
	
	@After
	public  void setup(){
		Campaign campaign = new Campaign();
		campaign.setCreated(new Date());
		campaign.setAd_content("Jaikiran's Ad");
		campaign.setDuration(10000);
		campaign.setPartner_id("jaikiran");
		Date time = new Date((campaign.getCreated().getTime()) + campaign.getDuration());
		campaign.setActiveUntil(time);
		campaignService.startCampaign(campaign);
	}

	@Test(expected=DataNotFoundException.class)
	public void startCampaignNullInput(){
		campaignService.startCampaign(null);
		
	}
	
	
	@Test(expected=CampaignNotActiveException.class)
	public void getAllActiveCampaignsNoActiveCampaigns(){
		
		campaignService.getAllActiveCampaigns();
	}
	
	@Test(expected=DataNotFoundException.class)
	public void getActiveCampaignsForPartnerIdInvalid(){
		
		campaignService.getActiveCampaignsForPartnerId("jaikiran1");
		
	}
	
	@Test
	public void getActiveCampaignsForPartnerId(){
		List<Campaign> campaigns = campaignService.getActiveCampaignsForPartnerId("jaikiran");
		assertFalse(campaigns.isEmpty());
		
	}
	
	
}
