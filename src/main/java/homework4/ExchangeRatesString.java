package homework4;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.DecimalFormat;

public class ExchangeRatesString {
    private WebDriver driver;

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
        //System.setProperty("webdriver.chrome.driver", "/home/ilyasemenov/Chromedriver/version74/chromedriver");
        driver = new ChromeDriver();
    }

    private static double[] privat24Rates(WebDriver driver) {
        double[] rates = new double[2];

        driver.get("https://www.privat24.ua");
        String pageStr = driver.getPageSource();
        //DIV sample: <div class="section-content rate">26.300&nbsp;/&nbsp;26.738</div>
        String classDiv = "<div class=\"section-content rate\">";
        pageStr = pageStr.replaceAll("&nbsp;", "");
        int divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        String ratesStr = pageStr.substring(divIndex, divIndex + 14);
        rates[0] = Double.parseDouble(ratesStr.split("\\/")[0].substring(0, 6).replaceAll("[^\\d.-]", ""));
        rates[1] = Double.parseDouble(ratesStr.split("\\/")[1].substring(0, 6).replaceAll("[^\\d.-]", ""));
        return rates;
    }

    private static double[] ukrsibRates(WebDriver driver) {
        double[] rates = new double[2];

        driver.get("https://my.ukrsibbank.com/ru/personal/operations/currency_exchange/");
        String pageStr = driver.getPageSource();

        // <span class="mobile-curr-name">Покупка</span>
        String classDiv = "<span class=\"mobile-curr-name\">Покупка</span>";
        int divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        String ratesStr = pageStr.substring(divIndex, divIndex + 14);
        rates[0] = Double.parseDouble(ratesStr.substring(0, 7));

        classDiv = "<span class=\"mobile-curr-name\">Продажа</span>";
        divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        ratesStr = pageStr.substring(divIndex, divIndex + 14);
        rates[1] = Double.parseDouble(ratesStr.substring(0, 7));

        return rates;
    }

    private static double[] universalRates(WebDriver driver) {
        double[] rates = new double[2];

        driver.get("https://www.universalbank.com.ua/");
        String pageStr = driver.getPageSource();

        String classDiv = "<td class=\"p-b-xs-2 p-y-1-sm\">";
        pageStr = pageStr.replaceAll("\n", "");
        int divIndex = pageStr.indexOf("rate table table-bordered light");
        pageStr = pageStr.substring(divIndex);
        divIndex = pageStr.indexOf("НБУ") + 1;
        pageStr = pageStr.substring(divIndex);
        divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        String ratesStr = pageStr.substring(divIndex, divIndex + 50).trim();
        rates[0] = Double.parseDouble(ratesStr);
        pageStr = pageStr.substring(divIndex + 5);
        divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        ratesStr = pageStr.substring(divIndex, divIndex + 50).trim();
        rates[1] = Double.parseDouble(ratesStr);
        return rates;
    }

    private static double[] oschadRates(WebDriver driver) {
        double[] rates = new double[2];

        driver.get("https://www.oschadbank.ua/ua");
        String pageStr = driver.getPageSource();

        // String oschRate = driver.findElement(By.xpath("//*[contains(@class, 'buy-USD')]")).getAttribute("data-buy");
        // <strong class="buy-USD" data-buy="26.5000">

        String classDiv = "<strong class=\"buy-USD\" data-buy=";
        int divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        String ratesStr = pageStr.substring(divIndex, divIndex + 10);
        rates[0] = Double.parseDouble(ratesStr.substring(1, 8));

        classDiv = "<strong class=\"sell-USD\" data-sell=";
        divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        ratesStr = pageStr.substring(divIndex, divIndex + 10);
        rates[1] = Double.parseDouble(ratesStr.substring(1, 8));

        return rates;
    }

    private static double[] nbuRates(WebDriver driver) {
        double[] rates = new double[2];

        driver.get("https://www.bank.gov.ua/control/uk/curmetal/detail/currency?period=daily");
        String pageStr = driver.getPageSource();

        //String nbuRate = driver.findElement(By.xpath("//*[contains(text(), 'Долар')]/../td[5]")).getText();

        pageStr = pageStr.substring(pageStr.indexOf("Долар США"));
        String classDiv = "<td class=\"cell_c";
        int divIndex = pageStr.indexOf(classDiv) + classDiv.length();
        String ratesStr = pageStr.substring(divIndex, divIndex + 20);

        rates[0] = Double.parseDouble(ratesStr.substring(2, 11));
        rates[1] = rates[0];

        return rates;
    }

    @Test
    public void printFilmAttributes() {
        double[] buyRates = new double[5];
        double[] sellRates = new double[5];

        double[] privatRates = privat24Rates(driver);
        buyRates[0] = privatRates[0];
        sellRates[0] = privatRates[1];

        double[] uksibRates = ukrsibRates(driver);
        buyRates[1] = uksibRates[0];
        sellRates[1] = uksibRates[1];

        double[] univRates = universalRates(driver);
        buyRates[2] = univRates[0];
        sellRates[2] = univRates[1];

        double[] oschRates = oschadRates(driver);
        buyRates[3] = oschRates[0];
        sellRates[3] = oschRates[1];

        double[] nbuRates = nbuRates(driver);
        buyRates[4] = nbuRates[0] / 100;
        sellRates[4] = nbuRates[1] / 100;

        double sumBuy = 0, sumSell = 0;
        int bestBuyIndex = 0, bestSellIndex = 0, counter = 0;

        // find sums and best buy/sell rates
        for (double rate : buyRates) {
            sumBuy += rate;
            if (buyRates[bestBuyIndex] < rate) {
                bestBuyIndex = counter;
            }
            counter++;
        }
        counter = 0;
        for (double rate : sellRates) {
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