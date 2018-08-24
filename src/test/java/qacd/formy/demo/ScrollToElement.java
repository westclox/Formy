package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class ScrollToElement {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/scroll";
	private static WebDriver driver;
	private static WebElement name;
	private static WebElement date;
	private static Actions action;
	
	@BeforeClass
	public static void setup() {
		FirefoxDriverManager.getInstance().setup();
		driver = new FirefoxDriver();
		action = new Actions(driver);
	}
	
	@Test (priority=1)
	public static void navigateToHerokuPage() {
		driver.get(FORMY_PAGE_URL);
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Formy"));
	}
	
	@Test (priority=2)
	public static void checkPage() {
		name = driver.findElement(By.id("name"));
		// Invoke the method moveToElement from the Actions class
		action.moveToElement(name);
		// now that the element is in view we can send keys to it
		name.sendKeys("Mehgan Philips");
		
		date = driver.findElement(By.id("date"));
		date.sendKeys("01/12/60");
		// adding a wait state to see that we actually get to the element
		try {
			Thread.sleep(5000);
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
