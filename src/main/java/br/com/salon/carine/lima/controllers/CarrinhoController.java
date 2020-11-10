package br.com.salon.carine.lima.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.converters.ConvertersServico;
import br.com.salon.carine.lima.dto.ServicoDTO;
import br.com.salon.carine.lima.dto.ServicoItemCarrinhoDTO;
import br.com.salon.carine.lima.services.CarrinhoService;
import br.com.salon.carine.lima.services.ServicoService;

@Controller
@RequestMapping(value = "carrinho")
public class CarrinhoController {

	@Autowired
	private ServicoService serviceServico;

	@Autowired
	private CarrinhoService carrinhoService;

	private Logger logger = Logger.getLogger("br.com.salon.carine.lima.Carrinho");

	@RequestMapping(method = RequestMethod.GET, value = "itensCarrinho")
	public ModelAndView carrinhoItens() {

		ModelAndView modelAndView = new ModelAndView("carrinho/CarrinhoItens");
		List<ServicoItemCarrinhoDTO> servicosCarrinho = carrinhoService.getServicosCarrinho();
		this.logger.info("Carrinho capturado");
		BigDecimal valorTotalCarrinho = carrinhoService.getValorTotalCarrinho();
		modelAndView.addObject("itens", servicosCarrinho);
		modelAndView.addObject("total", valorTotalCarrinho);
		modelAndView.addObject("activeCarrinho","active");

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "add/{id}")
	public ResponseEntity<ServicoItemCarrinhoDTO> addItemServicoCarrinho(@PathVariable Integer id) {

		ServicoDTO servicoDTO = ConvertersServico
				.deServicoParaServicoDTO(serviceServico.buscarServicoPorId(id));
		ServicoItemCarrinhoDTO itemAdicionado = carrinhoService.addServicoCarrinho(servicoDTO);

		return ResponseEntity.ok().body(itemAdicionado);
	}

	@RequestMapping(method = RequestMethod.GET, value = "remover/{id}")
	public ResponseEntity<ServicoItemCarrinhoDTO> removerItemServicoCarrinho(@PathVariable Integer id) {

		ServicoDTO servicoDTO = ConvertersServico
				.deServicoParaServicoDTO(serviceServico.buscarServicoPorId(id));
		ServicoItemCarrinhoDTO itemRemovido;
		itemRemovido = carrinhoService.removerItemCarrinho(servicoDTO);
		return ResponseEntity.ok().body(itemRemovido);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "remover/servico/{id}")
	public ResponseEntity<Void> removerServicoCarrinho(@PathVariable Integer id) {

		ServicoDTO servicoDTO = ConvertersServico
				.deServicoParaServicoDTO(serviceServico.buscarServicoPorId(id));
		boolean removido = carrinhoService.removerServicoCarrinho(servicoDTO);

		if (removido) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "totalCarrinho")
	public ResponseEntity<BigDecimal> getValorTotalCarrinho() {
		BigDecimal valorTotalCarrinho = carrinhoService.getValorTotalCarrinho();
		return ResponseEntity.ok().body(valorTotalCarrinho);
	}
}
