package extentReports;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase2 {
	
	WebDriver driver;

	@Test
	public void startApp() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String expTitle = "66OrangeHRM";
		String actTitle = driver.getTitle();
		System.out.println("The current page title is : " + actTitle );
		//Assert.assertEquals(actTitle, expTitle);
		Assert.assertEquals(actTitle, expTitle, "Both the titles are not equal");
	}

	@Test(dependsOnMethods = "startApp", alwaysRun = true)
	public void loginApp() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
	}

	@Test(dependsOnMethods = "loginApp")
	public void closeApp() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='Welcome Admin']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Thread.sleep(3000);
		driver.close();
	}

}