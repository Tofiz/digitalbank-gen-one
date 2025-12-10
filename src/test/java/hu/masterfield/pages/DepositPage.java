package hu.masterfield.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DepositPage {

    private WebDriver driver;

    @FindBy(id = "selectedAccount")
    private WebElement accountSelect;

    @FindBy(id = "amount")
    private WebElement amountInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(id = "reset")
    private WebElement resetButton;

    public DepositPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectAccount(String accountName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='selectedAccount']/option"), 1));
        Select select = new Select(accountSelect);

        try {
            select.selectByVisibleText(accountName);
        } catch (Exception e) {

            if (!select.getOptions().isEmpty()) {
                select.selectByIndex(0); 
            }
        }
    }


    public void selectAccountByValue(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//select[@id='selectedAccount']/option"), 1));
        Select select = new Select(accountSelect);
        select.selectByValue(value);
    }

    public void enterAmount(String amount) {
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public void submitDeposit() {
        submitButton.click();
    }
}
