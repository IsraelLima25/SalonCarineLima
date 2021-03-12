package br.com.salon.carine.lima.functional.login;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private void acessarPaginaLogin() {
		
		driver.get("http://localhost:8080/SalonCarineLima/login");
	}
	
	private void preencherFormLoginCorreto() {
		
		WebElement userNameInput = driver.findElement(By.name("username"));
		WebElement passwordInput = driver.findElement(By.name("password"));
		
		userNameInput.sendKeys("israelslf22@gmail.com");
		passwordInput.sendKeys("32931275");
	}
	
	private void clicarBotaoLogin() {
		
		WebElement btnLogin = driver.findElement(By.id("btn-login"));
		btnLogin.click();
	}
	
	public void fazerLoginComSucesso() {
		acessarPaginaLogin();
		preencherFormLoginCorreto();
		clicarBotaoLogin();
	}
	
	public boolean usuarioExiste() {
		return driver.getPageSource().contains("Hoje " + LocalDate.now().getDayOfMonth());
	}

}
