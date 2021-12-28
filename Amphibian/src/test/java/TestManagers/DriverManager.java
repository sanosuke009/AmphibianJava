package TestManagers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.JsonUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager extends ReportManager{
	
	public synchronized void initBrowser()
	{
		try {
		if(getConfig("browser").equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(getConfig("browser").equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(getConfig("browser").equals("android"))
		{
			dc = new DesiredCapabilities(JsonUtils.readJson(getConfig("appiumCapabilitiesDefault")));
			driver = new AndroidDriver<WebElement>(new URL(getConfig("appiumServerURL")), dc);
		}
		else if(getConfig("browser").equals("iOS"))
		{
			dc = new DesiredCapabilities(JsonUtils.readJson(getConfig("appiumCapabilitiesDefault")));
			driver = new IOSDriver<WebElement>(new URL(getConfig("appiumServerURL")), dc);
		}
		else
		{
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		wait = new WebDriverWait(driver, 8);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized boolean getURL(String url)
	{
		try {
			driver.get(url);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public synchronized boolean close()
	{
		try {
			driver.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public synchronized boolean quit()
	{
		try {
			driver.quit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
