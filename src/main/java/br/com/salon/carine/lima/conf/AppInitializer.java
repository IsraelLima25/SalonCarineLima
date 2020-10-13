package br.com.salon.carine.lima.conf;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
	
		return new Class[] {PersistenceJPAConfig.class};
//		return null
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
	            WebMvcConfig.class
	        };
	}

	@Override
	protected String[] getServletMappings() {
		 return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		return new Filter[] { encodingFilter };
	}	

}
