package Udemy;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Assignment8 {

	@Test
	public void section12part2() throws InterruptedException
	{
		 WebDriver driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		 driver.findElement(By.id("autocomplete")).sendKeys("ind");
		 Thread.sleep(2000);
		 driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
		 driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
		 System.out.println(driver.findElement(By.id("autocomplete")).getAttribute("value"));
		 
	}
}
