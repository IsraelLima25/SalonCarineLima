package br.com.salon.carine.lima.functional.cliente;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClienteSystemTest {

	private WebDriver driver;

	private NovoClientePage novoClientePage;

	@Before
	public void inicializa() {

		System.setProperty("webdriver.gecko.driver", "/home/israel/Downloads/geckodriver");
		driver = new FirefoxDriver();

		this.novoClientePage = new NovoClientePage(driver);
	}

	@After
	public void encerra() {
		driver.close();
	}

	@Test
	public void cadastrarNovoCliente() {

		ListaClientePage listaPageClientes = novoClientePage.cadastrarNovoCliente();

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("closeModalGlobal"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"btnCadastrar\"]"))));
		
		listaPageClientes.acessarListaCliente();

		WebElement nomePesquisa = driver.findElement(By.id("nome-filter"));
		nomePesquisa.sendKeys("Lucas");
		
		WebElement table = driver.findElement(By.id("cliente-table"));
		
		wait.until(ExpectedConditions.visibilityOf(table));
		
		boolean usuarioCadastrado = driver.getPageSource().contains("Lucas");
		
		assertTrue(usuarioCadastrado);
	}
}
