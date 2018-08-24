package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class SwitchToWindow {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/switch-window";
	private static WebDriver driver;
	private static WebElement newBrowserTabWindowBtn;
	
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
	
	@Test (priority=2)
	public static void checkPage() {
		newBrowserTabWindowBtn = driver.findElement(By.id("new-tab-button"));
		newBrowserTabWindowBtn.click();
		
		// Before we can switch to the new window
		// the driver is still focusing on the original window handle
		// get a handle to the original window
		String originalWindowHandle = driver.getWindowHandle();
		// Use a for loop to iterate over the window
		for (String newWindow: driver.getWindowHandles()) {
			driver.switchTo().window(newWindow);
		}
		
		// adding a wait state to see that we actually switch to the new
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// switch back to the original window
		driver.switchTo().window(originalWindowHandle);
		
		// adding a wait state to see that we actually switch to the new
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
