package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.UserRegistrationPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.NoSuchElementException;


public class UserRegistrationSteps
{
	WebDriver driver;
    UserRegistrationPage userRegister;
    WebDriverWait wait;
    
    @After
    public void tearDown(Scenario scenario) 
    {
        if (scenario.isFailed()) 
        {
            try 
            {
                // Take screenshot and save it locally
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "target/screenshots/" + scenario.getName() + ".png";

                // Ensure the target/screenshots folder exists
                File screenshotFolder = new File("target/screenshots");
                if (!screenshotFolder.exists()) 
                {
                    screenshotFolder.mkdirs();  // Create folder if it doesn't exist
                }

                // Copy the screenshot to the target directory
                FileUtils.copyFile(screenshot, new File(screenshotPath));

                // Log the file path for debugging
                System.out.println("Screenshot saved to: " + screenshotPath);

                // Attach it to the Cucumber report as well
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", "Screenshot on Failure");
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        driver.quit();
    }
    
    @Given("user opens registration page")
    public void user_opens_registration_page()
    {
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	driver.get("https://demowebshop.tricentis.com/register");
    	userRegister = new UserRegistrationPage(driver);
    }
    
    @When("user clicks on register button")
    public void user_clicks_on_register_button()
    {
    	wait.until(ExpectedConditions.visibilityOf(userRegister.registerButton())).click();
    }
    
    @And("ensures necessary validations are displayed")
    public void ensures_necessary_validations_are_displayed()
    {
    	String firstNameValidationMessage = "First name is required.";
    	String lastNameValidationMessage = "Last name is required.";
    	String emailValidationMessage = "Email is required.";
    	String passwordValidationMessage = "Password is required.";
    	
    	String uiFirstNameValidationMessage = userRegister.firstNameValidation().getText();
    	String uiLastNameValidationMessage = userRegister.lastNameValidation().getText();
    	String uiEmailValidationMessage = userRegister.emailValidation().getText();
    	String uiPasswordValidationMessage = userRegister.passwordValidation().getText();
    	String uiConfirmPasswordValidationMessage = userRegister.confirmPasswordValidation().getText();
    	
    	System.out.println("Expected Text: " + firstNameValidationMessage + " UI Text: " + uiFirstNameValidationMessage);
    	System.out.println("Expected Text: " + lastNameValidationMessage + " UI Text: " + uiLastNameValidationMessage);
    	System.out.println("Expected Text: " + emailValidationMessage + " UI Text: " + uiEmailValidationMessage);
    	System.out.println("Expected Text: " + passwordValidationMessage + " UI Text: " + uiPasswordValidationMessage);
    	System.out.println("Expected Text: " + passwordValidationMessage + " UI Text: " + uiConfirmPasswordValidationMessage);
    	
    	Assert.assertEquals(firstNameValidationMessage, uiFirstNameValidationMessage, "Expected Text: " + firstNameValidationMessage + "UI Text: " + uiFirstNameValidationMessage);
    	Assert.assertEquals(lastNameValidationMessage, uiLastNameValidationMessage, "Expected Text: " + lastNameValidationMessage + "UI Text: " + uiLastNameValidationMessage);
    	Assert.assertEquals(emailValidationMessage, uiEmailValidationMessage, "Expected Text: " + emailValidationMessage + "UI Text: " + uiEmailValidationMessage);
    	Assert.assertEquals(passwordValidationMessage, uiPasswordValidationMessage, "Expected Text: " + passwordValidationMessage + "UI Text: " + uiPasswordValidationMessage);
    	Assert.assertEquals(passwordValidationMessage, uiConfirmPasswordValidationMessage, "Expected Text: " + passwordValidationMessage + "UI Text: " + uiConfirmPasswordValidationMessage);
    }
    
    @Then("user selects their gender {string}")
    public void user_selects_their_gender(String gender)
    {
    	if (gender.equals("Male")) 
    	{
    		userRegister.genderMale().click();
    	}
    	else if (gender.equals("Female"))
    	{
    		userRegister.genderFemale().click();
    	}
    }
    
    @And("user enters their first name {string} last name {string} and email address {string}")
    public void user_enters_their_name_and_email(String fName, String lName, String email)
    {
    	userRegister.firstName().sendKeys(fName);
    	userRegister.lastName().sendKeys(lName);
    	userRegister.emailAddress().sendKeys(email);
    }
    
    @Then("user enters {string} in password and {string} in confirm password fields")
    public void user_enters_password_and_confirm_password(String password, String confirmPassword)
    {
    	Wait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
    	WebElement passwordField = fluentWait.until(driver -> userRegister.password());
    	passwordField.sendKeys(password);
    	userRegister.confirmPassword().sendKeys(confirmPassword);
    }
    
    @And("ensures the validation {string} is displayed for {string} field")
    public void ensures_validation_is_displayed_for_fields(String validationMessage, String fieldName)
    {
    	String uiMessage = "";
    	
    	switch (fieldName) 
    	{
    		case "Password":
    			uiMessage = userRegister.passwordValidation().getText();
    			break;
    		case "Confirm Password":
    			uiMessage = userRegister.confirmPasswordValidation().getText();
    			break;
    		case "Registration Complete":
    			uiMessage = userRegister.completeRegistrationMessage().getText();
    			break;
    	}
    		
    	
    	System.out.println("Expected Message: " + validationMessage + " UI Message: " + uiMessage);
    	Assert.assertEquals(validationMessage, uiMessage, "Expected Message: " + validationMessage + " UI Message: " + uiMessage);    	
    }
    
    @Then("user clears the passwprd fields")
    public void user_clears_the_passwprd_fields()
    {
    	userRegister.password().clear();
    	userRegister.confirmPassword().clear();
    }
}