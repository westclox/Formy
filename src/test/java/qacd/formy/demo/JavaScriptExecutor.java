package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

/**
 * 
 * @author hemraj
 * We use javaScriptExecutor under the following conditions
 * When webdriver fails to click on an element due to page issues
 * When actions on page needs to be triggered
 * When movement on page is required
 * 
 * There are two methods supported by javaScriptExecutor
 * executeAysncScript - must provide a callback
 * executeScript - must provide an anonymous function
 *
 * Generally button that hard to click exists within Modal Dialogs
 */
public class JavaScriptExecutor {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/modal";
	private static WebDriver driver;
	private static WebElement modalButton;
	private static WebElement modalDialogCloseBtn;
	
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
		modalButton = driver.findElement(By.id("modal-button"));
		modalButton.click();
		// adding a wait state to see that we actually have a modal dialog displayed
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// modal dialog close button
		modalDialogCloseBtn = driver.findElement(By.id("close-button"));
		// now create a javascript executor object
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", modalDialogCloseBtn);
		
		// adding a wait state to see that we actually have a modal dialog is closed
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
