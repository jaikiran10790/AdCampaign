package com.adCampaign.exception;

public class CampaignNotActiveException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5748551874781175513L;
	
	public CampaignNotActiveException(String message){
		super(message);
	}

}
