package homework2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class GetFilmShawshankRedemptionProperties {
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void printFilmAttributes() {
        driver.get("https://www.imdb.com/title/tt0111161/");

        String[] duration = driver.findElement(By.xpath("//*[@id='title-overview-widget']//time")).getText().split(" ");
        int durationSec = Integer.parseInt(duration[0].replaceAll("[^0-9]", "")) * 3600 +  // hours -> seconds
                Integer.parseInt(duration[1].replaceAll("[^0-9]", "")) * 60;   // minutes -> seconds

        System.out.println("Film info:");
        System.out.println("Title: " + driver.findElement(By.xpath("//*[@class='title_wrapper']/h1")).getText());
        System.out.println("Release date: " + driver.findElement(By.xpath("//*[@class='subtext']//a[2]")).getText());
        System.out.println("Duration in minutes: " + durationSec / 60);
        System.out.println("Duration in seconds: " + durationSec);
        System.out.println("Rating: " + driver.findElement(By.xpath("//*[@itemprop='ratingValue']")).getText());
        System.out.println("Genre: " + driver.findElement(By.xpath("//*[@class='subtext']//a[1]")).getText());
        System.out.println("Trailer link: " + driver.findElement(By.xpath("//*[@class='slate']//a")).getAttribute("href"));
        System.out.println("Poster link: " + driver.findElement(By.xpath("//*[@class='poster']//a/img")).getAttribute("src"));
        System.out.println("Director: " + driver.findElement(By.xpath("//*[@class='credit_summary_item']//a")).getText());

        List<WebElement> actorsList = driver.findElements(By.xpath("//*[@class='cast_list']/tbody/tr/td[2]/a"));
        int counter = 0;
        System.out.println("5 film actors:");
        for (WebElement element : actorsList) {
            System.out.println("   " + element.getText());
            counter++;
            if (counter > 4) break;
        }

        System.out.println("Metascore rating: " + driver.findElement(By.xpath("//*[contains(@class, 'metacriticScore')]/span")).getText());
        String userReviewsNumber = driver.findElement(By.xpath("//*[contains(@class, 'titleReviewBarItem ')]/div/span/a[1]")).getText();
        String criticsReviewsNumber = driver.findElement(By.xpath("//*[contains(@class, 'titleReviewBarItem ')]/div/span/a[2]")).getText();
        userReviewsNumber = userReviewsNumber.replaceAll("[^0-9]", "");
        criticsReviewsNumber = criticsReviewsNumber.replaceAll("[^0-9]", "");
        System.out.println("Number of reviews:");
        System.out.println("   Users: " + userReviewsNumber);
        System.out.println("   Critics: " + criticsReviewsNumber);
        System.out.println("   Users + Critics: " + (Integer.parseInt(userReviewsNumber) + Integer.parseInt(criticsReviewsNumber)));

        List<WebElement> similarList = driver.findElements(By.xpath("//*[contains(@class, 'rec_item')]/a/img"));
        counter = 0;
        System.out.println("3 similar films:");
        for (WebElement element : similarList) {
            System.out.println("   " + element.getAttribute("title"));
            counter++;
            if (counter > 2) break;
        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}