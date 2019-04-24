/*
QA Automation Course 2019

Написать программу которая будет отображать кол-во мест в зале кинотеатра:

Кол-во мест в зале
Кол-во мест занятых
Кол-во мест свободных
% Занятых мест от “всего мест в зале”
% Свободных мест от “всего мест в зале”

Что делает программа:

Переходим на http://liniakino.com/showtimes/aladdin/
Выбираем фильм “Дамбо” - ближайший сеанс.
Открываем схему зала
Считаем места
Выводим результат в консоль
 */

package homework2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.List;

public class CountNumberOfSeats {
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void printFilmAttributes() throws InterruptedException {
        driver.get("http://liniakino.com/showtimes/aladdin/");

        driver.findElement(By.xpath("//h1/a[contains(@href, '7531')]/../..//li/a")).click();

        //Pause till seats scheme is loaded
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'arcticmodal-close')]")));

        driver.switchTo().frame(0);
        wait = new WebDriverWait(driver, 5);

        // Try-Catch since they close online tickets sale 15 minutes before the film is started
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'arcticmodal-close')]")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click()", element);

            List<WebElement> seats = driver.findElements(By.xpath("//*[contains(@id, 'hseat')]"));
            List<WebElement> occupiedSeats = driver.findElements(By.xpath("//*[contains(@class, 'occupied')]"));

            int totalSeats = seats.size();
            int totalOccupiedSeats = occupiedSeats.size();

            System.out.println("Total seats: " + totalSeats);
            System.out.println("Occupied seats: " + totalOccupiedSeats);
            System.out.println("Total free seats: " + (totalSeats - totalOccupiedSeats));

            DecimalFormat df = new DecimalFormat("#.##");
            System.out.println("Occupied seats percent: " + df.format(100.0*(totalOccupiedSeats)/totalSeats) + "%");
            System.out.println("Free seats percent: " + df.format(100.0*(totalSeats - totalOccupiedSeats)/totalSeats) + "%");
        }
        catch (TimeoutException e) {
            System.out.println("You're unlucky. Online tickets sale for the nearest cinema session is closed. Try again in 5 minutes.");
        }
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}