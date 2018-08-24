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

public class FileUpload {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/fileupload";
	private static WebDriver driver;
	private static WebElement fileUploadField;
	private static WebElement buttonChoose;
	private static WebElement buttonReset;
	
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
		fileUploadField = driver.findElement(By.id("file-upload-field"));
		fileUploadField.sendKeys("C:\\projects\\qacd\\Formy\\file-to-upload.png");
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		buttonChoose = driver.findElement(By.cssSelector(".btn.btn-secondary.btn-choose"));
		buttonChoose.click();
		
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		buttonReset = driver.findElement(By.cssSelector(".btn.btn-warning.btn-reset"));
		buttonReset.click();
		
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
