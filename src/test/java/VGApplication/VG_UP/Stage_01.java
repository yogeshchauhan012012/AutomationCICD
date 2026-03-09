package VGApplication.VG_UP;

import java.awt.AWTException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import Datadetails.Studetdetails;




public class Stage_01 extends Getbrowser {

	@Test(dataProvider = "step1",dataProviderClass = Studetdetails.class)
	public void registrationfirst(String Acedemicyear,String Addmisiontestealier,String Schoolruralarea,String Aadharnumber,String Schoolname, String Genders,String DOB,String Mobilenumber,String Familyincome,
	String Mothername,String Motherqualification,String Motherincome,String Motheroccupion,String Fathername,String Fatherqualification,String Fatherincome,String Fatheroccupion,String Guardianaadhaar,String GFatherincome,String Grandfathername,String WhatsappNumber,
			String Village,String State,String District,String Tehsil,String Block,String postoffice ,String Pincode,String currentschoolname,String SchlUDISE,String Schtype,String Classadmitted,String Schladdress,String studphoto,String aadharphoto,String certificatephoto) throws InterruptedException, AWTException
									 
	{
		
		
		
		try {
		getweb();
		
		Registrationstep_01 studregistrationdetails= new Registrationstep_01(driver);
		Registrationstep_02 Studpersonaldetails= new Registrationstep_02(driver);
		//studregistrationdetails.step_001(Acedemicyear,Addmisiontestealier,Schoolruralarea,Aadharnumber,Schoolname,Genders,DOB,Mobilenumber,Familyincome,"111111","111111");
		studregistrationdetails.step_001("Yes","No","Yes", "909090909019", "XYZ SCHOOL", "Boy","28-Apr-2015", "8860370060", "₹ 150001 to ₹ 200000 ", "111111","111111");
		Studpersonaldetails.step_002(Mothername,Motherqualification,Motherincome,Motheroccupion,Fathername,Fatherqualification,Fatherincome,Fatheroccupion,Guardianaadhaar,GFatherincome,Grandfathername,WhatsappNumber,
				Village, State, District, Tehsil, Block, postoffice, Pincode, currentschoolname, SchlUDISE, Schtype, Classadmitted, Schladdress, studphoto, aadharphoto, certificatephoto);
		}
		catch(Throwable t) {
			System.out.println("Test case failed due to "+t.getMessage());
			
		}
		
		}
	//@Test
	public void registrationsecond() throws InterruptedException, AWTException
	{
		
		getweb();
		Registrationstep_01 studregistrationdetails= new Registrationstep_01(driver);
		studregistrationdetails.step_001("Yes","No","Yes", "909080909012", "XYZ SCHOOL", "Boy","28-Apr-2015", "8860370060", "₹ 150001 to ₹ 200000 ", "111111","111111");
		Registrationstep_02 Studdetails= new Registrationstep_02(driver);
		Studdetails.step_002("Dummy mother","Secondary School Leaving Certificate (SSLC)","No Income", "Farmer","Dummy father","Secondary School Leaving Certificate (SSLC)","No Income","Farmer","909090909095","No Income","Dummy grandfather","Yes","XYZ Village","उत्तर प्रदेश (Uttar Pradesh)","आगरा (Agra)","आगरा (Agra)","अकोला (Akola)","202020","110044","School name ","12345678901","शासकीय विद्यालय (Government School)","कक्षा 5","XYZ SCHOOL ADDRESS","C:\\Users\\yogesh_chauhan\\Downloads\\test.jpg",
				"C:\\Users\\yogesh_chauhan\\Downloads\\testdoc.pdf","C:\\Users\\yogesh_chauhan\\Downloads\\dummy.jpg");	
	}
	@AfterMethod
	public void shutdown()
	{
		driver.close();
	}
	
	
}
