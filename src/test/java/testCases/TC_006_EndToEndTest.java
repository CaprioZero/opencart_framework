package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.CheckoutPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.SearchPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

public class TC_006_EndToEndTest extends BaseClass {

	@Test(groups = { "Regression" })
	public void endToendTest() throws InterruptedException {
		logger.info(" Starting TC_006_EndToEndTest ");
		// Soft assertions
		SoftAssert myassert = new SoftAssert();

		// Account Registration
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();

		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());

		String email = randomString() + "@gmail.com";
		regpage.setEmail(email);// randomly generated the email

		regpage.setPassword("test123");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		Thread.sleep(3000);

		String confmsg = regpage.getConfirmationMsg();
		System.out.println(confmsg);

		myassert.assertEquals(confmsg, "Your Account Has Been Created!"); // validation

		MyAccountPage mc = new MyAccountPage(driver);
		mc.clickLogout();
		Thread.sleep(3000);

		// Login
		hp.clickMyAccount();
		hp.clickLogin();

		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword("test123");
		lp.clickLogin();

		System.out.println("Going to My Account Page? " + mc.isMyAccountPageExists());
		myassert.assertEquals(mc.isMyAccountPageExists(), true); // validation

		// search & add product to cart
		hp.enterProductName("iPhone");
		hp.clickSearch();

		SearchPage sp = new SearchPage(driver);

		if (sp.isProductExist("iPhone")) {
			sp.selectProduct("iPhone");
			sp.setQuantity("2");
			sp.addToCart();

		}
		Thread.sleep(3000);
		System.out.println("Added product to cart ? " + sp.checkConfMsg());
		myassert.assertEquals(sp.checkConfMsg(), true);

		// Shopping cart
		ShoppingCartPage sc = new ShoppingCartPage(driver);
		Thread.sleep(3000);
		sc.clickItemsToNavigateToCart();
		Thread.sleep(3000);
		sc.clickViewCart();
		Thread.sleep(3000);
		String totprice = sc.getTotalPrice();
		System.out.println("total price is shopping cart: " + totprice);
		myassert.assertEquals(totprice, "$246.40"); // validation
		Thread.sleep(3000);
		sc.clickOnCheckout(); // navigate to checkout page
		Thread.sleep(3000);

		// Checkout Product
		CheckoutPage ch = new CheckoutPage(driver);

		ch.setfirstName(randomString().toUpperCase());
		Thread.sleep(1000);
		ch.setlastName(randomString().toUpperCase());
		Thread.sleep(1000);
		ch.setaddress1("address1");
		Thread.sleep(1000);
		ch.setcity("Ho Chi Minh");
		Thread.sleep(1000);
		ch.setpin("70000");
		Thread.sleep(1000);
		ch.setCountry("Viet Nam");
		Thread.sleep(1000);
		ch.setState("Ho Chi Minh City");
		Thread.sleep(1000);
		ch.clickOnContinue();
		Thread.sleep(1000);
		ch.clickOnShippingMethod();
		Thread.sleep(1000);
		ch.radioShippingCheck();
		Thread.sleep(1000);
		ch.clickOnContinueAfterShippingMethod();
		Thread.sleep(1000);
		ch.clickOnPaymentMethod();
		Thread.sleep(1000);
		ch.radioPaymentCheck();
		Thread.sleep(1000);
		ch.clickOnContinueAfterPaymentMethod();
		Thread.sleep(1000);

		String total_price_inOrderPage = ch.getTotalPriceBeforeConfOrder();
		System.out.println("total price in confirmed order page:" + total_price_inOrderPage);
		myassert.assertEquals(total_price_inOrderPage, "$207.00"); // validation

		ch.clickOnConfirmOrder();
		Thread.sleep(3000);

		boolean orderconf = ch.isOrderPlaced();
		System.out.println("Is Order Placed? " + orderconf);
		myassert.assertEquals(ch.isOrderPlaced(), true);

		myassert.assertAll(); // conclusion
		logger.info(" Finished TC_006_EndToEndTest ");
	}

}
