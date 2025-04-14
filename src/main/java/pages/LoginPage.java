package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public WebElement getEmailField() {
		return driver.findElement(By.id("Email"));
	}

	public WebElement getPasswordField() {
		return driver.findElement(By.id("Password"));
	}

	public WebElement getLoginButton() {
		return driver.findElement(By.cssSelector("input.button-1.login-button"));
	}
}