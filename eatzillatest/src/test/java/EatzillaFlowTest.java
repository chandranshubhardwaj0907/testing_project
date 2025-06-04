import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class EatzillaFlowTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        // Set up the ChromeDriver location
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\HP\\Desktop\\selenium webDriver\\chromedriver-win64\\chromedriver.exe");

        // Initialize the driver and maximize the window
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/"); // Replace with your app URL
    }

    @Test(priority = 1)
    public void loginTest() {
        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-btn"));

        email.sendKeys("c898998@gmail.com");
        password.sendKeys("Hello@123456");
        loginButton.click();

        // Wait for 6 seconds to allow the page to load
        try {
            Thread.sleep(6000); // Wait for 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("browse"), "Login failed or did not redirect to browse");
    }

    @Test(priority = 2)
    public void goToBrowsePage() {
        // Wait for 6 seconds to allow the page to load
        try {
            Thread.sleep(6000); // Wait for 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("/browse"), "Failed to reach browse page");
    }

    @Test(priority = 3)
    public void goToRestaurantsPage() {
        WebElement restaurantsLink = driver.findElement(By.linkText("Restaurants"));
        restaurantsLink.click();

        // Wait for 6 seconds to allow the page to load
        try {
            Thread.sleep(6000); // Wait for 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(driver.getCurrentUrl().contains("/restaurants"), "Failed to reach restaurants page");
    }

    @Test(priority = 4)
    public void goToRestaurantDetailsPage() {
        // Find the link using the updated id
        WebElement pizzaPalaceLink = driver.findElement(By.id("restaurant-1"));
        pizzaPalaceLink.click();

        // Wait for 6 seconds to allow the page to load
        try {
            Thread.sleep(6000); // Wait for 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Assert that the restaurant name is displayed
        WebElement restaurantName = driver.findElement(By.xpath("//h1[contains(text(),'Pizza Palace')]"));
        Assert.assertTrue(restaurantName.isDisplayed(), "Failed to navigate to the correct restaurant details page");
    }

    @Test(priority = 5)
    public void navigateToOrdersPage() {
        WebElement ordersLink = driver
                .findElement(By.xpath("//a[contains(@href, '/orders') and contains(., 'Orders')]"));
        ordersLink.click();

        // Wait for 6 seconds to allow the page to load
        try {
            Thread.sleep(6000); // Wait for 6 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement heading = driver.findElement(By.xpath("//h1[contains(text(), 'Your Cart')]"));
        Assert.assertTrue(heading.isDisplayed(), "Orders page did not load correctly");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
