package runner;

import java.io.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import commonLibrary.CommonLibrary;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import listener.Reporter;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features/SalesForce.feature", glue = { "stepDefinition" }, plugin = {
		"listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, monochrome = true, tags = {
				"@regression" })

public class TestRunner {

	@AfterClass
	public static void teardown() {
		CommonLibrary.closeAllWindows();
		Reporter.loadXMLConfig(new File("config/extent-config.xml"));
		// Reporter.setSystemInfo("user", System.getProperty("user.name"));
		// Reporter.setSystemInfo("os", "Mac OSX");
		Reporter.setTestRunnerOutput("Sample test runner output message");
		CommonLibrary.closeAllWindows();
	}
}
// import java.io.File;
//
// import org.junit.AfterClass;
// import org.junit.runner.RunWith;
//
// import cucumber.api.CucumberOptions;
// import cucumber.api.junit.Cucumber;
// import com.cucumber.listener.Reporter;
//
// import com.aventstack.extentreports.reporter.*;
//
// @RunWith(Cucumber.class)
// @CucumberOptions(features = "features/Amazon.feature", glue = {
// "stepDefinition" }, plugin = { "html:target/cucumber-html-report"/*,
// "json:target/cucumber.json", "junit:target/cucumber-results.xml",
// "usage:target/cucumber-usage.json"*/,
// "pretty:target/cucumber-pretty.txt" },tags= {"~@sanity"})
//
//
// public class TestRunner {
//
// @AfterClass
// public static void writeExtentReport() {
// Reporter.loadXMLConfig(new
// File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
// }
// }
