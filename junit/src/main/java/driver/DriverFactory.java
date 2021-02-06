package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private final ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<RemoteWebDriver>();

    public WebDriver createDriver() {

        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless", "--silent");
        options.addArguments("--start-maximized");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver.set(new ChromeDriver(options));

        return driver.get();
    }

    public RemoteWebDriver createRemoteDriver() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("80.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        remoteDriver.set(new RemoteWebDriver(
                new URL("http://0.0.0.0:4444/wd/hub"), capabilities));

        return remoteDriver.get();
    }

}
