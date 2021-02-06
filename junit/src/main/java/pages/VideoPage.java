package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class VideoPage extends BasePage {

    @FindBy(css = "div.evnt-toogle-filters-text:nth-child(1) > span:nth-child(1)")
    private WebElement moreFilterButton;

    @FindBy(css = "#filter_location")
    private WebElement locationDropDown;

    @FindBy(css = "#filter_language")
    private WebElement languageDropDown;

    @FindBy(css = "#filter_category")
    private WebElement categoryDropDown;

    @FindBy(xpath = "//*[@data-value='Belarus']")
    private WebElement belarusItem;

    @FindBy(xpath = "//*[@data-value='ENGLISH']")
    private WebElement englishLanguageItem;

    @FindBy(xpath = "//*[@data-value='Testing']")
    private WebElement testingCategoryItem;

    @FindBy(xpath = "//input[contains(@class, 'evnt-search') and contains(@placeholder, 'Search by Talk Name')]")
    private WebElement searchField;

    @FindBy(xpath = "//div[contains(@class, 'evnt-talks-column')]")
    private List<WebElement> listOfCards;

    @FindBy(xpath = "//span[contains(text(), 'QA')]")
    private WebElement resultFoundQA;

    @FindBy(xpath = "//span[contains(text(), 'Testing')]")
    private WebElement resultFoundTesting;

    @FindBy(xpath = "//div[contains(@class, 'collapsing')]")
    private WebElement progress;

    String query = "QA";

    public VideoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public EventPage eventPage = new EventPage(driver);

    @Step
    public void adjustFilter() {

        waitVisibilityOfElement(moreFilterButton);
        logElementIsDisplayed(moreFilterButton);

        moreFilterButton.click();
        logElementIsClicked(moreFilterButton);

        waitIsClickable(locationDropDown);
        logElementIsNotDisplayed(locationDropDown);

        locationDropDown.click();
        logElementIsClicked(locationDropDown);

        belarusItem.click();
        logElementIsClicked(belarusItem);

        languageDropDown.click();
        logElementIsClicked(languageDropDown);

        englishLanguageItem.click();
        logElementIsClicked(englishLanguageItem);

        categoryDropDown.click();
        logElementIsClicked(categoryDropDown);

        testingCategoryItem.click();
        logElementIsClicked(testingCategoryItem);

    }

    @Step
    public void checkFoundTalksInfo() {

        String originalHandle = driver.getWindowHandle();


        waitVisibilityOfElement(resultFoundTesting);
        logElementIsDisplayed(resultFoundTesting);

        for (WebElement ele : listOfCards) {

            Actions newTab = new Actions(driver);
            newTab.keyDown(Keys.CONTROL)
                    .click(ele)
                    .keyUp(Keys.CONTROL)
                    .build()
                    .perform();

            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            eventPage.checkIfLabelPresented();

            for(String handle : driver.getWindowHandles()) {
                if (!handle.equals(originalHandle)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
            }

            driver.switchTo().window(originalHandle);

        }

    }

    @Step
    public void typeAndSearch() {

        waitVisibilityOfElement(searchField);
        logElementIsDisplayed(searchField);

        searchField.sendKeys(query);
        logger.info("Typed text " + query + " to " + searchField);

        waitVisibilityOfElement(resultFoundQA);
        logElementIsDisplayed(resultFoundQA);

        Assertions.assertNotSame(listOfCards.size(), 0, "List of found items is empty");
        logger.info("Found " + listOfCards.size() + " talks");
    }

    @Step
    public void checkFoundTalksTitle() {

        for (WebElement ele : listOfCards) {

            Assertions.assertTrue(ele.findElement(By.xpath("//*[contains(@class, 'evnt-talk-name')]/h1/span"))
                    .getText()
                    .contains(query), "No query string in title");
            logger.info("Title of card " + listOfCards.indexOf(ele) + " contains query string");
        }

    }

}
