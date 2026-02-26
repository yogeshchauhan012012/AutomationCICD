package Udemy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Assignment6 {

	@Test
	public void sector11()
	{
	 WebDriver driver = new ChromeDriver();
	 driver.manage().window().maximize();
	 driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	 
	 driver.findElement(By.id("checkBoxOption2")).click();
	 String checkname = driver.findElement(By.id("checkBoxOption2")).getAttribute("value");
	
	 
	 Select dropoption= new Select(driver.findElement(By.id("dropdown-class-example")));
	 dropoption.selectByVisibleText(checkname);
	 
	 driver.findElement(By.id("name")).sendKeys(checkname);
	 driver.findElement(By.id("alertbtn")).click();
	 
	 if(driver.switchTo().alert().getText().contains(checkname))
	 {
		 System.out.println("Success");
	 }
	 
	 else
	 {
		 System.out.println("Somethig wrong in text "+checkname);
		
	 }
	 
	 
	 
	 
	 
	 
	}
}
