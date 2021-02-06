package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(xpath = "//a[contains(@href, '/events')]")
    private WebElement eventLink;

    @FindBy(xpath = "//a[contains(@href, '/video')]")
    private WebElement videoLink;

    @FindBy(css = "#onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;

    @FindBy(css = "#onetrust-banner-sdk")
    private WebElement cookiesBanner;


    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public void acceptCookies() {

        webDriverWait.until(ExpectedConditions.visibilityOf(cookiesBanner));
        logElementIsDisplayed(cookiesBanner);

        acceptCookiesButton.click();
        logElementIsClicked(acceptCookiesButton);

        waitInvisibilityOfElement(cookiesBanner);
        logElementIsNotDisplayed(cookiesBanner);

    }

    @Step
    public void openEvents() {

        acceptCookies();

        eventLink.click();
        logElementIsClicked(eventLink);
    }

    @Step
    public void openVideos() {

        acceptCookies();

        videoLink.click();
        logElementIsClicked(videoLink);

    }

}
