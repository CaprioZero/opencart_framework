package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePageFactory {
	JavascriptExecutor js;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		js=(JavascriptExecutor) driver;
	}

	@FindBy(xpath = "//input[@id='input-shipping-firstname']")
	WebElement txtfirstName;

	@FindBy(xpath = "//input[@id='input-shipping-lastname']")
	WebElement txtlastName;

	@FindBy(xpath = "//input[@id='input-shipping-address-1']")
	WebElement txtaddress1;

	@FindBy(xpath = "//input[@id='input-shipping-city']")
	WebElement txtcity;

	@FindBy(xpath = "//input[@id='input-shipping-postcode']")
	WebElement txtpin;

	@FindBy(xpath = "//select[@id='input-shipping-country']")
	WebElement drpCountry;

	@FindBy(xpath = "//select[@id='input-shipping-zone']")
	WebElement drpState;

	@FindBy(xpath = "//button[@id='button-shipping-address']")
	WebElement btnContinue;

	@FindBy(xpath = "//button[@id='button-shipping-methods']")
	WebElement btnShippingMethod;

	@FindBy(xpath = "//input[@id='input-shipping-method-flat-flat']")
	WebElement radioFlat;
	
	@FindBy(xpath = "//button[@id='button-shipping-method']")
	WebElement btnContinueShippingMethod;
	
	@FindBy(xpath = "//button[@id='button-payment-methods']")
	WebElement btnPaymentMethod;
	
	@FindBy(xpath = "//input[@id='input-payment-method-cod-cod']")
	WebElement radioCod;
	
	@FindBy(xpath = "//button[@id='button-payment-method']")
	WebElement btnContinuePaymentMethod;

	@FindBy(xpath = "(//strong[contains(text(),'Total')])[4]//following::td")
	WebElement lblTotalPrice;

	@FindBy(xpath = "//button[@id='button-confirm']")
	WebElement btnConfOrder;

	@FindBy(xpath = "//*[@id='content']/h1")
	WebElement lblOrderConMsg;

	public void setfirstName(String firstName) {
		txtfirstName.sendKeys(firstName);
	}

	public void setlastName(String lastName) {
		txtlastName.sendKeys(lastName);
	}

	public void setaddress1(String address1) {
		txtaddress1.sendKeys(address1);
	}

	public void setcity(String city) {
		txtcity.sendKeys(city);
	}

	public void setpin(String pin) {
		txtpin.sendKeys(pin);
	}

	public void setCountry(String Country) {
		new Select(drpCountry).selectByVisibleText(Country);
	}

	public void setState(String State) {
		new Select(drpState).selectByVisibleText(State);
	}

	public void clickOnContinue() {
		btnContinue.click();
	}
	
	public void clickOnShippingMethod() {
		btnShippingMethod.click();;
	}
	
	public void radioShippingCheck() {
		if (radioFlat.isSelected() == false) {
			radioFlat.click();
		}
	}
	
	public void clickOnContinueAfterShippingMethod() {
		btnContinueShippingMethod.click();
	}

	public void clickOnPaymentMethod() {
		btnPaymentMethod.click();
	}
	
	public void radioPaymentCheck() {
		if (radioCod.isSelected() == false) {
			radioCod.click();
		}
	}
	
	public void clickOnContinueAfterPaymentMethod() {
		btnContinuePaymentMethod.click();
	}

	public String getTotalPriceBeforeConfOrder() {
		return lblTotalPrice.getText(); // $207.00

	}

	public void clickOnConfirmOrder() throws InterruptedException {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		btnConfOrder.click();
	}

	public boolean isOrderPlaced() throws InterruptedException {
		try {
			Thread.sleep(2000);
			if (lblOrderConMsg.getText().equals("Your order has been placed!"))
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}

	}

}
