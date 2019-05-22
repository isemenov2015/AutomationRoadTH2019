package trello;

import core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import trello.pages.LoginPage;

public class LoginTest extends BrowserFactory {
    private LoginPage loginPage = new LoginPage();

    @Test
    public void login() {

        loginPage.open();
        loginPage.login("sumigoxuci@mailsource.info", "werter321");
        Assert.assertTrue(driver.getTitle().contains("|"));
    }

    @Test(dependsOnMethods = {"login"})
    public void logout() {
        loginPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/logged-out");
    }
}
