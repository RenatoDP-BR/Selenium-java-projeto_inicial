package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

public class HelloWorldSelenium {

    @Test
    public void hello() {

        System.setProperty("webdriver.chrome.driver", "C:/Users/renat/Documents/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver browser = new ChromeDriver(chromeOptions);
        try {
            // Navigate to Url
            browser.get("http://www.localhost:8080/leiloes");
            //Procura o botão logar e clica nele

        } finally {
            browser.quit();
        }
    }
}
