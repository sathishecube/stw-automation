import java.util.Map;
import java.util.TreeMap;

import org.testng.annotations.Test;

public class MainTest {

	@Test
	public void testing() throws Exception
	{
		Support s = new Support();
		String [][] input = s.readData("D:\\Test Data\\Test Data.xls",0);
		int flag=0;
		
		//Creating map to store elements
		Map<String, Object[]> data   = new TreeMap<String, Object[]>();
		Map<String, Object[]> data1  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data2  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data3  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data4  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data5  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data6  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data7  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data8  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data9  = new TreeMap<String, Object[]>();
		Map<String, Object[]> data10 = new TreeMap<String, Object[]>();
		Map<String, Object[]> data11 = new TreeMap<String, Object[]>();
		Map<String, Object[]> data12 = new TreeMap<String, Object[]>();
		Map<String, Object[]> data13 = new TreeMap<String, Object[]>();
		Map<String, Object[]> data14 = new TreeMap<String, Object[]>();
		
		//output rows
		int rc=1;
		data.put(""+rc, new Object[] {"TC No", "Module", "Description", "Expected Result", "Actual Result", "Status", "Execution Time(Sec.)"});
		
		System.out.println(input.length);            
		for(int i=0;i<input.length;i++)
		{
			if(input[i][0].equalsIgnoreCase("Login"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					login login = new login();
					data1=login.exec(data1, rc);
					rc=rc+data1.size();
					System.out.println(rc);
					flag=1;
				}
			}else if(input[i][0].equalsIgnoreCase("Fleet Status"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					FleetStatus sts =new FleetStatus();
					data2=sts.fleetstatus(data2, rc);
					rc=rc+data2.size();
					System.out.println(rc);
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Fleet Detail"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					FleetDetail test = new  FleetDetail();
					data3=test.fleetDetail(data3, rc);
					rc=rc+data3.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Geofence"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					Geofence GF = new Geofence();
					data4 =GF.Geofence(data4 , rc);
					rc=rc+data4.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Timefence"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					System.out.println("Inside the time fence loop");
					Timefence TF = new Timefence();
					data5 =TF.Timefence(data5 , rc);
					rc=rc+data5.size();
					flag=1;
				}
			}
			
			else if(input[i][0].equalsIgnoreCase("Monthly -Utilization Report"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					MonthlyUtil MU = new MonthlyUtil();
					data6 =MU.Monthly(data6 , rc);
					rc=rc+data6.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Weekly -Utilization Report"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					WeeklyUtil WU = new WeeklyUtil();
					data7 =WU.Weekly(data7 , rc);
					rc=rc+data7.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Daily -Utilization Report"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					DailyUtil DU = new DailyUtil();
					data8 =DU.Daily(data8 , rc);
					rc=rc+data8.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("ADT Daily- Utilization Report"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					ADTDaily AD = new ADTDaily();
					data9 =AD.ADT(data9 , rc);
					rc=rc+data9.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Filter/Oil Management"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					Filteroil FO = new Filteroil();
					data10 =FO.Filter(data10 , rc);
					rc=rc+data10.size();
					flag=1;
				}
			}
			
			else if(input[i][0].equalsIgnoreCase("Event Report"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					EventRep ER = new EventRep();
					data11 =ER.EventReport(data11 , rc);
					rc=rc+data11.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("User settings"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					UserSetting US = new UserSetting();
					data12 =US.Userset(data12 , rc);
					rc=rc+data12.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Admin"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					System.out.println("Inside of admin module");
					Admin AM = new Admin();
					data13 =AM.Admn(data13 , rc);
					rc=rc+data13.size();
					flag=1;
				}
			}
			else if(input[i][0].equalsIgnoreCase("Logout"))
			{
				if(input[i][3].equalsIgnoreCase("Y"))
				{
					Logout LT = new Logout();
					data14 =LT.log(data14 , rc);
					rc=rc+data14.size();
					flag=1;
				}
			}
		}
		if(flag == 1)
		{
			rc++;
			data.putAll(data1);
			data.putAll(data2);
			data.putAll(data3);
			data.putAll(data4);
			data.putAll(data5);
			data.putAll(data6);
			data.putAll(data7);
			data.putAll(data8);
			data.putAll(data9);
			data.putAll(data10);
			data.putAll(data11);
			data.putAll(data12);
			data.putAll(data13);
			data.putAll(data14);
			s.writeoutput(data, "Output_Test_Doosan");
		}
		else
		{
			System.out.println("No modules to run");
		}
		
	}
}
