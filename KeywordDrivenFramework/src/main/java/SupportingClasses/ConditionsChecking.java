package SupportingClasses;
import java.sql.SQLException;



public class ConditionsChecking {
	
	public boolean condition_reading(String condition,databaseOperartions input,databaseOperartions output) throws SQLException
	{
		boolean condition_reading=false;
		
		
		if(condition.equals(""))
		{
			condition_reading=true;
		}
		else
		{
		
		//String[] splits=condition.toString().split(";");
		String[] splits=condition.split(";");
		//System.out.println("comma split1 "+splits[0]);
		//System.out.println("Comma split2 "+splits[1]);
		
				
		int length=splits.length;
		//System.out.println("length of conditions"+length);
		
		
		for(int i=0;i<length;i++)
		{
			String[] cond_value=splits[i].split("=");
			String cond=cond_value[0];
			//System.out.println("condition="+cond);
			String value=cond_value[1];
			//System.out.println("value="+value);
			String[] individualValue = value.split("\\|");
			//System.out.println(value);
			//System.out.println("individual length="+individualValue.length+"cond1="+individualValue[0]);
			for(int j=0;j<individualValue.length;j++)
			{
				//System.out.println(input.read_data(cond));
				//System.out.println(individualValue[j]);				
				if(input.read_data(cond).equals(individualValue[j]))
				{
					//System.out.println("condition satisfied");
					 condition_reading=true;
					 return condition_reading;
				}
			}
				
		}	
		
	}
		
	return condition_reading;
		
	}


}
