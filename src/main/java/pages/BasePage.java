package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        waitForPageLoad();
        verifyPageIsLoaded();
    }

    /**
     * Used for waiting for the page to load
     */
    public abstract void verifyPageIsLoaded();

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        wait.until(pageLoadCondition);
    }

    protected void enterText(WebElement element, String value) {
        waitUntilElementIsClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    protected void waitUntilElementIsClickable(WebElement element) {
        waitUntilElementIsDisplayed(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilElementIsDisplayed(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitUntilElementIsDisplayed(WebElement element) {
        wait.until((ExpectedCondition<Boolean>) driver -> isElementDisplayed(element));
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }
}
