package VGApplication.VG_UP;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registrationstep_02 {

	WebDriver driver;
	public Registrationstep_02(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(id="motherName") WebElement mothername;
	@FindBy(id="motherQualification") WebElement motherqualification;
	@FindBy(id="motherIncome") WebElement motherincome;
	@FindBy(id="motherOccupation") WebElement motheroccupion;
	@FindBy(id="fatherName") WebElement fathername;
	@FindBy(id="fatherQualification") WebElement fatherqualification;
	@FindBy(id="fatherIncome") WebElement fatherincome;
	@FindBy(id="fatherOccupation") WebElement fatheroccupion;
	@FindBy(id="guardianAadhaarNumber") WebElement guardaadhaar;
	@FindBy(id="otherIncomeSource") WebElement otherincome;
	@FindBy(id="grandFatherName") WebElement grandfathername;
	@FindBy(id="mainNumberCheck") WebElement whatsappNumber;
	@FindBy(id="noneNumberCheck") WebElement nonewhatsappNumber;
	@FindBy(id="addressVillage") WebElement addressvillage;
	@FindBy(id="addressState") WebElement state;
	@FindBy(id="addressDistrict") WebElement district;
	@FindBy(id="addressTehsil") WebElement tehsil;
	@FindBy(id="addressBlock") WebElement block;
	@FindBy(id="addressPost") WebElement post;
	@FindBy(id="addressPincode") WebElement pincode;
	@FindBy(id="schoolName") WebElement schoolname;
	@FindBy(id="schoolUDISE") WebElement UDISE;
	@FindBy(id="schoolType") WebElement schooltype;
	//@FindBy(id="studentAPAAR") WebElement studentAPAAR;
	@FindBy(id="studentAdmissionClass") WebElement admissionclass;
	@FindBy(id="schoolAddress") WebElement schooladdress;
	@FindBy(xpath="//button[.='Confirm']") WebElement confrmbutton;
	@FindBy(id="uploadBtn_1") WebElement passportphoto;
	@FindBy(id="uploadBtn_2") WebElement Aadhaarphoto;
	@FindBy(id="uploadBtn_9") WebElement Bonafidecertificate;
	@FindBy(id="submitButton")WebElement submitbutton;
	@FindBy(xpath="//button[@type='submit']") WebElement formsubmit;
	
	public void step_002(String Mothername,String Motherqualification,String Motherincome,String Motheroccupion,String Fathername,
			String Fatherqualification,String Fatherincome,String Fatheroccupion,String Guardaadhaar,String GFatherincome,String Grandfathername,String WhatsappNumber,
			String Village,String State,String District,String Tehsil,String Block,String postoffice ,String Pincode,String currentschoolname,
			String schlUDISE,String schtype,String Class,String Schladdress,String photo,String aadharphoto,String certificatephoto) throws InterruptedException, AWTException
	{
		mothername.sendKeys(Mothername);
		Select qualification = new Select(motherqualification);
		qualification.selectByVisibleText(Motherqualification);
		
		Select motherincomes = new Select(motherincome);
		motherincomes.selectByVisibleText(Motherincome);
		
		Select moccupion  = new Select(motheroccupion);
		moccupion.selectByVisibleText(Motheroccupion);
		
		fathername.sendKeys(Fathername);
		
		Select Fqualification = new Select(fatherqualification);
		Fqualification.selectByVisibleText(Fatherqualification);
		
		Select fatherincomes = new Select(fatherincome);
		fatherincomes.selectByVisibleText(Fatherincome);
		
		Select foccupion  = new Select(fatheroccupion);
		foccupion.selectByVisibleText(Fatheroccupion);
		
		guardaadhaar.sendKeys(Guardaadhaar);
		
		Select gfatherincomes = new Select(otherincome);
		gfatherincomes.selectByVisibleText(GFatherincome);
		grandfathername.sendKeys(Grandfathername);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 650);");
		Thread.sleep(2000);
		
		
	if(WhatsappNumber.contentEquals("Yes"))
	{
		
	
		whatsappNumber.click();
	}
	else {
		nonewhatsappNumber.click();
	}
		
		addressvillage.sendKeys(Village);
		
		Select AState = new Select(state);
		AState.selectByVisibleText(State);
		
		Select Adistrict = new Select(district);
		Adistrict.selectByVisibleText(District);
		
		Select Atehsil = new Select(tehsil);
		Atehsil.selectByVisibleText(Tehsil);
		
		Select Ablock = new Select(block);
		Ablock.selectByVisibleText(Block);
		
		post.sendKeys(postoffice);
		pincode.sendKeys(Pincode);
		schoolname.sendKeys(currentschoolname);
		UDISE.sendKeys(schlUDISE);
		Select Stype = new Select(schooltype);
		Stype.selectByVisibleText(schtype);
		Select Sclass = new Select(admissionclass);
		Sclass.selectByVisibleText(Class);
		schooladdress.sendKeys(Schladdress);
		js.executeScript("window.scrollBy(0, 700);");
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", passportphoto);
		StringSelection filepath1 = new StringSelection(photo);
		Robot robot = new Robot();
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath1, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_V);
	    Thread.sleep(1000);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyRelease(KeyEvent.VK_V);
	     Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(2000);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    js.executeScript("arguments[0].click();", confrmbutton);
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0, 400);");
        Thread.sleep(2000); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        wait.until(ExpectedConditions.elementToBeClickable(Aadhaarphoto)).click();
	    StringSelection filepath2 = new StringSelection(aadharphoto);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath2, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_V);
	    Thread.sleep(1000);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyRelease(KeyEvent.VK_V);
	     Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(2000);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	 
	    wait.until(ExpectedConditions.elementToBeClickable(confrmbutton)).click();
	   // js.executeScript("arguments[0].click();", confrmbutton);  
        Thread.sleep(4000); 
        wait.until(ExpectedConditions.elementToBeClickable(Bonafidecertificate)).click();
	    StringSelection filepath3 = new StringSelection(certificatephoto);
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filepath3, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
	    Thread.sleep(1000);
	    robot.keyPress(KeyEvent.VK_V);
	    Thread.sleep(1000);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.keyRelease(KeyEvent.VK_V);
	     Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    Thread.sleep(2000);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	    wait.until(ExpectedConditions.elementToBeClickable(confrmbutton)).click();
//	    js.executeScript("arguments[0].click();", confrmbutton);
        Thread.sleep(3000);
	    js.executeScript("arguments[0].click();", submitbutton);
        wait.until(ExpectedConditions.elementToBeClickable(confrmbutton)).click();
        Thread.sleep(2000);
	    js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	    Thread.sleep(3000);
	    wait.until(ExpectedConditions.elementToBeClickable(formsubmit)).click();
	    wait.until(ExpectedConditions.elementToBeClickable(confrmbutton)).click();
	    Thread.sleep(3000);
	    
	   
	    
	    
	    
		
		
	}
	
	

}
