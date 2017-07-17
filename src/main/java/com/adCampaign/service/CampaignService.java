package com.adCampaign.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.adCampaign.exception.CampaignNotActiveException;
import com.adCampaign.exception.DataNotFoundException;
import com.adCampaign.model.Campaign;

public class CampaignService {

	private static Map<String, List<Campaign>> adCampaigns = new HashMap<String, List<Campaign>>();

	public List<Campaign> getCampaigns(String partner_id) {

		return adCampaigns.get(partner_id);
	}

	public Campaign startCampaign(Campaign campaign) {
		if (campaign == null) {
			throw new DataNotFoundException("Invalid Campaign received");
		}
		campaign.setCreated(new Date());
		int duration = campaign.getDuration();
		Date time = new Date((campaign.getCreated().getTime()) + duration);
		campaign.setActiveUntil(time);
		if (getCampaigns(campaign.getPartner_id()) != null) {
			getCampaigns(campaign.getPartner_id()).add(campaign);
		} else {
			List<Campaign> campaigns = new ArrayList<Campaign>();
			campaigns.add(campaign);
			adCampaigns.put(campaign.getPartner_id(), campaigns);
		}
		return campaign;
	}

	public List<Campaign> getAllActiveCampaigns() {

		List<Campaign> activeCampaigns = new ArrayList<>();
		for (Entry<String, List<Campaign>> entry : adCampaigns.entrySet()) {
			activeCampaigns.addAll(getActiveCampaignsForPartnerId(entry.getKey()));
		}
		if (activeCampaigns.isEmpty()) {
			throw new CampaignNotActiveException("No active ad campaigns exist at this time");
		}
		return activeCampaigns;
	}

	public List<Campaign> getActiveCampaignsForPartnerId(String partnerId) {

		List<Campaign> campaigns = adCampaigns.get(partnerId);

		if (campaigns == null) {
			throw new DataNotFoundException("No Campaign is present for the Partner Id received");
		}

		List<Campaign> activeCampaigns = campaigns.stream()
				.filter(campaign -> campaign.getActiveUntil().after(new Date())).collect(Collectors.toList());
		if (activeCampaigns.isEmpty()) {
			throw new CampaignNotActiveException(
					"No active ad campaigns exist for the specified partner id " + partnerId);
		}
		return activeCampaigns;

	}

}
