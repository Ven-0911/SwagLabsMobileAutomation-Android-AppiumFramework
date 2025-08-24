package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ProductsPage;
import testUtils.BaseTest;

public class LoginTest extends BaseTest {

	public ProductsPage productpage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;

	@Test(dataProvider = "getLoginData")
	public void loginTest(String username, String password) {
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		productpage = loginPage.clickLogin();
	}

	@Test(dataProvider = "SelectProductFromList", dependsOnMethods = "loginTest")
	public void addProductToCartBySelect(List<String> productList) {
		for (String product : productList) {
			productpage.addProductFromTileView(product);
		}
		
	}

	@Test(dataProvider = "SelectProductDrag", dependsOnMethods = "addProductToCartBySelect")
	public void addProductToCartByDragAndDrop(List<String> productList, List<String> removeProductList, String fname,
			String lname, String pcode, String message) {
		productpage.clickToggle();
		for (String product : productList) {
			productpage.addProductByDrag(product);
		}
		cartPage = productpage.clickCartIcon();
		if (removeProductList != null) {
			for (String product : removeProductList) {
				cartPage.removeProductBySwipe(product);
				cartPage.clickBin();
			}
		}
		checkoutPage = cartPage.checkOutItems();

		checkoutPage.enterFirstname(fname);
		checkoutPage.enterLastName(lname);
		checkoutPage.enterPostalCode(pcode);
		checkoutPage.clickCheckoutContinue();
		checkoutPage.clickCheckoutFinish();
		Assert.assertEquals(checkoutPage.getOrderCompleteMessage(), message);
	}


	@DataProvider
	public Object[][] getLoginData() throws Exception {
		return new Object[][] { { "standard_user", "secret_sauce" } };

	}

	@DataProvider(name = "SelectProductFromList")
	public Object[][] getProductListForSelect() throws Exception {
		List<String> values = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light");
		return new Object[][] { { values } };
	}

	@DataProvider(name = "SelectProductDrag")
	public Object[][] getProductListDrag() throws Exception {
		List<String> values = Arrays.asList("Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket");
		List<String> removeList = Arrays.asList("Sauce Labs Bolt T-Shirt");
		return new Object[][] { { values, removeList, "Test01", "Test02", "90920", "THANK YOU FOR YOU ORDER" } };
	}

}
