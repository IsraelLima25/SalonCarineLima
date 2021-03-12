package br.com.salon.carine.lima.functional.cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.salon.carine.lima.functional.login.LoginPage;

public class NovoClientePage {
	
	private WebDriver driver;
	
	private LoginPage loginPage;

	public NovoClientePage(WebDriver driver) {
		
		this.driver = driver;
		this.loginPage = new LoginPage(driver);
	}
	
	public ListaClientePage cadastrarNovoCliente() {
		loginPage.fazerLoginComSucesso();
		acessarPaginaNovoCliente();
		preencherFormularioCadastroClienteValido();
		
		ListaClientePage listaPage = clicarBotaoCadastrar();
		
		return listaPage;
	}
	
	private void acessarPaginaNovoCliente() {
		driver.findElement(By.linkText("Clientes")).click();
		driver.findElement(By.linkText("Cadastrar")).click();
	}
	
	private void preencherFormularioCadastroClienteValido() {
		
		WebElement nomeInput = driver.findElement(By.id("nome"));
		WebElement emailInput = driver.findElement(By.id("email"));
		WebElement telefoneInput = driver.findElement(By.id("telefone"));
		WebElement cepInput = driver.findElement(By.id("cep"));
		WebElement complementoInput = driver.findElement(By.id("complemento"));
		WebElement numeroInput = driver.findElement(By.id("numero"));
		WebElement pontoReferenciaInput = driver.findElement(By.id("ponto-referencia"));
		
		
		nomeInput.sendKeys("Lucas Santana");
		emailInput.sendKeys("lucas@gmail.com");
		telefoneInput.sendKeys("71983300585");
		cepInput.sendKeys("41290221");
		complementoInput.sendKeys("Primeiro andar");
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.attributeToBe(By.id("bairro"), "value", "Pirajá"));
		numeroInput.sendKeys("25");
		pontoReferenciaInput.sendKeys("Ao lado do mercado do juca");
	}
	
	public ListaClientePage clicarBotaoCadastrar() {
		
		WebElement element = driver.findElement(By.id("btnCadastrar"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnCadastrar"))).click();
				
		return new ListaClientePage(driver);
	}
}
