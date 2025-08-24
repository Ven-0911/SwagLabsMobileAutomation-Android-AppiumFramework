package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
public abstract class AppiumUtils {
	
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		AppiumDriverLocalService service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\venny\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress(ipAddress).usingPort(port).build();
		service.start();
		return service;
	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		File src = driver.getScreenshotAs(OutputType.FILE);
		String desc = System.getProperty("user.dir")+"//reports"+testCaseName+".png";
		FileUtils.copyFile(src, new File(desc));
		
		return desc;
	}

}
