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

public class RadioButtons {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/radiobutton";
	private static WebDriver driver;
	private static WebElement radioBtn1;
	private static WebElement radioBtn2;
	private static WebElement radioBtn3;
	
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
		radioBtn1 = driver.findElement(By.id("radio-button-1"));
		radioBtn1.click();
		
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		radioBtn2 = driver.findElement(By.cssSelector("input[value='option2']"));
		radioBtn2.click();
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		radioBtn3 = driver.findElement(By.xpath("/html/body/div/div[3]/input"));
		radioBtn3.click();
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
