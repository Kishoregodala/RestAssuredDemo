package com.restassured.restassured;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetuserListTestng {
	RequestSpecification req;
	Response res;
	JsonPath data;
	
	@BeforeTest
	public void init()
	{
		RestAssured.baseURI="https://reqres.in/";	
	}
  @Test(priority=-1)
  public void getData() {
	 req=RestAssured.given();
	 res=req.get("api/users?page=1"); 
	 System.out.println(res.asString());
	 JsonPath data=res.jsonPath();
	 String email=data.getString("data[0].email");
	 System.out.println(email);
	 Assert.assertEquals(email, "george.bluth@reqres.in");

  }
  @Test(priority=0,dataProvider="createUser",dataProviderClass=DataProviderTest.class)
  public void addData(String name, String job) {
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("job", job);
		req.header("Content-Type", "application/json");
		res = req.body(obj.toJSONString()).post("api/users");
		System.out.println(obj);
		 JsonPath data=res.jsonPath();
		 String jobs=data.getString("job");
		 System.out.println(jobs);
		 Assert.assertEquals(jobs, job);
  }
  @AfterTest
  public void deallocateMem() {
	  req = null;
	  res = null;
  }
}
