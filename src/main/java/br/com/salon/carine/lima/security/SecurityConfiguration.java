package br.com.salon.carine.lima.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.salon.carine.lima.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService usuarioDao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.sessionManagement().maximumSessions(1)
		.expiredUrl("/warningSessionExpired");
		
		http.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/desbloquearUsuario/**").permitAll()
		.antMatchers("/esqueciSenha/**").permitAll()
		.antMatchers("/register/**").permitAll()
		.antMatchers("/warningSessionExpired").permitAll()
		.antMatchers("/registrar/**").permitAll()
		.antMatchers("/solicitarNovaSenha").permitAll()
		.anyRequest()
		.authenticated()
		.and().formLogin().loginPage("/login").permitAll()
		.successHandler(new MyAuthenticationSuccessHandler())
		.failureUrl("/login?error=true")
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.and().rememberMe();
		
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioDao)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}
