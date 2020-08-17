package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelReader;
import utilities.TestUtility;

public class TestBase {
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static Logger log = Logger.getLogger(TestBase.class);
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excelData\\TestData.xlsx");
	public static ChromeOptions options;
	public static String filePath;
	@BeforeSuite
	public void setUp() {
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");
		log.info("Log4j.properties file is loaded..");
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Configuration.properties");
			config.load(fis);
			log.info("Configuration file is loaded..");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if(config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\src\\test\\resources\\driverExe\\chromedriver.exe");
			options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications ");
			driver = new ChromeDriver(options);
			log.info("Chrome browser is instatiated..");
		}else if(config.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+ "\\src\\test\\resources\\driverExe\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox browser is instatiated..");
		}else if(config.getProperty("browser").equals("ie")) {
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+ "\\src\\test\\resources\\driverExe\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("IE browser is instatiated..");
		}
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get(config.getProperty("testSiteURL"));
		log.info("Application is navigated : "+config.getProperty("testSiteURL"));
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			fis = new FileInputStream("D:\\Workspace_UK\\DataDrivenFramework\\src\\test\\resources\\properties\\ObjectRepo.properties");
			or.load(fis);
			log.info("Object Repository file is loaded..");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@AfterSuite
	public void tearDown()  {
		TestUtility.waitTo(3);
		driver.quit();
	}

}
