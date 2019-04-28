package homework4_fbbirthday;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class FBBirthdayGreetings {
    String[] birthdayMessage = {"Have a great birthday!", "Celebrate well!", "Best wishes!", "Happy birthday!"};
    private WebDriver driver;

    private String fakeAccLogin1 = "scb19189@cndps.com"; // birthday set to 28.04.1994
    private String fakeAccPass1 = "ttrroo78&#";
    private String fakeAccLogin2 = "pro20094@cndps.com"; // birthday set to 28.04.1992
    private String fakeAccPass2 = "ppddmm45@#";

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
        driver.findElement(By.id("email")).sendKeys(fakeAccLogin1);
        driver.findElement(By.id("pass")).sendKeys(fakeAccPass1);
        driver.findElement(By.id("loginbutton")).click();
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
    public void firstTestNGExampleTest() {

        fbLogin(driver);
        driver.findElement(By.xpath("//*[@id=\"u_0_c\"]/a")).click();
        driver.findElement(By.xpath("//*[@ajaxify=\"/birthday/reminder/dialog/\"]./..")).click();
    }
}
