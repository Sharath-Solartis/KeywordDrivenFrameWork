package SupportingClasses;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import KeywordDrivenFramework.StarrassistBenefitsCheck;


public class UIoperartions {
	public static WebDriver driver;
	
	 public static WebDriverWait wait;
	 public String inputValue;
	
//**************************************UI operations***************************************************************************
public void perform(String p,String operation,String objectType,String value,String dbcolumn_name,String dataFlag,databaseOperartions input,databaseOperartions output,WebDriver driver,String waitingTime) throws Exception
{
	long waitingTimeinseconds=Long.parseLong(waitingTime);
       
switch (operation.toUpperCase())
{
//-------------------------------click operation-------------------------------------------------------------------------------
case "CLICK":
        try
        {
        	wait = new WebDriverWait(driver, waitingTimeinseconds);
        	wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
    		wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	driver.findElement(this.getObject(p,objectType)).click(); 	
        }
        catch(StaleElementReferenceException e)
        {
          this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
        }
        break;
 //---------------------------------------SET TEXT-----------------------------------------------------------------------       
 case "SETTEXT":
    try{	   
        switch(dataFlag)
         {
        	case "Read":
        		inputValue = input.read_data(dbcolumn_name);
        		break;
        		
        	case "Default":	
        		inputValue = value;
        		break;
          }
        		 wait = new WebDriverWait(driver, 30);
        		 wait = new WebDriverWait(driver, waitingTimeinseconds);
     		     wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
     		     wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
         	     wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        		driver.findElement(this.getObject(p,objectType)).clear();
        		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        		driver.findElement(this.getObject(p,objectType)).sendKeys(inputValue);
        		driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
        		break;  		 
     }       
   catch(StaleElementReferenceException e)
    {
	  this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
    }
    break;
 
 //------------------------------------------------GO TO URL--------------------------------------------------------------------------    
 case "GOTOURL":	 
	 try
	 {
		 inputValue = value;
		 wait = new WebDriverWait(driver, waitingTimeinseconds);
         wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
         wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
         driver.get(inputValue);     
	 }
	 catch(StaleElementReferenceException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	 }
	 break;
 //-------------------------------------------------------GET ATTRIBUTE-------------------------------------------------------------	 
 case "GETATTRIBUTE":
        try
        {
            wait = new WebDriverWait(driver, waitingTimeinseconds);
        	wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
            String text=browserLaunching.driver.findElement(this.getObject(p,objectType)).getAttribute("value");   
            output.write_data(dbcolumn_name,text);
            output.update_row();      
        }
       catch(StaleElementReferenceException e)
        {
    	   this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
        }
   	 break;
 //------------------------------------------------------GET TEXT----------------------------------------------------------------------                 
 case "GETTEXT":
	 try
	 {
		    wait = new WebDriverWait(driver, waitingTimeinseconds);
		    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
            String text1=driver.findElement(this.getObject(p,objectType)).getText();
            output.write_data(dbcolumn_name,text1);
            output.update_row();      
	 }
	 catch(StaleElementReferenceException|TimeoutException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
		 
	 }
	 break;		 
  //----------------------------------------------------SELECT OPERATION------------------------------------------------------------------------- 
 case "SELECT":
	 try
	 {
       switch(dataFlag)
         {  
         case "Read":
     		inputValue = input.read_data(dbcolumn_name);
     		break;
     		
     	case "Default":	
     		inputValue = value;
     		break;
           }      
                wait = new WebDriverWait(driver, waitingTimeinseconds);
        	    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
        	    wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        	    wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        		Select dropdown = new Select(driver.findElement(this.getObject(p,objectType)));
        		dropdown.selectByVisibleText(inputValue);
        		//driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);  		
       }
	 catch(StaleElementReferenceException|TimeoutException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);    
	 }
    break;
//--------------------------------------------------MOUSE HOVER---------------------------------------------------------------------------------- 
 case "MOUSEHOVER": 
	 try
	 {
		    wait = new WebDriverWait(driver, waitingTimeinseconds);
		    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
     	    wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	Actions mouse_hover = new Actions(driver);
			mouse_hover.moveToElement(driver.findElement(this.getObject(p,objectType))).build().perform();
				
	 }
	 catch(StaleElementReferenceException e)
	 {
		 this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
				 
	 }
	 break;
//-----------------------------------------------AUTO COMPLETE--------------------------------------------------------------------------------------	  
case "AUTOCOMPLETE":
	try
	{
     switch(dataFlag)
       {
       case "Read":
    		inputValue = input.read_data(dbcolumn_name);
    		break;
    		
    	case "Default":	
    		inputValue = value;
    		break;
       }
        	wait = new WebDriverWait(driver, waitingTimeinseconds);	
        	wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
            wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
        	driver.findElement(this.getObject(p,objectType)).sendKeys(inputValue);
        	driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
	}
	catch(StaleElementReferenceException e)
	{
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	       
	}
	 break;
	
//-----------------------------------------------------CLICK RADIO BUTTON BY CONCATINATING ITS VALUE ATTRIBUTE---------------------------------------------------------------------------        	
case "RADIOBUTTONVAL":
	
	try
	{
       wait = new WebDriverWait(driver, waitingTimeinseconds);	
       String dbvalue=input.read_data(dbcolumn_name);
       System.out.println(p+dbvalue+"']");
	   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType))); 
       driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
       driver.findElement(this.getObject(p,objectType)).click();
       System.out.println("Click Completed");
      
	}
	catch(StaleElementReferenceException e)
	{
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	}
   break;
