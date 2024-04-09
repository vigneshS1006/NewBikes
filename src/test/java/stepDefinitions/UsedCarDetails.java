package stepDefinitions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.ExcelWriter;
import factory.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.UsedCarspage;


public class UsedCarDetails {
	WebDriver driver;
	HomePage hp;
	UsedCarspage ucp;
		
		@When("user hovers to Used cars toggle")
		public void user_hovers_to_used_cars_toggle() {
			
		 hp=new HomePage(BaseClass.getDriver());
		 hp.moveToUsedCars();
		 BaseClass.getLogger().info("Hovering to used cars");
		}
		
		@When("user clicks on Chennai as prefered location")
		public void user_clicks_on_chennai_as_prefered_location() {
			 hp=new HomePage(BaseClass.getDriver());
			 hp.selectCity();
			 BaseClass.getLogger().info("selecting location as CHENNAI");
		}
		
		@Then("All the popular car models list will be diplayed")
		public void all_the_popular_car_models_list_will_be_diplayed() throws IOException {
		  ucp =new UsedCarspage(BaseClass.getDriver());
		  List<WebElement>popularCars=ucp.ListOfPopularcars();
		  List<String> cars=new ArrayList<>();
		  for(WebElement x:popularCars) {
			  System.out.println(x.getText());
			  cars.add(x.getText());
		  }
		  BaseClass.getLogger().info("Popular car models list is taken");
		  ExcelWriter.enterCarDetails(cars,"PopularCars");
		}
}
