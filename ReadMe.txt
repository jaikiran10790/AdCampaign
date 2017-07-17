This document explains steps to run the application locally step wise:

-> Import the project as an existing maven project and build it.
-> Add the project onto your localhost server and run it.
-> The project consists of Integration tests which can help hit the resource classes which
  implement the functionality in CampaignResourceClientTest class.
  
 -> To successfully run the Integration tests, please change the URI in the CampaignResourceClientTest class to point to your local server's port(Mine is 8080).
 	 The rest of the url can be the same.
 -> To hit the webservice using Webservice testing tools like Postman, 
 	url would be http://localhost:<yourPort>/AdCampaign/webapi/ads for Post request and Get requests to get all the activeCampaigns.
 	and http://localhost:8080/AdCampaign/webapi/ads/<Partner_id> for getting all active campaigns associated with a partner id.
 	
-> The Unit test cases for the exceptions are in the class CampaignUnitTests.

-> I have tried to implement the bonus cases also in this project.