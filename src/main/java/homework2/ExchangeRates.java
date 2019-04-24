package homework2;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class ExchangeRates {
    WebDriver driver;

    private static String getBankById(int bankId) {
        switch (bankId) {
            case 0: return "Privatbank";
            case 1: return "Ukrsibbank";
            case 2: return "Universalbank";
            case 3: return "Oschadbank";
            case 4: return "NBU";
            default: return "Gypsies at Kyiv central railway station";
        }
    }

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void printFilmAttributes() throws InterruptedException {
        double[] buyRates = new double[5];
        double[] sellRates = new double[5];

        driver.get("https://www.privat24.ua");
        String privatRateStr = driver.findElement(By.xpath("//*[contains(@class, 'section-content rate')]")).getText();
        buyRates[0] = Double.parseDouble(privatRateStr.split("\\/")[0]);
        sellRates[0] = Double.parseDouble(privatRateStr.split("\\/")[1]);

        driver.get("https://my.ukrsibbank.com/ru/personal/operations/currency_exchange/");
        String ukrsibRate = driver.findElement(By.xpath("//*[contains(@class, 'currency__ta')]/tbody/tr/td[2]")).getText();
        buyRates[1] = Double.parseDouble(ukrsibRate);
        ukrsibRate = driver.findElement(By.xpath("//*[contains(@class, 'currency__ta')]/tbody/tr/td[3]")).getText();
        sellRates[1] = Double.parseDouble(ukrsibRate);

        driver.get("https://www.universalbank.com.ua/");
        String univRate = driver.findElement(By.xpath("//*[contains(@class, 'currency dow')]/../td[2]")).getText();
        buyRates[2] = Double.parseDouble(univRate);
        univRate = driver.findElement(By.xpath("//*[contains(@class, 'currency dow')]/../td[3]")).getText();
        sellRates[2] = Double.parseDouble(univRate);

        driver.get("https://www.oschadbank.ua/ua");
        String oschRate = driver.findElement(By.xpath("//*[contains(@class, 'buy-USD')]")).getAttribute("data-buy");
        buyRates[3] = Double.parseDouble(oschRate);
        oschRate = driver.findElement(By.xpath("//*[contains(@class, 'sell-USD')]")).getAttribute("data-sell");
        sellRates[3] = Double.parseDouble(oschRate);

        driver.get("https://www.bank.gov.ua/control/uk/curmetal/detail/currency?period=daily");
        String nbuRate = driver.findElement(By.xpath("//*[contains(text(), 'Долар')]/../td[5]")).getText();
        buyRates[4] = Double.parseDouble(nbuRate) / 100;
        sellRates[4] = Double.parseDouble(nbuRate) / 100;

        double sumBuy = 0, sumSell = 0;
        int bestBuyIndex = 0, bestSellIndex = 0, counter = 0;

        // find sums and best buy/sell rates
        for (double rate : buyRates) {
            //System.out.println(getBankById(counter) + ", buy: " + rate);
            sumBuy += rate;
            if (buyRates[bestBuyIndex] < rate) {
                bestBuyIndex = counter;
            }
            counter++;
        }
        counter = 0;
        for (double rate : sellRates) {
            //System.out.println(getBankById(counter) + ", sell: " + rate);
            sumSell += rate;
            if (sellRates[bestSellIndex] > rate) {
                bestSellIndex = counter;
            }
            counter++;
        }

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Average BUY rate: " + df.format(sumBuy / buyRates.length));
        System.out.println("Average SELL rate: " + df.format(sumSell / sellRates.length));
        System.out.println("Best BUY rate bank: " + getBankById(bestBuyIndex));
        System.out.println("Best SELL rate bank: " + getBankById(bestSellIndex));
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}