package com.restassured.restassured;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ExcelReaderTest1 {
		RequestSpecification req;
		Response res;
		JsonPath data;
		File file;
		FileInputStream fis;
		XSSFWorkbook w;
		XSSFSheet s;
		@BeforeTest
		public void init()
		{
			RestAssured.baseURI="https://reqres.in/";	
		}
		@Test
		  public void addData() throws IOException {
			file=new File("C:\\Users\\KGODALA\\eclipse-workspace\\restassured\\src\\test\\resource\\Exceldata\\Data1.xlsx");
			fis=new FileInputStream(file);
			w=new XSSFWorkbook(fis);
			s=w.getSheet("userdata");
			int row=s.getPhysicalNumberOfRows();
			System.out.println("Rows"+row);
			int col=s.getRow(0).getPhysicalNumberOfCells();
			System.out.println("Column"+col);
			String name=s.getRow(0).getCell(0).toString();
			String job=s.getRow(0).getCell(1).getStringCellValue();
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
