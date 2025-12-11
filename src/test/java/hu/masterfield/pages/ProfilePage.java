package hu.masterfield.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {

    @FindBy(id = "title")
    private WebElement titleDropdown;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "homePhone")
    private WebElement homePhoneInput;

    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneInput;

    @FindBy(id = "workPhone")
    private WebElement workPhoneInput;

    @FindBy(id = "address")
    private WebElement addressInput;

    @FindBy(id = "locality")
    private WebElement localityInput;

    @FindBy(id = "region")
    private WebElement regionInput;

    @FindBy(id = "postalCode")
    private WebElement postalCodeInput;

    @FindBy(id = "country")
    private WebElement countryInput;

    @FindBy(xpath = "//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[2]/button[1]")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"right-panel\"]/div[2]/div/div/div/div/form/div[1]/div[1]/div/span[2]")
    private WebElement successMessage;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void updateProfile(String title, String firstName, String lastName) {
        selectByVisibleText(titleDropdown, title);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        saveButton.click();
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }
}