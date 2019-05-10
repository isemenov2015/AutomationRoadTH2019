package pp_payments_report;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class PPReportDownload {
    private static WebDriver driver;
    private static String ppPath;
    private static String login;
    private static String password;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner input;

        FileDialog dialog = new FileDialog((Frame)null, "Select PP login credentials file");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getDirectory() + dialog.getFile();
        dialog.dispose();

        input = new Scanner(new FileReader(file));
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0: ppPath = input.nextLine(); break;
                case 1: login = input.nextLine(); break;
                case 2: password = input.nextLine(); break;
            }
        }

        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/version74/chromedriver");
        driver = new ChromeDriver();
        driver.get(ppPath);
        driver.findElement(By.xpath("//*[@name='login_email']")).sendKeys(login);
        driver.findElement(By.xpath("//*[@name='login_email']")).sendKeys(Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name='login_password']")));
        driver.findElement(By.xpath("//*[@name='login_password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@name='login_password']")).sendKeys(Keys.RETURN);

        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-testid='navigation_transactions']")));
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@data-testid='navigation_transactions']")).click();

        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'rowClick redirect')]")));
        Thread.sleep(5000);
        List<WebElement> dropdownMenuList = driver.findElements(By.xpath("//*[contains(@class, 'paypal-react-dropdown-component')]"));
        dropdownMenuList.get(2).click();

        Thread.sleep(500);
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Balance affecting']")));
        driver.findElement(By.xpath("//*[@title='Balance affecting']")).click();

        Thread.sleep(5000);
        dropdownMenuList.get(4).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@data-duration='90']")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(@class, 'downloadTrigger')]/a")).click();

        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[contains(@class, 'react-dropdown')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[contains(@title, '3 month')]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id='dlogSubmit']")).click();
        driver.close();
    }
}
