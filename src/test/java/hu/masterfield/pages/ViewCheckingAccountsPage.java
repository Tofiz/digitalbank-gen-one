package hu.masterfield.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ViewCheckingAccountsPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@id='firstRow']/div/div")
    private WebElement firstTransaction;

    @FindBy(xpath = "//*[@id=\"transactionTable\"]/tbody/tr[1]/td[4]")
    private WebElement firstTransactionAmount;

    @FindBy(xpath = "//a[contains(text(),'View Checking')]")
    private WebElement viewCheckingAccountsLink;

    public ViewCheckingAccountsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToViewCheckingAccounts() {
        viewCheckingAccountsLink.click();
    }

    public boolean isTransactionVisible(String amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstTransaction));
        return firstTransactionAmount.getText().contains(amount);
    }

    public String getFirstTransactionAmount() {
        return firstTransactionAmount.getText();
    }
}
