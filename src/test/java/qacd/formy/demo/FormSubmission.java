package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class FormSubmission {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/form";
	private static final String FORMY_THANKS = "https://formy-project.herokuapp.com/thanks";
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static WebElement alertBanner;
	
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
	public static void submitForm() {
		
		// get our web elements, note we are declaring variables
		driver.findElement(By.id("first-name")).sendKeys("John");
		driver.findElement(By.id("last-name")).sendKeys("Doe");
		driver.findElement(By.id("job-title")).sendKeys("QQ Engineer");
		driver.findElement(By.id("radio-button-2")).click();
		driver.findElement(By.id("checkbox-2")).click();
		driver.findElement(By.cssSelector("option[value='1']")).click();
		
		driver.findElement(By.id("datepicker")).sendKeys("01/12/1960");
		driver.findElement(By.id("datepicker")).sendKeys(Keys.RETURN);
		
		driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary")).click();
		
		
	}
	
	@Test (priority=3)
	public static void validateNewPage() {
		// wait for our form to be submitted and for the new page and alert banner to appear
		alertBanner = wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("alert"))));
		
		String alertText = alertBanner.getText();
		System.out.println("THE TEST is : " + alertText);
		
		Assert.assertEquals("The form was successfully submitted!", alertText);
		
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains(FORMY_THANKS), "check " + url + " contains " + FORMY_THANKS);
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
	
	
}
