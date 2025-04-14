package stepdefinitions;

import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import java.time.Duration;

public class LoginSteps
{
	WebDriver driver;
    LoginPage loginPage;
    
    @Given("user opens login page")
    public void the_user_is_on_the_login_page()
    {
    	WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	System.out.println("System Waited for 10 Seconds");
    	driver.get("https://demowebshop.tricentis.com/login");
    	loginPage = new LoginPage(driver);
    }
    
    @When("user tries to login with email {string} and password {string}")
    public void the_user_tries_to_login_with_credentials(String email, String password)
    {
    	loginPage.getEmailField().sendKeys(email);
    	loginPage.getPasswordField().sendKeys(password);
    }
    
    @And("clicks the login button")
    public void clicks_the_login_button()
    {
    	loginPage.getLoginButton().click();
    }
    
    @Then("the user should see their account page")
    public void the_user_should_see_their_account_page()
    {
    	WebElement userName = driver.findElement(By.xpath("//div[1]/div[2]/div[1]/ul/li[1]/a"));
    	String uiText = userName.getText();
    	String expectedText = "testuser_ak@example.com";
    	
    	Assert.assertTrue(userName.isDisplayed());
    	System.out.println("Login successful!");
    	System.out.println("Expected Text: " + expectedText + " UI Text: " + uiText);
    	Assert.assertEquals(expectedText, uiText, "Expected Text: " + expectedText + "UI Text: " + uiText);
    	driver.quit();
    }
}