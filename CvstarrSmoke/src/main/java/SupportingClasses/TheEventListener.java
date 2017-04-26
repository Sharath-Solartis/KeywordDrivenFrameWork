package SupportingClasses;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import CvstarrSmoke.DriverScript;

public class TheEventListener implements WebDriverEventListener 
{
	static //Static Block that executed before main program runs.
	{


		File theDir = new File("D:/Logs");
		System.out.println("Checking the Presence of Logs Folder in D:\\Logs");
		
		// if the directory does not exist, create it
		if (!theDir.exists()) 
		{
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;
		
		    try
		    {
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se)
		    {
		        //handle it
		    	 System.out.println("Exception Occured while Creating the Folder");
		    }        
		    if(result) 
		    {    
		        System.out.println("DIR created");  
		    }
		}
		System.out.println("Logs Folder is Detected");

	}
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	static Logger logError = Logger.getLogger("ERRORlog");
	static Logger logInfo = Logger.getLogger("INFOlog");
	
	//DriverScript info = new DriverScript();
	
		
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		//System.out.println(by);
		
		//logInfo.info(by);
	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		//logInfo.info(by);
	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		logInfo.info(element);
		System.out.println(element);
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		logInfo.info(element);
		System.out.println(element);
	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		throwable.getStackTrace();
		logError.error(throwable);
	}
	public void testData(String testdata){
		logInfo.info("##################################################################");
		logInfo.info(testdata);
		logInfo.info("##################################################################");
		
	}

}
