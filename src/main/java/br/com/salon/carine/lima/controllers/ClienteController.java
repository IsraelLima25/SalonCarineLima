package br.com.salon.carine.lima.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "cliente")
public class ClienteController {
	
	@RequestMapping(value = "cadastrar")
	public String formCadastroCliente() {
		
		System.out.println("Cadastrar Cliente");
		
		return "cliente/formCadastro";
		
	}
}
