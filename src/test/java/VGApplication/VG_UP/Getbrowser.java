package VGApplication.VG_UP;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Getbrowser {

  public  WebDriver driver;
    public WebDriver getweb() {
        
		String browsername="Chrome";
			if(browsername.contains("Chrome"))
			{
					WebDriverManager.chromedriver().setup();
					driver= new ChromeDriver();
				}
		else if(browsername.contains("Firefox"))
			{ 
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
			}
			else if (browsername.contains("Edge")) {
				WebDriverManager.edgedriver().setup();
				driver= new EdgeDriver();
			}
			else{
           System.out.println("Selected Broswer is not configure/install in your System. Please change the configure/install the broswer in your System"+"\n"+"Brosername:"+browsername);
}

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				driver.manage().window().maximize();
				driver.get("https://dev-vidyagyanadmission.shivnadarfoundation.org/up/2026-27/003bbbc3efb873fb8d62f9651342382f/registration");
				return driver ;
    }
    
	public String screenshot(String testcasename,WebDriver driver) throws IOException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);// +dateFormat.format(date)
		File file = new File(System.getProperty("user.dir") + "\\screenshot\\" + testcasename + "-"+ dateFormat.format(date) + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\screenshot\\" + testcasename + "-" + dateFormat.format(date)+ ".png";
	}
	
	public void scrollpage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
	
	public static ExtentReports getreports(String path)
	{
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("Web Automation");
		report.config().setDocumentTitle("Mobilebackend/cms/testreports");
		ExtentReports extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Tester","Yogesh chauhan");
        return extent;
	}

	

    
    
}


