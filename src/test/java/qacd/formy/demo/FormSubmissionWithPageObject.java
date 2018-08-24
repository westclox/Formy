package qacd.formy.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class FormSubmissionWithPageObject {
	
	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/form";
	
	private static WebDriver driver;
	private static WebDriverWait wait;

	// no need to declare FormPage and invoke it's default constructor

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
		// we need to access FormPage in a static manner as
		// submitForm is a static method
		FormPage.submitForm(driver);
		String alertText = ConfirmationPage.validateConfirmationPage(driver, wait);
		Assert.assertEquals("The form was successfully submitted!", alertText);
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
}
