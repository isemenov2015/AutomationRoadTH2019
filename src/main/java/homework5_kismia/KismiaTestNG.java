package homework5_kismia;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class KismiaTestNG {

    private WebDriver driver;
    private static final String loginMale = "werter123@protonmail.com";
    private static final String passwordMale = "werter123";
    //private String loginFemale = "fignakapse@desoz.com";
    //private String passwordFemale = "werter123";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        //System.setProperty("webdriver.chrome.driver", "e:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://kismia.com");
    }

    @AfterTest
    public void testOver() {
        driver.close();
    }

    @Test
    public void loginTest() {

        driver.findElement(By.xpath("//*[contains(@class, 'js_emailField')]")).sendKeys(loginMale);
        driver.findElement(By.xpath("//*[contains(@class, 'js_passwordField')]")).sendKeys(passwordMale);
        driver.findElement(By.xpath("//*[contains(@class, 'js_passwordField')]")).sendKeys(Keys.RETURN);

        Assert.assertEquals(driver.getTitle(), "Kismia");
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void messageTest() {
        Random randomGenerator = new Random();
        int randomKey = randomGenerator.nextInt(1000000);

        driver.findElement(By.xpath("//*[contains(@class, 'link--messages')]")).click();
        driver.findElement(By.xpath("//*[@id=\"standard-threads-container\"]/li")).click();
        driver.findElement(By.xpath("//*[@name='message']")).sendKeys("Hello! I love you, won't you tell me your name? " + randomKey);
        driver.findElement(By.xpath("//*[contains(@class, 'chat-controls__button--green')]")).click();

        String messagesXPath = "//*[contains(@class, 'chat__message--from')]";
        List<WebElement> messagesList = driver.findElements(By.xpath(messagesXPath));

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(messagesXPath), messagesList.size()));

        messagesList = driver.findElements(By.xpath(messagesXPath));
        String lastMessageText = messagesList.get(messagesList.size() - 1).getText();
        Assert.assertTrue(lastMessageText.contains(String.valueOf(randomKey)));
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void ageChangeTest() {
        driver.findElement(By.xpath("//*[contains(@class, 'icon--header-sub--improve')]")).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@href, '/profile/settings')]")));
        driver.findElement(By.xpath("//*[contains(@href, '/profile/settings')]")).click();

        List<WebElement> elementsList = driver.findElements(By.xpath("//*[contains(@class, 'settings-nav__a')]"));
        elementsList.get(1).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='year']")));
        driver.findElement(By.xpath("//*[@id='year']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='1985']")));

        Random randomGenerator = new Random();
        String birthYear = String.valueOf(2018 - 21 - randomGenerator.nextInt(15));
        driver.findElement(By.xpath("//*[@value='" + birthYear + "']")).click();

        String buttonXPath = "//*[contains(@class, 'button--flat-green js_save')]";
        driver.findElement(By.xpath(buttonXPath)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(buttonXPath)));

        WebElement button = driver.findElement(By.xpath(buttonXPath));
        Assert.assertFalse(button.isDisplayed());
    }
}
