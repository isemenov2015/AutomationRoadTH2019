/*
    Вывести в консоль список фильмов которые показывают в кинотеатре http://liniakino.com/showtimes/aladdin/
 */

package lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShowAladdinCinemaMovies extends BrowserFactory {
    @Test
    public void showMovies() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("http://liniakino.com/showtimes/aladdin/");
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(@class, 'showtime-mo')]/h1"));
        for (WebElement el : elements) {
            System.out.println(el.getText());
        }
    }

    @Test
    public void showMoviesByCSSSelector() {
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("http://liniakino.com/showtimes/aladdin/");
        List<WebElement> elements = driver.findElements(By.cssSelector(".showtime-movie h1"));
        for (WebElement el : elements) {
            System.out.println(el.getText());
        }
    }
    //.showtime-movie h1
}
