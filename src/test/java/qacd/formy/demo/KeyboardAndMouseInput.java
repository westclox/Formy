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

/**
 * 
 * @author Hemraj
 * Simple Test with navigation 
 */

public class KeyboardAndMouseInput {
	
	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/keypress";
	private static WebDriver driver;
	private static WebElement name;
    private static WebElement button;
    
	
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
		name = driver.findElement(By.id("name"));
		button = driver.findElement(By.id("button"));
		name.click();
		name.sendKeys("Meaghan Lewis");
		button.click();
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains(FORMY_PAGE_URL), "check " + url + " contains " + FORMY_PAGE_URL);
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
	
}
