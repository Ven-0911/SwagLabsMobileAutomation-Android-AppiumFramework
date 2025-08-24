package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import pageObjects.LoginPage;
import utils.AppiumUtils;

import org.testng.annotations.BeforeMethod;

public class BaseTest extends AppiumUtils {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public LoginPage loginPage;
	
	@BeforeClass(alwaysRun=true)
	public void setup() throws URISyntaxException, IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		
		String ipAddress = System.getProperty("ipAddress")!=null? System.getProperty("ipAddress"):prop.getProperty("ipAddress");
		service = startAppiumServer(ipAddress, Integer.valueOf(prop.getProperty("port")));
		
		UiAutomator2Options options = new UiAutomator2Options();
		
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\appResources\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
		options.setAppWaitActivity("*");
		
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		loginPage = new LoginPage(driver);
		
		
	}

	@BeforeMethod(alwaysRun=true)
	public void preSetUp() {
//		Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
//		driver.startActivity(activity);
		((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent", "com.swaglabsmobileapp/com.swaglabsmobileapp.MainActivity"));
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
