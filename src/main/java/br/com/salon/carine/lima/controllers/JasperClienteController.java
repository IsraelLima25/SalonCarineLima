package br.com.salon.carine.lima.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.salon.carine.lima.services.JasperClienteService;

@Controller
@RequestMapping(value = "/jasperClientes")
public class JasperClienteController {

	@Autowired
	private JasperClienteService jasperService;
	
	@RequestMapping(method=RequestMethod.GET, value="/relatorios/pdf/clientesCadastrados")
	public void exibirRelatorioClientesCadastrados(@RequestParam String code,
			@RequestParam String acao, HttpServletResponse response,
			HttpServletRequest request) throws IOException, SQLException {
		
		byte[] bytes = jasperService.exportarPDF(code, request);
		response.setContentType(MediaType.APPLICATION_PDF_VALUE);
		
		if(acao.equals("o")) {
			response.setHeader("content-disposition", "inline; filename=relatorio-"+ code + ".pdf");
		}else {
			response.setHeader("content-disposition", "attachment; filename=relatorio-"+ code + ".pdf");
		}
		
		response.getOutputStream().write(bytes);
	}
}
