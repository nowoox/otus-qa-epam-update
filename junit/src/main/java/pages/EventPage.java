package pages;

import base.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EventPage extends BasePage {

    public EventPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = "//label[contains(text(), 'QA')]")
    private WebElement searchLabel;

    public void checkIfLabelPresented() {

        waitVisibilityOfElement(searchLabel);
        Assertions.assertTrue(searchLabel.isDisplayed(), "Label is not presented");
        logElementIsDisplayed(searchLabel);

    }
}
