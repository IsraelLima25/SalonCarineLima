package br.com.salon.carine.lima.functional.login;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSystemTest {

	private WebDriver driver;
	
	private LoginPage login;

	@Before
	public void inicializa() {

		System.setProperty("webdriver.chrome.driver", "/home/israel/Downloads/chromedriver");
		driver = new ChromeDriver();
		
		login = new LoginPage(driver);
	}

	@Test
	public void deveLogarUsuario() {

		login.fazerLoginComSucesso();
		boolean usuarioExiste = login.usuarioExiste();

		assertTrue(usuarioExiste);
	}
	
	@After
	public void encerra() {

		driver.close();
	}

}
