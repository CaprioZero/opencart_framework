package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePageFactory {
	JavascriptExecutor js;
	
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		js=(JavascriptExecutor) driver;
	}

	// @FindBy(xpath="//button[@aria-expanded='false']")
	@FindBy(xpath = "(//div[@id='header-cart']//child::button)[1]")
	WebElement btnItems;

	@FindBy(xpath = "//strong[normalize-space()='View Cart']")
	WebElement lnkViewCart;

	@FindBy(xpath = "(//strong[contains(text(),'Total')])[4]//following::td")
	WebElement lblTotalPrice; // $246.40

	@FindBy(xpath = "//a[text()='Checkout']")
	WebElement btnCheckout;

	public void clickItemsToNavigateToCart() {
		btnItems.click();
	}

	public void clickViewCart() {
		lnkViewCart.click();
	}

	public String getTotalPrice() {
		return lblTotalPrice.getText();
	}

	public void clickOnCheckout() throws InterruptedException {
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Thread.sleep(2000);
		btnCheckout.click();
	}
}
