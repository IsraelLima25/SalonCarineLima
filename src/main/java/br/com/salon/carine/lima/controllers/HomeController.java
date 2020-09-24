package br.com.salon.carine.lima.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		System.out.println("Requisição Atendida");
		return "home";
	}

}