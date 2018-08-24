package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class AutoCompleteWithGooglePlacesAPI {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/autocomplete";
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static WebElement autocomplete;
    private static WebElement autocompleteResult;
    
    @BeforeClass
	public static void setup() {
		FirefoxDriverManager.getInstance().setup();
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test (priority=1)
	public static void navigateToHerokuPage() {
		driver.get(FORMY_PAGE_URL);
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Formy"));
	}
	
	@Test (priority=2)
	public static void checkPage() {
		autocomplete = driver.findElement(By.id("autocomplete"));
		// set the character sequence so we get one result
		autocomplete.sendKeys("1555 Park Blvd, Palo Alto, CA");
		
		/*
		 * wait for the element to be clickable else or test will fail
		 */
		autocompleteResult = wait.until(ExpectedConditions.elementToBeClickable(By.className("pac-item")));
		// use mouse input to select the class that identifies the result
		autocompleteResult.click();
		
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains(FORMY_PAGE_URL), "check " + url + " contains " + FORMY_PAGE_URL);
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
}
