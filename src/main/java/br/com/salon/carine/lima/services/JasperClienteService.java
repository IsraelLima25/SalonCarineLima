package br.com.salon.carine.lima.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class JasperClienteService {
	
	private static final String JASPER_PREFIXO = "clientes-";
	private static final String JASPER_SUFIXO = ".jasper";
	
	@Autowired
	private Connection connection;
	
	private Map<String, Object> params = new HashMap<>();
	
	public void addParams(String key, Object value) {
		this.params.put(key, value);
	}
	
	public byte[] exportarPDF(String code, HttpServletRequest request) throws SQLException, MalformedURLException {
		
		ServletContext servletContext = request.getServletContext();
		URL resource = servletContext.getResource("/WEB-INF/jasper/clientes/");
		
		final String JASPER_DIRETORIO = resource.getFile();
		
		byte [] bytes = null;
		try {
			File file = ResourceUtils.getFile(JASPER_DIRETORIO
					.concat(JASPER_PREFIXO)
					.concat(code)
					.concat(JASPER_SUFIXO));
			
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
			bytes = JasperExportManager.exportReportToPdf(print);
		} catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
		}

		return bytes;
	}
	
	
}
