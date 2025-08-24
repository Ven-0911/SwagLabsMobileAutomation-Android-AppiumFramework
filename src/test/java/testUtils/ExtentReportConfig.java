package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportConfig {

	public static ExtentReports extentReports;

	public static ExtentReports getReporterObject() {
		// extentReports, extentSparkReport
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter extentSpark = new ExtentSparkReporter(path);
		extentSpark.config().setReportName("Web Automation Results");
		extentSpark.config().setDocumentTitle("Test Results");

		extentReports = new ExtentReports();

		extentReports.attachReporter(extentSpark);

		return extentReports;

	}

}
