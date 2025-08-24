package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ProductsPage extends AndroidActions {

	AndroidDriver driver;

	public ProductsPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Toggle']")
	private WebElement toggleButton;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Cart']")
	private WebElement cart;

	public void addProductFromTileView(String product) {
		driver.findElement(By.xpath("//android.widget.TextView[@text='" + product
				+ "']/following-sibling::android.view.ViewGroup[@content-desc='test-ADD TO CART']")).click();
	}
	
	public CartPage clickCartIcon() {
		cart.click();
		return new CartPage(driver);
	}

	public void clickToggle() {
		toggleButton.click();
	}

	public void addProductByDrag(String product) {
			WebElement dragElement = driver.findElement(By.xpath("//android.widget.TextView[@text='" + product
					+ "']/following-sibling::android.view.ViewGroup[@content-desc='test-Drag Handle']/child::android.widget.TextView"));
			dragGestureAction(dragElement);
	}

}
