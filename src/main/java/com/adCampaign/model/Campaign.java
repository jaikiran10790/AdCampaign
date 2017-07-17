package com.adCampaign.model;

import java.util.Date;

public class Campaign {

	private String partner_id;
	private int duration;
	private Date created;
	private Date activeUntil;
	private String ad_content;

	public Campaign() {

	}

	public Campaign(String partner_id, int duration, Date created, Date activeUntil, String ad_content) {
		super();
		this.duration = duration;
		this.created = new Date();
		this.activeUntil = activeUntil;
		this.ad_content = ad_content;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(Date activeUntil) {
		this.activeUntil = activeUntil;
	}

	public String getAd_content() {
		return ad_content;
	}

	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Campaign [partner_id=" + partner_id + ", duration=" + duration + ", created=" + created
				+ ", activeUntil=" + activeUntil + ", ad_content=" + ad_content + "]";
	}
}
