package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class DragAndDrop {

	private static final String FORMY_PAGE_URL = "https://formy-project.herokuapp.com/dragdrop";
	private static WebDriver driver;
	private static WebElement image;
	private static WebElement box;
	
	@BeforeClass
	public static void setup() {
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test (priority=1)
	public static void navigateToHerokuPage() {
		driver.get(FORMY_PAGE_URL);
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Formy"));
	}

	@Test (priority=2)
	public static void checkPage() {
		image = driver.findElement(By.id("image"));
		box = driver.findElement(By.id("box"));
		
		
		// Tried with FireFox and Chrome did not work
		Actions actions = new Actions(driver);
		actions.dragAndDrop(image, box).build().perform();
//		Action dragAndDrop = actions.clickAndHold(image)
//				.moveToElement(box)
//				.release(image)
//				.build();
//		dragAndDrop.perform();
		
		// adding a wait state to see that we actually perform the drag and drop
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
