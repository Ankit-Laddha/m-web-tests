package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {


    @FindBy(css = ".al-add__icon")
    protected WebElement addNewAdvert;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public By advertLocator(String suffix) {
        return By.xpath(String.format("//td[normalize-space()='Advert%s']", suffix));
    }

    public AdvertisementPage addNewAdvert() {
        addNewAdvert.click();
        return new AdvertisementPage(driver);
    }

    public AdvertisementPage editAdvert(String advertName) {
        driver.findElement(advertLocator(advertName)).click();
        return new AdvertisementPage(driver);
    }

    public HomePage waitForAdvertToBeAvailable(String advertName) {
        waitUntilElementIsDisplayed(advertLocator(advertName));
        return this;
    }


    @Override
    public void verifyPageIsLoaded() {
        waitUntilElementIsDisplayed(addNewAdvert);
    }
}
