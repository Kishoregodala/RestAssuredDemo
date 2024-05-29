package com.restassured.restassured;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Testingparameter {
	RequestSpecification req;
	Response res;
	JsonPath data;
	
	@BeforeTest
	public void init()
	{
		RestAssured.baseURI="https://reqres.in/";	
	}
  
  @Test()
  @Parameters({"name","job"})
  public void addData(String name, String job) {
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("job", job);
		req.header("Content-Type", "application/json");
		res = req.body(obj.toJSONString()).post("api/users");
		System.out.println(obj);
		  data=res.jsonPath();
		 String jobs=data.getString("job");
		 System.out.println(jobs);
		 Assert.assertEquals(jobs, job);
  }
  @AfterTest
  public void deallocateMem() {
	  req = null;
	  res = null;
	  data= null;
  }
}
