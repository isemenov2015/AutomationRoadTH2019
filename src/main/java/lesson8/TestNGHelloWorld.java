package lesson8;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class TestNGHelloWorld {

    private WebDriver driver;
    private String expectedTitle = "Save Time Editing PDF Documents Online For Free";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterTest
    public void testOver() {
        driver.close();
    }

    @Test
    public void firstTestNGExampleTest() {

        driver.get("https://pdffiller.com");
        WebElement titleElement = driver.findElement(By.xpath("//*[@class='page-title']"));
        WebElement homeElement = driver.findElement(By.xpath(("/html/body/div[1]/div[1]/div/div[2]/nav/ul/li[1]/a")));
        String actualHomeElement = homeElement.getText();

        Assert.assertEquals(titleElement.getText(), expectedTitle);
        Assert.assertTrue(expectedTitle.equals(titleElement.getText()));
        // Assert.fail("Test failed example message");
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualHomeElement, "HOME", "Home button is incorrect");
        softAssert.assertAll();
    }
}
