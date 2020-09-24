package br.com.salon.carine.lima.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.models.ServicoItemCarrinho;
import br.com.salon.carine.lima.services.CarrinhoService;
import br.com.salon.carine.lima.services.ServicoService;

@Controller
@RequestMapping(value = "carrinho")
public class CarrinhoController {

	@Autowired
	private ServicoService serviceServico;

	@Autowired
	private CarrinhoService carrinhoService;

	@RequestMapping(method = RequestMethod.GET, value = "itensCarrinho")
	public ModelAndView carrinhoItens() {
		ModelAndView modelAndView = new ModelAndView("carrinho/CarrinhoItens");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "add/{id}")
	public ResponseEntity<Collection<ServicoItemCarrinho>> AddItemCarrinho(@PathVariable Integer id) {

		ServicoDTO servicoDTO = serviceServico.buscarServicoPorId(id);
		Collection<ServicoItemCarrinho> itensCarrinho = carrinhoService.addServicoCarrinho(servicoDTO);
		
		System.out.println("Quantidade total de itens do carrinho: " + carrinhoService.getQuantidadeTotalItensCarrinho());
		return ResponseEntity.ok().body(itensCarrinho);

	}

}
