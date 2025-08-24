package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class CartPage extends AndroidActions {

	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='test-Delete']/descendant::android.widget.TextView[@text='󰩺']")
	private WebElement bin;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CHECKOUT']")
	private WebElement checkout;

	public void removeProductBySwipe(String productName) {
		WebElement swipeElement = driver
				.findElement(By.xpath("//android.widget.TextView[@text='Sauce Labs Bolt T-Shirt']"));
		swipeGestureAction(swipeElement, "left");

	}
	
	public void clickBin() {
		bin.click();
	}
	
	public CheckoutPage checkOutItems() {
		scrollToText("CHECKOUT");
		checkout.click();
		return new CheckoutPage(driver);
	}
	
	
	
	

}
