package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "deposit-menu-item")
    private WebElement depositLink;

    @FindBy(id = "withdraw-menu-item")
    private WebElement withdrawLink;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public DepositPage navigateToDepositPage() {
        depositLink.click();
        return new DepositPage(driver, wait);
    }

    public WithdrawPage navigateToWithdrawPage() {
        withdrawLink.click();
        return new WithdrawPage(driver);
    }
}