package com.restassured.restassured;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthorizationTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://bookstore.toolsqa.com/";
		JSONObject obj=new JSONObject();
		obj.put("userName", "UserTesting1");
		obj.put("password", "UserTestingPassword@123");
		RequestSpecification req=RestAssured.given().auth().
				basic("UserTesting1", "UserTestingPassword@123").
				header("Content-Type","application/json").
				body(obj.toJSONString());
		Response res=req.post("Account/v1/Authorized");
		String data=res.asString();
		System.out.println(data);
		RequestSpecification req1=RestAssured.given().header("Content-Type","application/json").
				body(obj.toJSONString());
		Response res1=req1.post("Account/v1/GenerateToken");
		System.out.println(res1.asString());
	}

}
