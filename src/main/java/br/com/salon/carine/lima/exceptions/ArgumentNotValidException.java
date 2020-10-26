package br.com.salon.carine.lima.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

public class ArgumentNotValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private BindingResult result;

	private HttpServletRequest request;

	public ArgumentNotValidException(BindingResult result, HttpServletRequest request) {
		this.setRequest(request);
		this.setResult(result);
	}

	public BindingResult getResult() {
		return result;
	}

	public void setResult(BindingResult result) {
		this.result = result;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}
