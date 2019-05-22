package trello;

import core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import trello.pages.LoginPage;

import java.io.IOException;

public class LoginTest extends BrowserFactory {

    @Test
    public void login() throws IOException {
        LoginPage loginPage = new LoginPage();

        loginPage.open();
        loginPage.login("ios@gmail.com", "somepassword");
        Assert.assertTrue(driver.getTitle().contains("Boards"));
    }
}
