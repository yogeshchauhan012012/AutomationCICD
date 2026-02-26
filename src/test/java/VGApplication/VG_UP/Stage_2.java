package VGApplication.VG_UP;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Datadetails.studetdetails;
import Datadetails.studetdetails2;
import dataprovider.getdataprovider;



public class Stage_2 extends getbrowser {
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String Browser) {
		getweb(Browser);
	}
	@Test(dataProvider = "step2",dataProviderClass = studetdetails2.class)
	public void registrationfirst(String Acedemicyear,String Addmisiontestealier,String Schoolruralarea,String Aadharnumber,String Schoolname, String Genders,String DOB,String Mobilenumber,String Familyincome,
	String Mothername,String Motherqualification,String Motherincome,String Motheroccupion,String Fathername,String Fatherqualification,String Fatherincome,String Fatheroccupion,String Guardianaadhaar,String GFatherincome,String Grandfathername,String WhatsappNumber,
			String Village,String State,String District,String Tehsil,String Block,String postoffice ,String Pincode,String currentschoolname,String SchlUDISE,String Schtype,String Classadmitted,String Schladdress,String studphoto,String aadharphoto,String certificatephoto) throws InterruptedException, AWTException
									 
	{
		try {
		//getweb(Browser);
		Registrationstep_01 studregistrationdetails= new Registrationstep_01(driver);
		Registrationstep_02 Studpersonaldetails= new Registrationstep_02(driver);
		studregistrationdetails.step_001(Acedemicyear,Addmisiontestealier,Schoolruralarea,Aadharnumber,Schoolname,Genders,DOB,Mobilenumber,Familyincome,"111111","111111");
		//studregistrationdetails.step_001("Yes","No","Yes", "909090909019", "XYZ SCHOOL", "Boy","28-Apr-2015", "8860370060", "₹ 150001 to ₹ 200000 ", "111111","111111");
		Studpersonaldetails.step_002(Mothername,Motherqualification,Motherincome,Motheroccupion,Fathername,Fatherqualification,Fatherincome,Fatheroccupion,Guardianaadhaar,GFatherincome,Grandfathername,WhatsappNumber,
				Village, State, District, Tehsil, Block, postoffice, Pincode, currentschoolname, SchlUDISE, Schtype, Classadmitted, Schladdress, studphoto, aadharphoto, certificatephoto);
		}
		catch(Throwable t) {
			System.out.println("Test case failed due to "+t.getMessage());
			
		}
		
		}
	@AfterMethod
	public void shutdown()
	{
		driver.close();
	}
	
	
}
