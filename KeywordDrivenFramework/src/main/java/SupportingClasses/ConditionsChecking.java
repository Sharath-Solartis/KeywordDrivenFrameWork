package SupportingClasses;
import java.sql.SQLException;



public class ConditionsChecking {
	
	public boolean condition_reading(String condition,databaseOperartions input,databaseOperartions output) throws SQLException
	{
		boolean condition_reading=false;
			
		if(condition.equals(""))
		{
			System.out.println("Null condition");
			condition_reading=true;
		}
		else
		{
		String[] splits=condition.split(";");			
		int length=splits.length;		
		   for(int i=0;i<length;i++)
		     {
			   System.out.println(splits[i]);
			   System.out.println("Checking equal values");	
				   String[] cond_value=splits[i].split("=");
				   String cond=cond_value[0];
				   String value=cond_value[1];
				   //System.out.println("Condition-->"+cond);
				   //System.out.println("values--->"+value);
				   String[] individualValue = value.split("\\|");
				   for(int j=0;j<individualValue.length;j++)
				   {
					   System.out.println(input.read_data(cond));
					   System.out.println(individualValue[j]);
					   if(input.read_data(cond).equals(individualValue[j]))
					   {
						   System.out.println("codition true");
						   condition_reading=true;
						   //return condition_reading;
					   }
				   }	
			   }
		
		     }		
	return condition_reading;
	}
	}

