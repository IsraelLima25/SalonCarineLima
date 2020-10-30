package br.com.salon.carine.lima.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.salon.carine.lima.models.Atendimento;
import br.com.salon.carine.lima.services.DashBoardService;

@Controller
@RequestMapping(value = "dashboard")
public class DashBoardController {
	
	@Autowired
	public DashBoardService dashBoardService;

	@RequestMapping(method = RequestMethod.GET, value = "home")
	public ModelAndView homePage() {
		
		ModelAndView modelAndView = new ModelAndView("dashboard/home");
		String dateActualFormatada = dashBoardService.getDateActualFormatter();
		modelAndView.addObject("dataAtual", dateActualFormatada);
		
		List<Atendimento> atendimentos = dashBoardService.atendimentosDiaAtual();
		modelAndView.addObject("atendimentos", atendimentos);
		
		return modelAndView;
	}

}
