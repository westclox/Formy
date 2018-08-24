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

public class CheckBoxes {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/checkbox";
	private static WebDriver driver;
	private static WebElement checkBox1;
	private static WebElement checkBox2;
	private static WebElement checkBox3;
	
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
		checkBox1 = driver.findElement(By.id("checkbox-1"));
		checkBox1.click();
		
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		checkBox2 = driver.findElement(By.id("checkbox-2"));
		checkBox2.click();
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		checkBox3 = driver.findElement(By.id("checkbox-3"));
		checkBox3.click();
		// adding a wait state to see that we actually see the click
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
