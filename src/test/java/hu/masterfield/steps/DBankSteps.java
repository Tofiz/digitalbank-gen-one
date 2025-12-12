package hu.masterfield.steps;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import hu.masterfield.driver.BrowserType;
import hu.masterfield.driver.DriverInitializer;
import hu.masterfield.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DBankSteps {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private DepositPage depositPage;
    private ViewCheckingAccountsPage viewCheckingAccountsPage;
    private ProfilePage profilePage;
    private WithdrawPage withdrawPage;

    private double initialBalance;

    @Before
    public void setup() {
        driver = DriverInitializer.getDriver(BrowserType.CHROME_SELMGR);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @After
    public void cleanup(Scenario scenario) {
        if (scenario.isFailed()) {
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
        driver.quit();
    }

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        driver.get("https://eng.digitalbank.masterfield.hu/bank/login");
        try {
            Objects.requireNonNull(wait.until(ExpectedConditions.elementToBeClickable(By.className("cc-nb-okagree")))).click();
        } catch (TimeoutException e) {
            // GDPR popup did not appear or was not clickable in time.

        }
        loginPage = new LoginPage(driver);
    }

    @When("the user enters {string} and {string}")
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
        try {
            wait.until(ExpectedConditions.urlContains("home"));
        } catch (TimeoutException e) {
            fail("The login was not successful. Timed out waiting for dashboard. Current URL: " + driver.getCurrentUrl());
        }
        assertEquals("https://eng.digitalbank.masterfield.hu/bank/home", driver.getCurrentUrl());
        dashboardPage = new DashboardPage(driver, wait);
    }

    @Then("the registered user should not be redirected to the dashboard")
    public void theRegisteredUserShouldNotBeRedirectedToTheDashboard() {
        assertNotEquals("https://eng.digitalbank.masterfield.hubank/home", driver.getCurrentUrl());
    }

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        theUserIsOnTheLoginPage();
        theUserEntersUsernameAndPassword("tester01", "Kjhgfdsa.9");
        clicksTheLoginButton();
        theRegisteredUserShouldBeRedirectedToTheDashboard();
    }

    @Given("the user is on the deposit into account page")
    public void theUserIsOnTheDepositIntoAccountPage() {
        depositPage = dashboardPage.navigateToDepositPage();
    }

    @When("the user makes an individual checking deposit of {string}")
    public void theUserMakesAnIndividualCheckingDepositOf(String amount) {
        depositPage.createDeposit(amount);
    }

    @Then("the new transaction for {string} should be visible in the transaction list")
    public void theNewTransactionForShouldBeVisibleInTheTransactionList(String amount) {
        viewCheckingAccountsPage = new ViewCheckingAccountsPage(driver);
        assertTrue(viewCheckingAccountsPage.isTransactionVisible(amount), "Transaction not visible for amount: " + amount);
    }

    @And("the account balance should be updated accordingly")
    public void theAccountBalanceShouldBeUpdatedAccordingly() {
        String balanceText = viewCheckingAccountsPage.getFirstTransactionAmount();
        balanceText = balanceText.replace("$", "").trim();
        double newBalance = Double.parseDouble(balanceText);
        assertNotEquals(0.0, newBalance, "The balance has not been updated.");
    }

    @And("the user is on the profile page")
    public void theUserIsOnTheProfilePage() {
        driver.get("https://eng.digitalbank.masterfield.hu/bank/user/profile");
        profilePage = new ProfilePage(driver);
    }

    @When("the user updates their title to {string}, first name to {string} and last name to {string}")
    public void theUserUpdatesTheirTitleToFirstNameToAndLastNameTo(String title, String firstName, String lastName) {
        profilePage.updateProfile(title, firstName, lastName);
    }

    @Then("a success message {string} is displayed")
    public void aSuccessMessageIsDisplayed(String message) {
        assertEquals(message, profilePage.getSuccessMessage());
    }

    @Given("the user is on the withdraw from account page")
    public void theUserIsOnTheWithdrawFromAccountPage() {
        withdrawPage = dashboardPage.navigateToWithdrawPage();
    }

    @When("the user makes an individual cheking withdraw of {string}")
    public void theUserMakesAnIndividualChekingWithdrawOf(String amount) {
        withdrawPage.createWithdraw(amount);
    }

    @Then("the user checks the layout of the dashboard")
    public void theUserChecksTheLayoutOfTheDashboard() throws IOException {
        LayoutReport layoutReport = Galen.checkLayout(driver, "src/test/resources/specs/dashboard.gspec", Collections.singletonList("desktop"));

        List<GalenTestInfo> tests = new LinkedList<>();
        GalenTestInfo test = GalenTestInfo.fromString("Dashboard layout");
        test.getReport().layout(layoutReport, "Check dashboard layout");
        tests.add(test);

        String reportPath = "target/galen-html-reports";
        new HtmlReportBuilder().build(tests, reportPath);

        if (layoutReport.errors() > 0) {
            fail("Layout test failed. Found " + layoutReport.errors() + " errors. See report at: " + reportPath);
        }
    }
}
