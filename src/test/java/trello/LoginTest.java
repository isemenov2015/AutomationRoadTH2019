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
    public void createBoard() {
        loginPage.createBoard(LoginPage.TEST_BOARD_NAME);
        Assert.assertTrue(driver.getTitle().contains("TestBoard"));
    }

    @Test(dependsOnMethods = {"createBoard"})
    public void makeBoardPublic() {
        loginPage.makeBoardPublic(LoginPage.TEST_BOARD_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/johantestoff/boards");
    }

    @Test(dependsOnMethods = {"makeBoardPublic"})
    public void makeBoardPrivate() {
        loginPage.makeBoardPrivate(LoginPage.TEST_BOARD_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/johantestoff/boards");
    }

    @Test(dependsOnMethods = {"makeBoardPrivate"})
    public void deleteBoard() {
        loginPage.deleteBoard(LoginPage.TEST_BOARD_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/johantestoff/boards");
    }

    @Test(dependsOnMethods = {"deleteBoard"})
    public void logout() {
        loginPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/logged-out");
    }
}
