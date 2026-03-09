package Udemy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment3 {

	WebDriver driver;
	@Test
	public void section9() {
		
		String browsername=System.getProperty("broswer")!=null ? System.getProperty("broswer"):"Chrome";
		
	//	String browsername="Chrome";
		if(browsername.equalsIgnoreCase("Chrome"))
		{
				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver();
			}
	else if(browsername.equalsIgnoreCase("Firefox"))
		{ 
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else if (browsername.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		else{
       System.out.println("Selected Broswer is not configure/install in your System. Please change the configure/install the broswer in your System"+"\n"+"Brosername:"+browsername);
}
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        String[] text=driver.findElement(By.xpath("//p[@class='text-center text-white']")).getText().split("and");
        String[] userids =text[0].split("is");
        String[] passwords=text[1].split("is");
		driver.findElement(By.id("username")).sendKeys(userids[1].trim());
		driver.findElement(By.id("password")).sendKeys(passwords[1].replace(")","").trim());
		driver.findElement(By.xpath("(//input[@id='usertype'])[2]")).click();
		WebDriverWait  wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[.='Okay']")));
		driver.findElement(By.xpath("//button[.='Okay']")).click();
		Select dropdown= new Select(driver.findElement(By.xpath("//select[@class='form-control']")));
		dropdown.selectByValue("consult");	
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".nav-item.active")));
		List<WebElement> products = driver.findElements(By.xpath("//button[.='Add ']"));
		for(int i=0;i<products.size();i++)
		{
			products.get(i).click();
		}
		
		driver.findElement(By.cssSelector(".nav-item.active")).click();
		
		
	}
}
