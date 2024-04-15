package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	//---Locating elements---
	
	@FindBy(xpath="//div[text()='Use another account']")
	WebElement anotherAcc;
	
	@FindBy(id="identifierId")
	WebElement emailInput;
	
	@FindBy(xpath="//span[text()='Next']")
	WebElement emailNext;
	
	@FindBy(xpath="//div[@class='Ekjuhf Jj6Lae']")
	WebElement errorMessage;
	
	//---Action Methods---
	
	//clicking on Use Another account
	public void clickAnotherAcc() {
		anotherAcc.click();		
	}
	
	//Entering email
	public void setEmail(String email) {
		explicitWait(emailInput);			
		emailInput.sendKeys(email);
		System.out.println(emailInput.getAttribute("data-initial-value"));
	}
	
	//Clicking Next after entering email
	public void clickNext() {
		scrollToElement(emailNext,emailNext);
	}
	
	//Reading and returning the error message
	public String getErrorMessage() {
		return errorMessage.getText();
	}
	

}
