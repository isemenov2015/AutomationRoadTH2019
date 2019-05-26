package trello.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.BrowserFactory.driver;

public class LoginPage {

    public static final String TEST_BOARD_NAME = "TestBoard";
    private By emailFid = By.cssSelector("#user");
    private By passwordFid = By.cssSelector("#password");
    private By loginBtn = By.cssSelector("#login");
    private By logoutBtn = By.cssSelector(".member-initials");
    private By logoutMenuItem = By.xpath("//*[contains(@class, 'js-logout')]");
    private By newBoardCreate = By.cssSelector(".board-tile.mod-add");
    private By boardNameInput = By.cssSelector("input.subtle-input");
    private By createBoardButton = By.xpath("//*[contains(@type, 'submit')]");
    private By locateBoard = By.xpath("//*[contains(@title, '" + TEST_BOARD_NAME + "') and contains(@dir, 'auto')]");
    private By homeIcon = By.xpath("//*[contains(@class, 'icon-house')]");
    private By moreItemsBoardMenuItem = By.xpath("//*[contains(@class, 'js-open-more')]");
    private By closeBoardMenuElement = By.xpath("//*[contains(@class, 'js-close-board')]");
    private By closeBoardConfirmButton = By.xpath("//*[contains(@class, 'js-confirm full negate')]");
    private By deleteBoardConfirmLink = By.xpath("//*[contains(@class, 'quiet js-delete')]");
    private By noBoardAvailable = By.xpath("//*[contains(@tabindex, '-1')]");
    private By permissionSwitcherMenu = By.xpath("//*[contains(@id, 'permission')]");
    private By switchBoardPublic = By.xpath("//*[contains(@name, 'public')]");
    private By switchBoardPrivate = By.xpath("//*[contains(@name, 'private')]");
    private By makeBoardPublicConfirmButton = By.xpath("//*[contains(@class, 'make-public')]");
    private By iconBoardIsPublic = By.xpath("//*[contains(@class, 'icon-sm icon-public')]");
    private By iconBoardIsPrivate = By.xpath("//*[contains(@class, 'icon-sm icon-private')]");

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

    public void createBoard(String boardName) {
        driver.findElement(newBoardCreate).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(boardNameInput));
        driver.findElement(boardNameInput).sendKeys(boardName);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(createBoardButton));
        driver.findElement(createBoardButton).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains(boardName));
        driver.findElement(homeIcon).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://trello.com/johantestoff/boards"));
    }

    public void makeBoardPublic(String boardName) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locateBoard));
        driver.findElement(locateBoard).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(permissionSwitcherMenu));
        driver.findElement(permissionSwitcherMenu).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(switchBoardPublic));
        driver.findElement(switchBoardPublic).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(makeBoardPublicConfirmButton));
        driver.findElement(makeBoardPublicConfirmButton).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(iconBoardIsPublic));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(homeIcon));
        driver.findElement(homeIcon).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://trello.com/johantestoff/boards"));
    }

    public void makeBoardPrivate(String boardName) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locateBoard));
        driver.findElement(locateBoard).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(permissionSwitcherMenu));
        driver.findElement(permissionSwitcherMenu).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(switchBoardPrivate));
        driver.findElement(switchBoardPrivate).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(iconBoardIsPrivate));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(homeIcon));
        driver.findElement(homeIcon).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://trello.com/johantestoff/boards"));
    }

    public void deleteBoard(String boardName) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(locateBoard));
        driver.findElement(locateBoard).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains(boardName));
        driver.findElement(moreItemsBoardMenuItem).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(closeBoardMenuElement));
        driver.findElement(closeBoardMenuElement).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(closeBoardConfirmButton));
        driver.findElement(closeBoardConfirmButton).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(deleteBoardConfirmLink));
        driver.findElement(deleteBoardConfirmLink).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(closeBoardConfirmButton));
        driver.findElement(closeBoardConfirmButton).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(noBoardAvailable));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(homeIcon));
        driver.findElement(homeIcon).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(newBoardCreate));
    }

    public void logout() {
        driver.findElement(logoutBtn).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(logoutMenuItem));
        driver.findElement(logoutMenuItem).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.urlToBe("https://trello.com/logged-out"));
    }
}
