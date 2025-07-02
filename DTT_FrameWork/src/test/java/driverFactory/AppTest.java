package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibaray;
import util.AppUtil;
import util.ExcelFileUtil;

public class AppTest extends AppUtil{
	String FileInput="./DataTables/TestData.xlsx";
	String FileOutput ="./DataTables/DataDrivenResults.xlsx";
	@Test
	public void startTest() throws Throwable
	{
		//create Object for ExcelFileUtil class
		ExcelFileUtil xl = new ExcelFileUtil(FileInput);
		//count no of rows in Logindata sheet
		int rc = xl.rowCount("LoginData");
		Reporter.log("No of rows  "+rc,true);
		for(int i=1;i<=rc;i++)
		{
		//read username and password cells
		String username = xl.getCellData("LoginData", i, 0);
		String password = xl.getCellData("LoginData", i, 1);
		//call adminlogin method from Functionlibaray class
		boolean res =FunctionLibaray.adminLogin(username, password);
		if(res)
		{
			//if true write as pass into status cell in LoginData sheet
			xl.setCelldata("LoginData", i, 2, "Pass", FileOutput);
		}
		else
		{
			//take screen shot
			File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./target/Screenshot/"+i+"Loginpage.png"));
			//if false write as Fail into status cell in LoginData sheet
			xl.setCelldata("LoginData", i, 2, "Fail", FileOutput);
		}
		
	}

}
}
