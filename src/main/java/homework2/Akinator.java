/*
QA Automation course 2019

http://ru.akinator.com/

Это джин который задавая вам вопросы отгадает какого персонажа вы задумали.

Суть в чем, вам нужно перенести данную "игру" в консоль
Вы открываете сайт http://ru.akinator.com/ и начинаете играть
Все вопросы вы выводите в консоль + варианты ответа которые предлагает Акинатор
В консоли вы вводите цифру ответа, браузер соответсвенно будет делать нажатие выбранного ответа на сайте.


Пример:
в консоль выводит

Вопрос: У вашего персонажа есть канал на YouTube?
Варианты ответа:
Да - 1
Нет - 2
Я не знаю - 3
Возможно Частично - 4
Скорее нет Не совсем - 5
 */

package homework2;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Akinator {
    private static int getConsoleInput(Scanner input, ArrayList<WebElement> optionsList) {
        int choice = -1;
        while (choice < 0 || choice > optionsList.size()) {
            System.out.println("Your choice:");
            if (input.hasNextInt()) {
                choice = input.nextInt() - 1;
                if (choice < 0 || choice > optionsList.size()) {
                    System.out.println("Impossible choice");
                    input.nextLine();
                }
            }
            else if (input.hasNext()) {
                input.nextLine();
            }
        }
        System.out.println("You chose " + optionsList.get(choice).getText());
        return choice;
    }

    private static ArrayList<WebElement> retrieveOptions(WebDriver driver, String xpathQuestion, String xpathOptions) {
        if (!xpathQuestion.equals("")) {
            System.out.println(driver.findElement(By.xpath(xpathQuestion)).getText());
        }
        ArrayList<WebElement> choiceList = (ArrayList<WebElement>) driver.findElements(By.xpath(xpathOptions));
        int counter = 0;
        for (WebElement choice : choiceList) {
            String option = choice.getText();
            System.out.println("   " + (counter + 1)+ ": " + option);
            counter++;
        }
        return choiceList;
    }

    // have to use main() method in order to properly recieve console input inside IDEA
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://ru.akinator.com/");

        driver.findElement(By.xpath("//*[contains(@class, 'btn-play')]")).click();
        boolean gameOver = false;
        while (!gameOver) {
            ArrayList<WebElement> optionsList = retrieveOptions(driver,
                    "//*[contains(@class, 'question-text')]",
                    "//*[contains(@class, 'database-selection')]/ul/li");
            int choice = getConsoleInput(input, optionsList);
            driver.findElement(By.xpath("//*[contains(@class, 'database-selection')]/ul/li[" + (choice+1) + "]")).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            if (driver.findElements(By.xpath("//*[contains(@class, 'bubble-propose-container')]")).size() > 0) {
                gameOver = true;
            }
        }
        System.out.println("Q&A session is over, Akinator answer is: ");
        System.out.println(driver.findElement(By.xpath("//*[contains(@class, 'bubble-propose bubble')]/p")).getText());
        ArrayList<WebElement> optionsList = retrieveOptions(driver,
                "",
                "//*[contains(@class, 'proposal-answers')]/a");
        int choice = getConsoleInput(input, optionsList);
        driver.findElement(By.xpath("//*[contains(@class, 'proposal-answers')]/a[" + (choice+1) + "]")).click();
        driver.close();
    }}
