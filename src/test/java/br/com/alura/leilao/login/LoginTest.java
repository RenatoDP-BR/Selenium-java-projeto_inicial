package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/renat/Documents/chromedriver.exe");
    }


    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        WebDriver browser = new ChromeDriver(chromeOptions);
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {

        browser.get("http://www.localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertFalse(browser.getCurrentUrl().equals("http://www.localhost:8080/login"));

        Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {

        browser.get("http://www.localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("testes");
        browser.findElement(By.id("password")).sendKeys("12312345");
        browser.findElement(By.id("login-form")).submit();

        Assert.assertTrue(browser.getCurrentUrl().equals("http://www.localhost:8080/login?error"));

        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos"));

        Assert.assertThrows(NoSuchElementException.class, (ThrowingRunnable) () -> browser.findElement(By.id("usuario-logado")));

    }

}
