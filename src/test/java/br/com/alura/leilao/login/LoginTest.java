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

    private LoginPage paginaDeLogin;

    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fechar();
    }



    @Test
    public void deveriaEfetuarLoginComDadosValidos() {

        paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();

        Assert.assertFalse(paginaDeLogin.isPaginaDeLogin());

        Assert.assertEquals("fulano", paginaDeLogin.getNomeUsuarioLogado());

    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {

        paginaDeLogin.preencherFormularioDeLogin("invalido", "123123");
        paginaDeLogin.efetuaLogin();

        Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());

        Assert.assertNull(paginaDeLogin.getNomeUsuarioLogado());

        Assert.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {

        paginaDeLogin.navegaParaPaginaDeLances();


        Assert.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());

        Assert.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));

    }

}
