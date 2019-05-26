package trello;

import core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import trello.pages.TrelloBoardPage;

public class TrelloTestBundle extends BrowserFactory {
    private TrelloBoardPage loginPage = new TrelloBoardPage();

    @Test
    public void login() {

        loginPage.open();
        loginPage.login("sumigoxuci@mailsource.info", "werter321");
        Assert.assertTrue(driver.getTitle().contains("|"));
    }

    @Test(dependsOnMethods = {"login"})
    public void createBoard() {
        loginPage.createBoard(TrelloBoardPage.TEST_BOARD_NAME);
        Assert.assertTrue(driver.getTitle().contains("TestBoard"));
    }

    @Test(dependsOnMethods = {"createBoard"})
    public void makeBoardPublic() {
        loginPage.makeBoardPublic(TrelloBoardPage.TEST_BOARD_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/johantestoff/boards");
    }

    @Test(dependsOnMethods = {"makeBoardPublic"})
    public void makeBoardPrivate() {
        loginPage.makeBoardPrivate(TrelloBoardPage.TEST_BOARD_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/johantestoff/boards");
    }

    @Test(dependsOnMethods = {"makeBoardPrivate"})
    public void makeBoardFavorite() {
        loginPage.makeBoardFavorite(TrelloBoardPage.TEST_BOARD_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/johantestoff/boards");
    }

    @Test(dependsOnMethods = {"makeBoardFavorite"})
    public void deleteBoard() {
        loginPage.deleteBoard(TrelloBoardPage.TEST_BOARD_NAME);
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/johantestoff/boards");
    }

    @Test(dependsOnMethods = {"deleteBoard"})
    public void logout() {
        loginPage.logout();
        Assert.assertEquals(driver.getCurrentUrl(), "https://trello.com/logged-out");
    }
}
