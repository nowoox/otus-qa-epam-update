package base;

import driver.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.EventPage;
import pages.EventsPage;
import pages.MainPage;
import pages.VideoPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    //protected RemoteWebDriver driver;
    public Logger logger = LogManager.getLogger(BaseTest.class);
    public MainPage mainPage;
    public EventsPage eventsPage;
    public EventPage eventPage;
    public VideoPage videoPage;
    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriverManager.chromedriver().setup();
        driver = driverFactory.createDriver();
        logger.info("Driver was started");


////        String selenoidURL = "http://localhost:4444/wd/hub";
////        DesiredCapabilities caps = new DesiredCapabilities();
////        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        caps.setBrowserName("chrome");
//        caps.setVersion("88.0");
//        caps.setCapability("enableVNC", true);
//        caps.setCapability("screenResolution", "1280x1024");
//        caps.setCapability("enableVideo", false);
//        caps.setCapability("enableLog", true);
//        caps.setCapability("headless", true);
//        driver = new RemoteWebDriver(new URL(selenoidURL), caps);

//        ChromeOptions options = new ChromeOptions();
        //       options.addArguments("start-maximized"); // open Browser in maximized mode
//        options.addArguments("disable-infobars"); // disabling infobars
//        options.addArguments("--disable-extensions"); // disabling extensions
//        options.addArguments("--disable-gpu"); // applicable to windows os only
//        //options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//        options.addArguments("--no-sandbox"); // Bypass OS security model
//        options.addArguments("--whitelisted-ips='127.0.0.1'");

//        caps.setCapability(ChromeOptions.CAPABILITY, options);

//        WebDriver driver = new RemoteWebDriver(new URL(selenoidURL), caps);

//        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        mainPage = PageFactory.initElements(driver, MainPage.class);
        eventsPage = PageFactory.initElements(driver, EventsPage.class);
        eventPage = PageFactory.initElements(driver, EventPage.class);
        videoPage = PageFactory.initElements(driver, VideoPage.class);

        driver.get("https://events.epam.com");
    }

    @AfterEach
    void setDown() {
        driver.quit();
        logger.info("Driver was stopped");
    }

}
