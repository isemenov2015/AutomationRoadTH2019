package trello.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.BrowserFactory.driver;

public class LoginPage {

    private By emailFid = By.cssSelector("#user");
    private By passwordFid = By.cssSelector("#password");
    private By loginBtn = By.cssSelector("#login");

    public void open() {
        driver.get("https://trello.com/login");
    }

    public void login(String email, String password) {
        driver.findElement(emailFid).clear();
        driver.findElement(emailFid).sendKeys(email);
        driver.findElement(passwordFid).clear();
        driver.findElement(passwordFid).sendKeys(password);
        driver.findElement(loginBtn).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://trello.com/johantestoff/boards"));
    }
}
