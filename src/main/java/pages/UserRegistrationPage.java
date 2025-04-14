package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class UserRegistrationPage
{
	WebDriver driver;
	
	public UserRegistrationPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	

	public WebElement registerButton() 
	{
		return driver.findElement(By.id("register-button"));
	}

	public WebElement firstNameValidation() 
	{
		return driver.findElement(By.xpath("//form/div/div[2]/div[2]/div[2]/div[2]/span[2]/span"));
	}
	
	public WebElement lastNameValidation() 
	{
		return driver.findElement(By.xpath("//form/div/div[2]/div[2]/div[2]/div[3]/span[2]/span"));
	}
	
	public WebElement emailValidation() 
	{
		return driver.findElement(By.xpath("//form/div/div[2]/div[2]/div[2]/div[4]/span[2]/span"));
	}
	
	public WebElement passwordValidation() 
	{
		return driver.findElement(By.xpath("//form/div/div[2]/div[3]/div[2]/div[1]/span[2]/span"));
	}
	
	public WebElement confirmPasswordValidation() 
	{
		return driver.findElement(By.xpath("//form/div/div[2]/div[3]/div[2]/div[2]/span[2]/span"));
	}
	
	public WebElement genderMale() 
	{
		return driver.findElement(By.id("gender-male"));
	}
	
	public WebElement genderFemale() 
	{
		return driver.findElement(By.id("gender-female"));
	}
	
	public WebElement firstName() 
	{
		return driver.findElement(By.id("FirstName"));
	}
	
	public WebElement lastName() 
	{
		return driver.findElement(By.id("LastName"));
	}
	
	public WebElement emailAddress() 
	{
		return driver.findElement(By.id("Email"));
	}
	
	public WebElement password() 
	{
		return driver.findElement(By.id("Password"));
	}
	
	public WebElement confirmPassword() 
	{
		return driver.findElement(By.id("ConfirmPassword"));
	}
	
	public WebElement completeRegistrationMessage() 
	{
		return driver.findElement(By.xpath("//div[4]/div[2]/div/div[2]/div[1]"));
	}
}
