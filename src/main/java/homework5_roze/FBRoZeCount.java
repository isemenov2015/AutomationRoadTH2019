package homework5_roze;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class FBRoZeCount {
    private WebDriver driver;
    private String login = "";
    private String password = "";

    private static ChromeOptions setChromeOptions() {
        // Escape "Show notifications" Chrome dialog sequence start
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        return options;
    }

    private void fbLogin(WebDriver driver) {

        driver.get("https://facebook.com");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("loginbutton")).click();
    }

    private static String getWordsFromFBFeed(WebDriver driver) {

        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        for (int i = 0; i < 50; i++)
            jsx.executeScript("window.scrollBy(0,1000)", "");

        return driver.findElement(By.xpath("//div[contains(@id, 'feed_stream_')]")).getText().toLowerCase();
    }


    @BeforeTest
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        driver = new ChromeDriver(setChromeOptions());
    }

    @AfterTest
    public void testOver() {
        driver.close();
    }

    @Test
    public void countRoZe() {
        if (login.length() == 0 || password.length() == 0) {
            System.out.println("Empty FB credentials, fill login/password");
        } else {
            fbLogin(driver);
            String feedTxt = getWordsFromFBFeed(driver);
            System.out.println("Total words in feed: " + (feedTxt.split(" ").length - 1));
            System.out.println("Mr. Poroshenko mentioned: " + (feedTxt.split("порош").length - 1) + " times");
            System.out.println("Mr. Zelensky mentioned: " + (feedTxt.split("зеленс").length - 1) + " times");
        }
    }
}