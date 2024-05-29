package com.restassured.restassured;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Byusingpatch {

	public static void main(String[] args) {
		RestAssured.baseURI="https://reqres.in/";
		RequestSpecification req=RestAssured.given();
		JSONObject obj=new JSONObject();
		obj.put("name", "Venkat");
		obj.put("job", "JIOManager");
		req.body(obj.toJSONString());
		Response res=req.patch("api/users/2");
		System.out.println(res.asPrettyString());
		System.out.println(res.getStatusLine());
	}

}
