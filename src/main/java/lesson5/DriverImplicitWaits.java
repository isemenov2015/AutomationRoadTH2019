package lesson5;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DriverImplicitWaits extends BrowserFactory {

    @Test
    public void pageLoadWait() {
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://google.com");
    }

    @Test
    public void elementWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://www.imdb.com/chart/top");
        String text = driver.findElement(By.cssSelector(".article .header")).getText();
        System.out.println(text);
    }
}
