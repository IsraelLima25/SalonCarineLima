package br.com.salon.carine.lima.functional.cliente;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaClientePage {
	
	private WebDriver driver;
	
	public ListaClientePage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public void acessarListaCliente() {
		
		driver.navigate().refresh();		
		driver.findElement(By.xpath("//*[@id=\"clientes-dropdown\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"listar-cliente\"]")).click();
	}
}
