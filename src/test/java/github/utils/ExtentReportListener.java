package github.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import github.restapi.testbase.TestBase;

public class ExtentReportListener extends TestBase implements ITestListener {
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest test;
	Logger logger = LogManager.getLogger();

	public void onStart(ITestContext context) {
		System.out.println("onStart()");
		logger.info("Starting class :" + context.getName());
		reports = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\extent_report.html");
		spark.config().setDocumentTitle("RestAPITesting");
		spark.config().setReportName("GitHubRestAPIReport");
		spark.config().setTheme(Theme.DARK);
		reports.attachReporter(spark);
		reports.setSystemInfo("OS", "windows10");
		reports.setSystemInfo("author", "Gayatri");
		reports.setSystemInfo("environment", "Test");
		test = reports.createTest(context.getName());
		test.log(Status.INFO, context.getName() + " started!");
		logger.info("onStart() finish");

	}

	public void onTestSkipped(ITestResult result) {
		logger.warn("Test skipped " + result.getMethod().getMethodName());
		test.log(Status.SKIP, result.getName() + " was skipped");
	}

	public void onTestFailure(ITestResult result) {
		logger.warn("Test failed " + result.getMethod().getMethodName());
		test.log(Status.FAIL, result.getName() + " has failed");
		test.fail(result.getThrowable());

	}

	public void onTestSuccess(ITestResult result) {
		logger.info("Test succeeded " + result.getMethod().getMethodName());
		test.log(Status.PASS, result.getName() + " has succeded");

	}

	public void onFinish(ITestContext context) {
		logger.info("Total tests pass :" + context.getPassedTests().size() + "\nTotal tests skipped :"
				+ context.getSkippedTests().size() + "\nTotal tests failed :" + context.getFailedTests().size());
		test.log(Status.INFO, "Total Failed tests: " + context.getFailedTests().size() + ".Total passed tests:"
				+ context.getPassedTests().size() + ". Total skipped tests:" + context.getSkippedTests().size());
		reports.flush();
	}

}
