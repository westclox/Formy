package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {
	
	private static WebElement alertBanner;
	
	public static String validateConfirmationPage(WebDriver driver, WebDriverWait wait) {
		
		// wait for our form to be submitted and for the new page and alert banner to appear
		alertBanner = wait.until((ExpectedConditions.visibilityOfElementLocated(By.className("alert"))));
		
		String alertText = alertBanner.getText();
		return alertText;
	}
}
