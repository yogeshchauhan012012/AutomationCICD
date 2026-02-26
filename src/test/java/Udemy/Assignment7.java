package Udemy;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Assignment7 {

	@Test
	public void section12()
	{
		 WebDriver driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		 
		 
		System.out.println("Total number of rows:-"+driver.findElements(By.xpath("//table[@name='courses']//tr")).size());
		System.out.println("Total number of cloumns:-"+driver.findElements(By.xpath("//table[@name='courses']//th")).size());
	List<WebElement> values=driver.findElements(By.xpath("//table[@name='courses']//tr[3]"));
	
	for(int i=0; i<values.size();i++)
	{
		System.out.println("Value in second row is:-"+values.get(i).getText()); 
	}
	
		
		
		
	
	}
}
