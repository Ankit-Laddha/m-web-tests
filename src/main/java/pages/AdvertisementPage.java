package pages;

import model.Advertisement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdvertisementPage extends BasePage {

    @FindBy(name = "name")
    protected WebElement advertisementName;

    @FindBy(name = "street")
    protected WebElement street;

    @FindBy(name = "rooms")
    protected WebElement rooms;

    @FindBy(name = "price")
    protected WebElement price;

    @FindBy(css = ".md-container")
    protected WebElement status;

    @FindBy(css = ".md-toast-content")
    protected WebElement toastMsg;

    @FindBy(xpath = "//span[normalize-space()='save']")
    protected WebElement save;

    public AdvertisementPage(WebDriver driver) {
        super(driver);
    }

    public AdvertisementPage addAdvert(Advertisement advert) {
        enterText(advertisementName, "Advert" + advert.name());
        if (advert.street() != null)
            enterText(street, advert.street());
        if (advert.rooms() != 0)
            enterText(rooms, String.format("%s", advert.rooms()));
        if (advert.status)
            status.click();
        enterText(price, String.format("%s", advert.price()));

        save.click();
        waitUntilElementIsDisplayed(toastMsg);
        return this;
    }

    public boolean isAdvertSavedSuccessfully() {
        return toastMsg.getText().equals("Saved successfully");
    }

    public AdvertisementPage changeRooms(int roomsValue) {
        enterText(rooms, String.format("%s", roomsValue));
        save.click();
        waitUntilElementIsDisplayed(toastMsg);
        return this;
    }


    @Override
    public void verifyPageIsLoaded() {
        waitUntilElementIsDisplayed(advertisementName);
    }

}
