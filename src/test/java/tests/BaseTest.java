package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    public static final String baseUrl = "https://admin-advertisement.herokuapp.com";
    public static final String apiUrl = baseUrl + "/api/advertisements";
    public static final String webUrl = baseUrl + "/advertisements";
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = apiUrl;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(webUrl);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null)
            driver.quit();
        driver = null;
    }
}
