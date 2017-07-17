package com.adCampaign.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.adCampaign.model.Campaign;
import com.adCampaign.service.CampaignService;

@Path("/ads")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CampaignResource {

	CampaignService campaignService= new CampaignService();
	
	@POST
	public Campaign startCampaign(Campaign campaign){
		return campaignService.startCampaign(campaign);
				
	}
	
	@GET
	@Path("/{partner_id}")
	public List<Campaign> getCampaign(@PathParam("partner_id")String partner_id){
		
		return campaignService.getActiveCampaignsForPartnerId(partner_id);
	}
	
	@GET
	public List<Campaign> getAllCampaigns(){
		
		return campaignService.getAllActiveCampaigns();
	}
		
}
