package br.com.salon.carine.lima.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.salon.carine.lima.controllers.HomeController;
import br.com.salon.carine.lima.models.Cliente;
import br.com.salon.carine.lima.repositories.ClienteRepository;
import br.com.salon.carine.lima.services.ClienteService;
import br.com.salon.carine.lima.util.Message;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class, Cliente.class, 
									ClienteService.class, ClienteRepository.class, Message.class} )

public class AppWebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

//	@Override
//	 public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//	        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	    }

}
