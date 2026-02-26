package VGApplication.VG_UP;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v135.fetch.model.AuthChallengeResponse.Response;
import org.openqa.selenium.devtools.v137.network.Network;
import org.openqa.selenium.devtools.v137.network.Network.GetResponseBodyResponse;
import org.openqa.selenium.devtools.v137.network.model.RequestId;

import io.github.bonigarcia.wdm.WebDriverManager;

public class networkprint {

	public static void main(String []args)
	{
		WebDriverManager.chromedriver().setup();
		 ChromeDriver driver= new ChromeDriver();
		 DevTools devtools  =driver.getDevTools();
		 devtools.createSession();
		 devtools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
		 
		 devtools.addListener(Network.responseReceived(), response ->
		 {
			RequestId requestid= response.getRequestId();
			// System.out.println(Network.getResponseBody(requestid));
			//System.out.println(response.getRequestId());
			System.out.println(response.getResponse().getHeaders());
	          
		 
	});
		  
	driver.get("https://rahulshettyacademy.com/angularAppdemo/");
	driver.findElement(By.xpath("//button[.=' Virtual Library ']")).click();
}
}
