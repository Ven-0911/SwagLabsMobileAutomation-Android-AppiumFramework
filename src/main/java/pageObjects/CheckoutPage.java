package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class CheckoutPage extends AndroidActions {
	
	AndroidDriver driver;

	public CheckoutPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "test-First Name")
	private WebElement firstName;
	
	@AndroidFindBy(accessibility = "test-Last Name")
	private WebElement lastName;
	
	@AndroidFindBy(accessibility = "test-Zip/Postal Code")
	private WebElement postalCode;
	
	@AndroidFindBy(accessibility = "test-FINISH")
	private WebElement finish;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
	private WebElement checkoutContinue;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='THANK YOU FOR YOU ORDER']")
	private WebElement orderComplete;
	
	public void enterFirstname(String fname) {
		firstName.sendKeys(fname);
	}
	
	public void enterLastName(String lname) {
		lastName.sendKeys(lname);
	}
	
	public void enterPostalCode(String pcode) {
		postalCode.sendKeys(pcode);
	}
	
	public void clickCheckoutContinue() {
		checkoutContinue.click();
	}
	
	public void clickCheckoutFinish() {
		scrollToText("FINISH");
		finish.click();
	}
	
	public String getOrderCompleteMessage() {
		return orderComplete.getText();
	}
	

}
