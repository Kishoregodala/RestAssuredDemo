package step;



import org.json.simple.JSONObject;
import org.testng.Assert;
import java.util.List;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddUserTest {
	RequestSpecification req;
	Response res;
	JsonPath path;
	JSONObject obj;
	String expJ,expN;
//-------------------------Scenario 1----------------------------------
	/*
	 * Created By: Kishore
	 * Reviewed By: Deepali Dhole
	 * Description: This step is used for adding the user
	 */
	@Given("User is on reqres URL")
	public void user_is_on_reqres_url() {
	    RestAssured.baseURI="https://reqres.in/";
	    req=RestAssured.given();
	    System.out.println("Gievn step is completed");
	}

	@When("^User enters the (.*)and (.*)$")
	public void user_enters_the_and(String name, String job) {
	   obj= new JSONObject();
	   obj.put("name", name);
	   obj.put("job", job);
	   expJ=job;
	   expN=name;
	   req.header("Content-Type", "application/json");
	   System.out.println(obj);
	   System.out.println("When step is completed");
	}

	@And("users hits the user API")
	public void users_hits_the_user_api() {
	    res=req.body(obj.toJSONString()).post("api/users");
	    System.out.println("And step is completed");
	}

	@Then("Users are added to list")
	public void users_are_added_to_list() {
	   System.out.println(res.asPrettyString());
	   path=res.jsonPath();
	   String job1=path.getString("job");
	   System.out.println(job1);
	   Assert.assertEquals(job1, expJ);
	}
	//-------------------------Scenario 2----------------------------------
		/*
		 * Created By: Kishore
		 * Reviewed By: Deepali Dhole
		 * Description: This step is used for updating the user
		 */
	@When("User enters name & job")
	public void user_enters_name_job(DataTable data) {
	   List<List<String>> udata= data.asLists(String.class);
	   String name=udata.get(0).get(0);
	   String job=udata.get(0).get(1);
	   
	   //Single value
	  // List<String> cell= data.asList(String.class);
	   //String name= cell.get(0);
	   obj=new JSONObject();
	   obj.put("name", name);
	   obj.put("job", job);
	   expJ=job;
	   expN=name;
	   req.header("Content-Type", "application/json");
	   System.out.println(obj);
	   System.out.println("When step is completed");
	}  
	@And("user hits the API")
	public void user_hits_the_api() {
	   res=req.body(obj.toJSONString()).put("api/users/2");
	   System.out.println("And step is completed");
	}

	@Then("User data is updated")
	public void user_data_is_updated() {
		System.out.println(res.asPrettyString());
		   path=res.jsonPath();
		   String job1=path.getString("job");
		   System.out.println(job1);
		   Assert.assertEquals(job1, expJ); 
	}
	
	
}
