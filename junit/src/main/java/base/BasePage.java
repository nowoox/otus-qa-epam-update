package base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public enum EventsType {
        PAST,
        UPCOMNIG
    }

    public WebDriver driver;
    public WebDriverWait webDriverWait;
    public Logger logger = LogManager.getLogger(BaseTest.class);

    public BasePage(WebDriver webDriver) {
        this.driver = webDriver;
        webDriverWait = new WebDriverWait(driver, 15);
        PageFactory.initElements(driver, this);
    }

    public void click(By elementBy) {
        driver.findElement(elementBy).click();
    }

    public void waitIsClickable(WebElement ele) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
    }

    public void waitVisibilityOfElement(WebElement ele) {
        webDriverWait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitInvisibilityOfElement(WebElement ele) {
        webDriverWait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void logElementIsDisplayed(WebElement ele) {
        logger.info("Element is displayed: " + ele);
    }

    public void logElementIsNotDisplayed(WebElement ele) {
        logger.info("Element is not displayed: " + ele);
    }

    public void logElementIsClicked(WebElement ele) {
        logger.info("Click completed on element: " + ele);
    }

}
