package VGApplication.VG_UP;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Registrationstep_01 {
	WebDriver driver ;
	
	public Registrationstep_01(WebDriver driver)
	{
		this.driver= driver;
		 PageFactory.initElements(driver,this);
    
	}
	@FindBy(xpath="//i[@class='fa fa-bars']")WebElement menu;
	@FindBy(xpath="//li[contains(.,'Candidate Registration')]")WebElement regis;
	@FindBy(id="regPassed")WebElement acadyear;
	@FindBy(id="regAppeared")WebElement admissiontest;
	@FindBy(id="regSchoolLocation")WebElement schoolarea;
	@FindBy(id="regAadhaar")WebElement studaadhaar;
	@FindBy(id="regName")WebElement Studname;
	@FindBy(id="regGender")WebElement gender;
	@FindBy(id="regDob")WebElement DOB;
	@FindBy(css=".ui-datepicker-month")	WebElement months;
	@FindBy(css=".ui-datepicker-year")WebElement years;	
	@FindBy(id="regPhone")WebElement mobile;
	@FindBy(id="regIncome")WebElement annualincome;
	@FindBy(id="captchaInput")WebElement captchacode;
	@FindBy(xpath="//button[@type='submit']")WebElement submitbutton;
	@FindBy(xpath="//button[.='Confirm']")WebElement Confirmbuttion;
	@FindBy(id="mobileOtpModal")WebElement mobileotp;
	@FindBy(id="otpModalVerifyButton")WebElement OTPsubmit;
	
	
	public void step_001(String academicyear,String addmisiontestealier,String schoolruralarea,String aadharnumber,
			String studname,String genders,String Date,String mobilenum,String familyincome,String captcha,String otp) throws InterruptedException 
	{
		
		menu.click(); 
		regis.click();
		Select acadyears = new Select(acadyear);
		acadyears.selectByVisibleText(academicyear);
		Select admission = new Select(admissiontest);
		admission.selectByVisibleText(addmisiontestealier);	
		Select ruralarea = new Select(schoolarea);
		ruralarea.selectByVisibleText(schoolruralarea);
		studaadhaar.sendKeys(aadharnumber);
		Studname.sendKeys(studname);
		Select genderoption = new Select(gender);
		genderoption.selectByVisibleText(genders);
		Thread.sleep(1000);
		DOB.click();
		String []parts= Date.split("-");
		String day=parts[0];
		String month=parts[1];
		String year=parts[2];
	
		Select Dateofmonth = new Select(years);
		Dateofmonth.selectByVisibleText(year);
		
		Select Dateofyear = new Select(months);
		Dateofyear.selectByVisibleText(month);
		
		driver.findElement(By.xpath("//table//td[.='"+day+"']")).click();
		mobile.sendKeys(mobilenum);
		Select annualoption= new Select(annualincome);
		annualoption.selectByVisibleText(familyincome);
		captchacode.sendKeys(captcha);
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submitbutton);
//		js.executeScript("window.scrollBy(0, 800);"); 
//		Thread.sleep(2000);
//		submitbutton.click();
		
		Confirmbuttion.click();
		Thread.sleep(2000);
		mobileotp.sendKeys(otp);
		
	//	Thread.sleep(1000);
		OTPsubmit.click();
		
		Thread.sleep(2000);
	
		
	}
	
	
	
	
	

}
