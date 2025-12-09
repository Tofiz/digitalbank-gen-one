package hu.masterfield.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverInitializer {

    public static WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case CHROME_SELMGR: {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-infobars");
                options.addArguments("--start-maximized");

                return new ChromeDriver(options);
            }
            case FIREFOX_SELMGR: {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--private");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-infobars");
                options.addArguments("--start-maximized");

                return new FirefoxDriver(options);
            }
            case EDGE_SELMGR: {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--inprivate");
                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--disable-infobars");
                options.addArguments("--start-maximized");

                return new EdgeDriver(options);
            }
            default:
                throw new IllegalArgumentException("Invalid browser type: " + browserType);
        }
    }
}