//------------------------------------------------------ASSERTION-------------------------------------------------------------------	       	
case "ASSERTTEXT":
	 try
	   {
		 wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	 wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
    	 wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));   
	    } 	   		
	catch(StaleElementReferenceException e)
	   {
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	   }
      break; 	   
//------------------------------------------------SCREENSHOT------------------------------------------------------------------   	
case "SCREENSHOT":
    	   
    	     System.out.println("Taking Screenshot"); 
    	     File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	     String Test_data_name=StarrassistBenefitsCheck.objectInput.read_data("test_data_name");
    	   	 FileUtils.copyFile(scrFile, new File("D:\\sas\\sas1\\"+Test_data_name+".png"));
    	   	 wait = new WebDriverWait(driver, waitingTimeinseconds);	
    	   	 break;
    	   	
 //-----------------------------------------CLICK RADIO BUTTON BY ITS VALUE-------------------------------------------------------------------------------
    	   		
case "RADIOBUTTON":
	
try
{
	switch(dataFlag)
	{
	 case "Read":
 		inputValue = input.read_data(dbcolumn_name);
 		break;
 		
 	case "Default":	
 		inputValue = value;
 		break;
	 }
		   wait = new WebDriverWait(driver, waitingTimeinseconds);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
		   wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
    	   wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
    	   List<WebElement> RadButtonList =driver.findElements(this.getObject(p,objectType));
   			for(int i=0; i< RadButtonList.size() ; i++)
   			{
   				System.out.println(((WebElement) RadButtonList.get(i)).getAttribute("value"));
   				System.out.println(input.read_data(dbcolumn_name));
   			if(((WebElement) RadButtonList.get(i)).getAttribute("value").equals(inputValue))
   			{
   				System.out.println("radio button clicked");
   			   ((WebElement) RadButtonList.get(i)).click();	
   			}
   			}	
	}
catch(WebDriverException e)
{
	this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
}
	break;
	


