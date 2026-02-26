package Udemy;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class Assignment4 {

	@Test
	public void section10()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/");
		driver.findElement(By.xpath("//a[.='Multiple Windows']")).click();
		driver.findElement(By.xpath("//a[.='Click Here']")).click();
		Set<String> windows =driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentwindow= it.next();
		driver.switchTo().window(it.next());
		
		System.out.println(driver.findElement(By.xpath("//div[@class='example']/h3")).getText());
		driver.switchTo().window(parentwindow);
		System.out.println(driver.findElement(By.xpath("//div/h3")).getText());
		
		
		
		
	}
	@Test
	public void section7() throws InterruptedException {
	WebDriver driver = new ChromeDriver();
	driver.get("https://rahulshettyacademy.com/angularpractice/");
	
	driver.findElement(By.name("name")).sendKeys("Yogesh chauhan");
	driver.findElement(By.name("email")).sendKeys("Yogesh@test.com");
	driver.findElement(By.id("exampleInputPassword1")).sendKeys("Yogesh@123");
	driver.findElement(By.id("exampleCheck1")).click();
	Select gender = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
	gender.selectByVisibleText("Male");
	driver.findElement(By.id("inlineRadio2")).click();
	
	driver.findElement(By.name("bday")).sendKeys("2026-01-12");
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	
	Thread.sleep(2000);
	System.out.println(driver.findElement(By.cssSelector("div[class*='alert-success ']")).getText());
}}
