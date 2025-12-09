package hu.masterfield.steps;

import hu.masterfield.driver.BrowserType;
import hu.masterfield.driver.DriverInitializer;
import hu.masterfield.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBankSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup() {
        driver = DriverInitializer.getDriver(BrowserType.CHROME_SELMGR);
    }

    @After
    public void cleanup() {
        driver.quit();
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        driver.get("https://dbank-qa.wedevx.co/bank/login");
        loginPage = new LoginPage(driver);
    }

    @When("the user enters {string} and {string} password")
    public void theUserEntersUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("clicks the login button")
    public void clicksTheLoginButton() {
        loginPage.clickLogin();
    }

    @Then("the registered user should be redirected to the dashboard")
    public void theRegisteredUserShouldBeRedirectedToTheDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("home"));
        assertEquals("https://dbank-qa.wedevx.co/bank/home", driver.getCurrentUrl());
    }
}