//------------------------------------------DATE PICKER-----------------------------------------------------------------------------------------
case "DATEPICKER":
	
  try{	   
     switch(dataFlag)
     {
     	 case "Read":
      		inputValue = input.read_data(dbcolumn_name);
      		break;
      		
      	case "Default":	
      		inputValue = value;
      		break;
     } 		
     	wait = new WebDriverWait(driver, 30);
     	wait = new WebDriverWait(driver, waitingTimeinseconds);
  		wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
  	    wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
      	wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
     	driver.findElement(this.getObject(p,objectType)).clear();
     	driver.findElement(this.getObject(p,objectType)).sendKeys(inputValue);
        driver.findElement(this.getObject(p,objectType)).sendKeys(Keys.ENTER);
   }
catch(StaleElementReferenceException e)
 {
	this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
 }
 break;

//-------------------------------------------------------------WAIT FOR LOAD-----------------------------------------------------------------
case "WAITLOAD":
	try
	{
	wait = new WebDriverWait(driver, waitingTimeinseconds); 
	wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	WebElement element = driver.findElement(this.getObject(p,objectType));
	element.isDisplayed();
	break;
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
//-------------------------------------------------------CONTINUES OPERATION------------------------------------------------------------------------------	
case "CONTOPERATION":
	try
	{
	switch(dataFlag)
 	{
 	 case "Read":
  		inputValue = input.read_data(dbcolumn_name);
  		break;
  		
  	case "Default":	
  		inputValue = value;
  		break;
 	}
	 wait = new WebDriverWait(driver, waitingTimeinseconds); 
	 wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
	 WebElement element1= driver.findElement(this.getObject(p,objectType));
     Actions builder = new Actions(driver);
     Actions seriesOfActions = builder.moveToElement(element1).click().sendKeys(element1, inputValue);
     seriesOfActions.perform();
	}
	catch(StaleElementReferenceException e)
	 {
		this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
	 }
	 break;
     
//--------------------------------------------------------WAITING FOR IMG INVISIBILITY----------------------------------------------------------------------------     
case "IMGIDVISIBLE":	
	try
	{
	wait = new WebDriverWait(driver, waitingTimeinseconds);
	wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getObject(p,objectType)));
	break;
	}
	catch(Exception e)
	{
		System.out.println("waited for invisibility");
		break;
	}
//---------------------------------------------------------Set text without enter-------------------------------------------------------------------------------------------	
case "SETTEXT_WITHOUT_ENTER":
    try{	   
        switch(dataFlag)
         {
        	case "Read":
        		inputValue = input.read_data(dbcolumn_name);
        		break;
        		
        	case "Default":	
        		inputValue = value;
        		break;
          }
        		 wait = new WebDriverWait(driver, 30);
        		 wait = new WebDriverWait(driver, waitingTimeinseconds);
     		     wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(p,objectType)));
     		     wait.until(ExpectedConditions.presenceOfElementLocated(this.getObject(p,objectType)));
         	     wait.until(ExpectedConditions.elementToBeClickable(this.getObject(p,objectType)));
        		driver.findElement(this.getObject(p,objectType)).clear();
        		driver.findElement(this.getObject(p,objectType)).sendKeys(inputValue);
        		break;  		 
     }       
   catch(StaleElementReferenceException e)
    {
	  this.perform(p, operation, objectType, value, dbcolumn_name, dataFlag, input, output, driver, waitingTime);
    }
    break;
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
default:
	    System.out.println("operations not  performed");
	    break;
}     
}
 //============================================Locator Action====================================================================  
    private By getObject(String p,String objectType) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH"))
        {  
            return By.xpath(p);
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME"))
        {     
            return By.className(p);      
        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME"))
        {   
            return By.name(p); 
        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS"))
        {   
            return By.cssSelector(p);     
        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK"))
        {   
            return By.linkText(p);     
        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK"))
        {
            return By.partialLinkText(p);
        }
        else if(objectType.equalsIgnoreCase("ID"))
        {
        	return By.id(p);
        }
        else if(objectType.equalsIgnoreCase("VALUE"))
        {
        	return By.id(p);
        }
        else
        {
            throw new Exception("Wrong object type");
        }
    }
}
