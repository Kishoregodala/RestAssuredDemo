package objRepo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ObjectRepoTest {
	RequestSpecification req;
	Response res;
	JsonPath data;
	File file;
	FileInputStream fis;
	
	@BeforeTest
	public void init()
	{
		RestAssured.baseURI="https://reqres.in/";	
	}
	@Test
	  public void addData() throws IOException {
		file=new File("C:\\Users\\KGODALA\\eclipse-workspace\\restassured\\src\\test\\java\\objRepo\\Postdata.properties");
		fis=new FileInputStream(file);
		Properties p=new Properties();
		p.load(fis);
		String name=p.getProperty("name");
		String job=p.getProperty("job");
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
