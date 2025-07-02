package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import util.AppUtil;

public class FunctionLibaray extends AppUtil {
public static boolean adminLogin(String user,String pass) throws Throwable
{
	driver.get(conpro.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(conpro.getProperty("ObjUser"))).sendKeys(user);
	driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(pass);
	driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
	Thread.sleep(2000);
	String Expected ="dashboard";
	String Actual = driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		Reporter.log("Login success",true);
		return true;
	}
	else
	{
		String Error_mess = driver.findElement(By.xpath(conpro.getProperty("ObjError"))).getText();
		Reporter.log(Error_mess,true);
		return false;
	}
}
}
