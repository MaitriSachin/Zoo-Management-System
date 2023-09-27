package com.ZMS.GenericUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
public class BaseClass {
	
	public ExcelUtility eu=new ExcelUtility();
	public FileUtility fu=new FileUtility();
	public JavaUtility ju=new JavaUtility();
	public WebDriverUtility wu=new WebDriverUtility();
	public DBUtility db=new DBUtility();
	
	public WebDriver driver;
	public static WebDriver ssdriver;
	
	@BeforeSuite(alwaysRun = true)
	public void configBS() throws Throwable
	{
		db.connectToDB();
		System.out.println("DB Connected");
	}
	
	@BeforeClass(alwaysRun = true)
	public void configBC() throws Throwable
	{
		String Browser = fu.getPropertyKeyValue("browser");
		
		if(Browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		
		else if(Browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		
		else
		{
			System.out.println("Invalid Browser");
		}
		
		System.out.println("Browser launched");
		
		ssdriver=driver;
		wu.maximizeTheBrowser(driver);
		
		}
	
	@BeforeMethod(alwaysRun = true)
	public void configBM() throws Throwable
	{
		String URL = fu.getPropertyKeyValue("url");
		String UN=fu.getPropertyKeyValue("username");
		String PWD= fu.getPropertyKeyValue("password");
		
		driver.get(URL);
		wu.implicitWait(driver, 10);
	}
}
