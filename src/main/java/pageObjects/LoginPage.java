package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class LoginPage extends AndroidActions {
	
	AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "test-Username")
	private WebElement user;
	
	@AndroidFindBy(accessibility = "test-Password")
	private WebElement password;
	
	@AndroidFindBy(accessibility = "test-LOGIN")
	private WebElement login;
	
	public void enterUsername(String username) {
		user.sendKeys(username);
	}
	
	public void enterPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public ProductsPage clickLogin() {
		login.click();
		return new ProductsPage(driver);
	}
	
	

}
