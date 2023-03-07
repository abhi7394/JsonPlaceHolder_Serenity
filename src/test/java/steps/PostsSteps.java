package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import utils.Utility;

import java.util.HashMap;
import java.util.Map;

public class PostsSteps 
{
	ValidatableResponse response;
	
	@When("^User sends GET request to the endpoint \"([^\"]*)\"$")
	public void user_sends_GET_request_to_the_endpoint(String endpoint) 
	{
		response = Utility.getRequest(endpoint);
	}

	@Then("^All posts count is not more than (\\d+)$")
	public void all_posts_count_is_not_more_than(int expPostCount) 
	{
		int actualPostCount = response.extract().path("size()");
		Assert.assertTrue(actualPostCount <= expPostCount);
	}

	@Then("^It returns status code \"([^\"]*)\"$")
	public void it_returns_status_code(String statusCode) 
	{
		response.assertThat().statusCode(Integer.parseInt(statusCode));
	}

	@When("^User sends GET request to the \"([^\"]*)\" with id as \"([^\"]*)\"$")
	public void user_sends_GET_request_to_the_with_id_as(String endpoint, String id) 
	{
		response = Utility.getRequest(endpoint  + "?id=" + id);
	}

	@Then("^Verify it has \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void verify_it_has_and(String expId, String expTitle, String expBody) 
	{
		Assert.assertEquals(expId, response.extract().path("id[0]").toString());
        Assert.assertEquals(expTitle, response.extract().path("title[0]"));
        expBody = expBody.replaceAll("\\\\n", "\n");
        Assert.assertEquals(expBody, response.extract().path("body[0]"));
	}

	@When("^User sends POST request to the endpoint \"([^\"]*)\" with payload size greater than (\\d+)mb$")
	public void user_sends_POST_request_to_the_endpoint_with_payload_size_greater_than_mb(String endpoint, int payloadSize) 
	{
		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("userId", 5);
		jsonAsMap.put("title", Utility.getBigText());
		jsonAsMap.put("body", Utility.getBigText());
		response = Utility.postRequest("application/json", endpoint, jsonAsMap);
	}
}

