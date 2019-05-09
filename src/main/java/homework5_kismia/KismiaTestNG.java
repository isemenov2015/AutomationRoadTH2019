package homework5_kismia;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class KismiaTestNG {

    private WebDriver driver;
    private String loginMale = "werter123@protonmail.com";
    private String passwordMale = "werter123";

    @BeforeTest
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        System.setProperty("webdriver.chrome.driver", "e:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterTest
    public void testOver() {
        driver.close();
    }

    @Test
    public void firstTestNGExampleTest() {

        driver.get("https://kismia.com");

        driver.findElement(By.xpath("//*[contains(@class, 'js_emailField')]")).sendKeys(loginMale);
        driver.findElement(By.xpath("//*[contains(@class, 'js_passwordField')]")).sendKeys(passwordMale);
        driver.findElement(By.xpath("//*[contains(@class, 'js_passwordField')]")).sendKeys(Keys.RETURN);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(), "Kismia", "Login failed");
        softAssert.assertAll();

        /*
        WebElement titleElement = driver.findElement(By.xpath("//*[@class='page-title']"));
        WebElement homeElement = driver.findElement(By.xpath(("/html/body/div[1]/div[1]/div/div[2]/nav/ul/li[1]/a")));
        String actualHomeElement = homeElement.getText();

        Assert.assertEquals(titleElement.getText(), expectedTitle);
        Assert.assertTrue(expectedTitle.equals(titleElement.getText()));
        // Assert.fail("Test failed example message");
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualHomeElement, "HOME", "Home button is incorrect");
        softAssert.assertAll();
         */
    }
}
