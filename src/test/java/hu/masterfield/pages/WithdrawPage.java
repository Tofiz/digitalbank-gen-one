package hu.masterfield.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class WithdrawPage extends BasePage {

    @FindBy(id = "selectedAccount")
    private WebElement accountSelect;

    @FindBy(id = "amount")
    private WebElement amountInput;

    @FindBy(css = "button.btn.btn-primary.btn-sm")
    private WebElement submitButton;

    public WithdrawPage(WebDriver driver) {
        super(driver);
    }

    public void createWithdraw(String amount) {
        // Using the requested XPath to find the second option
        WebElement accountOption = driver.findElement(By.xpath("//*[@id='selectedAccount']/option[2]"));
        accountOption.click();

        amountInput.sendKeys(amount);
        submitButton.click();
    }
}