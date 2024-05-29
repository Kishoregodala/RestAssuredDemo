package com.restassured.restassured;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataProviderTest {
	RequestSpecification req;
	Response res;
	JsonPath data;
	@DataProvider(name="createUser")
	public Object [][] getData(){
		Object[][] data=new Object[3][2];
		data[0][0]="Vengatesh";
		data[0][1]="Manager";
		data[1][0]="Praveen";
		data[1][1]="SrEngineering";
		data[2][0]="Prasad";
		data[2][1]="Consultant";
		return data;
	}
	@BeforeTest
	public void init()
	{
		RestAssured.baseURI="https://reqres.in/";	
	}
  @Test(dataProvider="createUser")
  public void addData(String name, String job) {
	  req=RestAssured.given();
	  JSONObject obj=new JSONObject();
		obj.put("name", name);
		obj.put("job", job);
		req.header("Content-Type", "application/json");
		res = req.body(obj.toJSONString()).post("api/users");
		System.out.println(obj);
		 data=res.jsonPath();
		 String jobdata=data.getString("job");
		 System.out.println(jobdata);
		 Assert.assertEquals(jobdata, job);
  }
  @AfterTest
  public void deallocateMem() {
	  req = null;
	  res = null;
	  data = null;
  }
}
