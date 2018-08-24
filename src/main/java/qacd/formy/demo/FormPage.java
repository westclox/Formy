package qacd.formy.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FormPage {

	public static void submitForm(WebDriver driver) {
		// get our web elements, note we are not declaring variables
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
}
