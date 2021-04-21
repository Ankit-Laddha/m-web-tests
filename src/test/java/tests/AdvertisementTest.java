package tests;

import apiHelpers.AdvertisementApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Advertisement;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class AdvertisementTest extends BaseTest {


    @Test(priority = 1, groups = {"create-advert"})
    public void createNewAdvertisement() throws JsonProcessingException {

        //Given
        String randomString = RandomStringUtils.randomAlphabetic(10);
        int price = getRandomNumberUsingNextInt(100, 1000);
        Advertisement advertisement = new Advertisement()
                .name(randomString)
                .street(randomString)
                .status(true)
                .price(price);

        //When
        var advertisementPage = new HomePage(driver)
                .addNewAdvert()
                .addAdvert(advertisement);

        //Then
        assertThat(advertisementPage.isAdvertSavedSuccessfully()).isTrue();
        new HomePage(driver).waitForAdvertToBeAvailable(randomString);

        var advert = AdvertisementApi.findAdvertUsingApi(randomString);

        assertThat(advert).isNotNull();
        assertThat(advert.price()).isEqualTo(price);
        assertThat(advert.street()).isEqualTo(randomString);
        assertThat(advert.status()).isTrue();
    }


    @Test(priority = 2, groups = {"edit-advert"})
    public void editAdvertisement() throws JsonProcessingException {
        //Given
        String randomString = RandomStringUtils.randomAlphabetic(10);

        Advertisement advertisement = new Advertisement()
                .name(randomString)
                .rooms(getRandomNumberUsingNextInt(1, 10))
                .status(true)
                .price(getRandomNumberUsingNextInt(100, 1000));

        AdvertisementApi.createNewAdvertisementUsingAPI(advertisement);
        driver.navigate().refresh();

        //When
        int newRoomValue = getRandomNumberUsingNextInt(10, 20);
        new HomePage(driver)
                .waitForAdvertToBeAvailable(randomString)
                .editAdvert(randomString)
                .changeRooms(newRoomValue);

        //Then
        var advert = AdvertisementApi.findAdvertUsingApi(randomString);

        assertThat(advert).isNotNull();
        assertThat(advert.rooms()).isEqualTo(newRoomValue);
    }

    private int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
