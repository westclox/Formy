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

public class DropDownSelect {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/dropdown";
	private static final String AUTOCOMPLETE = "https://formy-project.herokuapp.com/autocomplete";
	private static WebDriver driver;
	private static WebElement dropDownMenu;
	private static WebElement autoCompleteItem;
	
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
		dropDownMenu = driver.findElement(By.id("dropdownMenuButton"));
		dropDownMenu.click();
		
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		autoCompleteItem = driver.findElement(By.id("autocomplete"));
		autoCompleteItem.click();
		// adding a wait state to see that we actually see the click
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.contains(AUTOCOMPLETE), "check " + url + " contains " + AUTOCOMPLETE);
	}
	
	@AfterClass
	public static void cleanUp() {
		driver.quit();
	}
}
