import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

public class SauceDemoTests {
    WebDriver driver;
    private static Logger log = Logger.getLogger(SauceDemoTests.class.getSimpleName());

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/index.html");
    }

    @Test
    public void loginTest() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String actualUrl = driver.getCurrentUrl();

        returnElementNamesAndPrices();

        Assert.assertEquals(actualUrl, "https://www.saucedemo.com/inventory.html");
    }


    public void returnElementNamesAndPrices() {
        List<WebElement> elementsNames = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        List<WebElement> elementsPrices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));

        for (int i = 0; i < elementsNames.size(); i++) {
            log.info(elementsNames.get(i).getText() + "\t" + elementsPrices.get(i).getText());
        }
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}