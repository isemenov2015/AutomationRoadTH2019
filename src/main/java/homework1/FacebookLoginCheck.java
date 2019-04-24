package homework1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FacebookLoginCheck {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");


        // FB credentials input (in order to not reveal own FB login and password)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your FB login email: ");
        String login = scanner.nextLine();
        System.out.println("Enter your FB password: ");
        String password = scanner.nextLine();
        scanner.close();
        System.out.println("You entered login: " + login + ", and password: " + password);


        // Escape "Show notifications" Chrome dialog sequence start
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        // escape dialog sequence end

        // Start driver and main input actions sequence
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://facebook.com");
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).sendKeys(login);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("loginbutton")).click();
        driver.findElement(By.name("mercurymessages")).click();

        //Pause till message dialog is loaded
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//a[contains(@class, 'messagesContent')]")));


        WebElement messageWrapper = driver.findElement(By.xpath(".//a[contains(@class, 'messagesContent')]"));
        WebElement message = messageWrapper.findElement(By.xpath(".//div[contains(@class, '_1iji')]"));

        System.out.println("First chat message text: " + message.getText());

        driver.quit();
    }
}
