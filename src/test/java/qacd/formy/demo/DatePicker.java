package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class DatePicker {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/datepicker";
	private static WebDriver driver;
	private static WebElement dateField;

	@BeforeClass
	public static void setup() {
		FirefoxDriverManager.getInstance().setup();
		driver = new FirefoxDriver();
	}
	
	@Test (priority=1)
	public static void navigateToHerokuPage() {
		driver.get(FORMY_PAGE_URL);
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Formy"));
	}
	
	// With date picker there are several ways to set a date picker
	@Test (priority=2)
	public static void checkPage() {
		dateField = driver.findElement(By.id("datepicker"));
		dateField.sendKeys("01/12/1960");
		// adding a wait state to see that we actually see the datepicker date selected
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// in order to actually set the date we need hit return
		dateField.sendKeys(Keys.RETURN);
		
		// adding a wait state to see that we actually see the datepicker updated
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains(FORMY_PAGE_URL), "check " + url + " contains " + FORMY_PAGE_URL);
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
}
