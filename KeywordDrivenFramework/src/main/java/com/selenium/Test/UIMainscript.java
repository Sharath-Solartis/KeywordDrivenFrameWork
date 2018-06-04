package com.selenium.Test;

import java.awt.AWTException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.selenium.Configuration.PropertiesHandle;
import com.selenium.DriverPackage.*;
import com.selenium.SupportingClasses.DatabaseOperation;
import com.selenium.exception.DatabaseException;
import com.selenium.exception.MacroException;
import com.selenium.exception.POIException;
import com.selenium.exception.PropertiesHandleException;

public class UIMainscript
{
	public static DatabaseOperation input;
	public static DatabaseOperation output;

	public static LinkedHashMap<Integer, LinkedHashMap<String, String>> inputtable;
	public static LinkedHashMap<Integer, LinkedHashMap<String, String>> outputtable;
	public static Iterator<Entry<Integer, LinkedHashMap<String, String>>> inputtableiterator;
	public static Iterator<Entry<Integer, LinkedHashMap<String, String>>> outputtableiterator;
	public static ObjectMapper inputtableobjectMapper;
	public static ObjectMapper outputtableobjectMapper;
	public static LinkedHashMap<String, String> inputrow;
	public static LinkedHashMap<String, String> outputrow;
	//public  BaseDriverScript objDriver;
	public static PropertiesHandle configFile;
	public static boolean loginStatus;
	public  static WebDriver driver=null;
	public static String exceptionScreenshotPath=null;
	public static Connection conn = null;
	@BeforeTest//(alwaysRun=true)
	public void loadconfig() throws DatabaseException, ClassNotFoundException, SQLException, PropertiesHandleException, MalformedURLException
	{
		System.setProperty("jsse.enableSNIExtension", "false");
		System.out.println(System.getProperty("Project")+System.getProperty("Flow")+System.getProperty("Env")+System.getProperty("FlagForExecution")+System.getProperty("JDBC_DRIVER")+System.getProperty("DB_URL")+System.getProperty("USER")+System.getProperty("password")+System.getProperty("browser")+ System.getProperty("ResultChoice")+System.getProperty("remoteIP")+ System.getProperty("Port"));
		try
		{
		configFile = new PropertiesHandle(System.getProperty("Project"),System.getProperty("Flow"),System.getProperty("Env"),System.getProperty("FlagForExecution"),System.getProperty("JDBC_DRIVER"),System.getProperty("DB_URL"),System.getProperty("USER"),System.getProperty("password"),System.getProperty("browser"), System.getProperty("ResultChoice"), System.getProperty("remoteIP"), System.getProperty("Port"),System.getProperty("userLogin"));		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		conn=(Connection) DatabaseOperation.ConnectionSetup(configFile);
		//exceptionScreenshotPath=configFile.getProperty("ScreenShotPath");
		
	}
		
	@SuppressWarnings("unchecked")
	@Test(dataProvider="UITestData")
    public void UITest(Integer RowIterator, Object inputtablerowobj) throws ClassNotFoundException, SQLException, IOException, InterruptedException, AWTException, DatabaseException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, MacroException, POIException
    {
		LinkedHashMap<String, String> inputrow = inputtableobjectMapper.convertValue(inputtablerowobj, LinkedHashMap.class);
		
			 if(inputrow.get("Flag_for_execution").equals(configFile.getProperty("flagForExecution")))
				{  
				 try
				 {
					 BaseDriverScript objDriver=new BaseDriverScript(configFile);
					 objDriver.launchBrowser();
				     String ExecutionChoice=configFile.getProperty("ResultsChoice");
				     System.out.println("Executing main script");
				  if(ExecutionChoice.equals("Comparison"))
				  {
					  objDriver.generatExpectedResult(inputrow, outputrow);
				  }
				  
				  objDriver.executeTestScript(inputrow, outputrow);
				
				  if(ExecutionChoice.equals("Comparison"))
				  {
					  objDriver.CompareExpectedWithActual(outputrow);
				  }
				    Statement stmt = (Statement) conn.createStatement();
					stmt.executeUpdate("update "+configFile.getProperty("inputTable")+" set Flag_for_execution='Completed' where S_No="+RowIterator);
					stmt.executeUpdate("update "+configFile.getProperty("outputTable")+" set Result='Completed' where S_No="+RowIterator);
				 }
				 catch(Exception e)
				 {
					e.printStackTrace();
					String message=e.getMessage().toString();
				 }	
			   }
		
    }
	@AfterTest
	public void close() throws DatabaseException
	{
		DatabaseOperation.CloseConn();
	}
//========================================================================data provider=========================================================================================
    @DataProvider(name="UITestData",parallel=true)
	 public Object[][] getDataFromDataprovider() throws DatabaseException, PropertiesHandleException
	 {
		 input = new DatabaseOperation();
		 inputtable = input.GetDataObjects(configFile.getProperty("inputQuery"));
		 Iterator<Entry<Integer, LinkedHashMap<String,String>>> inputtableiterator = inputtable.entrySet().iterator();
		
		 int rowIterator = 0;
		 Object[][] combined = new Object[inputtable.size()][2];
		 while (inputtableiterator.hasNext()) 
			{
				Entry<Integer, LinkedHashMap<String, String>> inputentry = inputtableiterator.next();
		        LinkedHashMap<String, String> inputrow = inputentry.getValue();
		         inputtableobjectMapper = new ObjectMapper();
				 Object inputtablerowobject = inputtableobjectMapper.convertValue(inputrow, Object.class);
				 combined[rowIterator][0] = rowIterator+1;
				 combined[rowIterator][1] = inputtablerowobject;
				 rowIterator++;
			}  
		 return combined;
	 }
}
