package br.com.salon.carine.lima.security;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	
	Authentication getAuthentication();

}
