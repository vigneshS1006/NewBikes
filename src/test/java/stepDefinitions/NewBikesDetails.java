package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ExcelWriter;
import pageObjects.HomePage;
import pageObjects.UpcomingBikesPage;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NewBikesDetails {
	WebDriver driver;
	HomePage hm;
	UpcomingBikesPage ubp;
	
		
		@Given("user navigates to ZigWheels page")
		public void user_navigates_to_zig_wheels_page() {
			BaseClass.getLogger().info("Navigated to ZigWheels");
			hm=new HomePage(BaseClass.getDriver());		   
		}
		
		@When("user hovers to Newbikes toggle")
		public void user_hovers_to_newbikes_toggle() {
			BaseClass.getLogger().info("Navigated to NewBikes");
			hm=new HomePage(BaseClass.getDriver());
			hm.moveToNewBikes();		
		}
		
		@When("user clicks on Upcoming bikes")
		public void user_clicks_on_upcoming_bikes() {
			BaseClass.getLogger().info("Clicked on Upcoming bikes");
			hm=new HomePage(BaseClass.getDriver());
			hm.clickOnUpcomingBikes();			
		}
		
		@When("chooses manufacturer as Honda")
		public void chooses_manufacturer_as_honda() {
		 BaseClass.getLogger().info("Honda as manufacturer is selected ");
		 ubp= new UpcomingBikesPage(BaseClass.getDriver());
		 ubp.selectManufacturer();		 
		}
		
		@Then("All the upcomings bikes of Honda manufacturer are displayed")
		public void all_the_upcomings_of_honda_manufacturer_are_displayed() throws IOException {
			
			BaseClass.getLogger().info("Upcoming bikes of Honda are displayed");
			
			ubp= new UpcomingBikesPage(BaseClass.getDriver());
			ubp.clickViewMore();
			 
			List<WebElement> modelName= ubp.listOfModels();//List of model name of upcoming HONDA bikes
			List<WebElement> price= ubp.priceOfModels();//List of price of upcoming HONDA bikes
			List<WebElement> expectedDate= ubp.expectedDateOfRelease();//List of Expected Release date of upcoming HONDA bikes

			List<String> mName=new  ArrayList<String>();//List for storing model Name which are under 4L
			List<String> pr=new  ArrayList<String>();//List for storing price under 4L
			List<String> eDate=new ArrayList<>();//List for storing Expected Release date which are under 4L
			
			 for(int i=0;i<modelName.size();i++) {
				 
				   String temp=price.get(i).getText();
				   if(!temp.contains("Lakh"))//Prices under Lakh(Thousands)
				   {
					   mName.add(modelName.get(i).getText());
					   pr.add(price.get(i).getText());
					   eDate.add(expectedDate.get(i).getText());
					   System.out.println(modelName.get(i).getText()+"    "+price.get(i).getText()+"   "+expectedDate.get(i).getText());
				   }
				   else if(temp.contains("Lakh"))	 
				   {
					  double p=Double.parseDouble(temp.substring(temp.indexOf("Rs")+4,temp.indexOf("Lakh")-1));
				      if(p<=4.00)//prices under 4L
				    	{
				    	 mName.add(modelName.get(i).getText());
						 pr.add(price.get(i).getText());
						 eDate.add(expectedDate.get(i).getText());
				    	 System.out.println(modelName.get(i).getText()+"    "+price.get(i).getText()+"   "+expectedDate.get(i).getText());
				    	}
				   	}
			 }
			 BaseClass.getLogger().info("Upcoming bikes of Honda under 4L are readed");
			 ExcelWriter.enterBikeDetails(mName, pr, eDate,"NewBikes");
		}


}
