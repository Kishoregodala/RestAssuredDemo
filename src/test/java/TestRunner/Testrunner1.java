package TestRunner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(features="src\\test\\resource\\Features\\AddUser.feature",
glue="step",
tags="@users",
//dryRun=true,
monochrome=false,
plugin= {"pretty", "html:target/reqresReport/UserReport.html",
		"json:target/JsonReport/UserReport.json",
		"junit:target/JunitReport/UserReport.xml",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class Testrunner1 extends AbstractTestNGCucumberTests {

}
