package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UpcomingBikesPage extends BasePage {
	
	public UpcomingBikesPage(WebDriver driver) {
		super(driver);
		
	}
	
	//---Locating elements---
	
	@FindBy(xpath ="//select[@id='makeId']")
	WebElement manufacturer;
	
	@FindBy(xpath ="//span[@class='zw-cmn-loadMore']")
	WebElement viewMore;

	@FindBy(xpath ="//ul[@id='modelList']//li//div/a")
	List<WebElement> modelName;
	
	@FindBy(xpath ="//ul[@id='modelList']//li//div/a/following-sibling::div[1]")
	List<WebElement> price;
	
	@FindBy(xpath ="//ul[@id='modelList']//li//div/a/following-sibling::div[2]")
	List<WebElement> expectedDate;
		
	//---Action Methods---
	
	//Selecting manufacturer as HONDA
	public void selectManufacturer() {
		 try{	    	
	      Select manu=new Select(manufacturer);
	      manu.selectByVisibleText("Honda");
	      }catch(Exception e) {}
	}
	
	//Clicking on view more
	public void clickViewMore() {	
		scrollToElement(viewMore, viewMore);	       
	}
	
	//List of Model Name
	public  List<WebElement> listOfModels(){
		return modelName;
	}
	
	//List of price
	public  List<WebElement> priceOfModels(){
		return price;
	}
	
	//List of Release date
	public  List<WebElement> expectedDateOfRelease(){
		return expectedDate;
	}
}
