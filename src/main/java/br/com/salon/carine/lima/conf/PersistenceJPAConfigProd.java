package br.com.salon.carine.lima.conf;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"br.com.salon.carine.lima"})
@EnableJpaRepositories(basePackages = "br.com.salon.carine.lima.repositoriessdp")
@Profile("prod")
public class PersistenceJPAConfigProd {
	
	@Autowired
	private Environment environment;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws URISyntaxException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionalProperties());
		
		entityManagerFactoryBean.setPackagesToScan(new String[] {"br.com.salon.carine.lima.models"});

		return entityManagerFactoryBean;
	}

	public Properties additionalProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return hibernateProperties;
	}

	@Bean
	public DataSource dataSource() throws URISyntaxException {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
//		logger.error("Criando conexão");;
//		logger.error("DATABASE_URL");
//		logger.error(environment.getProperty("DATABASE_URL").toString());
//		logger.error("CLEARDB_DATABASE_URL");
//		logger.error(environment.getProperty("CLEARDB_DATABASE_URL").toString());
//		logger.error("PASSWORD_EMAIL");
//		logger.error(environment.getProperty("PASSWORD_EMAIL").toString());
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		
		//URI dbUrl = new URI("mysql://bcf5398440fcaf:bec5aafe@us-cdbr-east-03.cleardb.com/heroku_ee0664da547d01d?reconnect=true");
		
		dataSource.setUrl("mysql://us-cdbr-east-03.cleardb.com:3306/heroku_ee0664da547d01d");
		dataSource.setUsername("bcf5398440fcaf");
		dataSource.setPassword("bec5aafe");

		return dataSource;
	}
	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    	
	    mailSender.setUsername("sagsoftwareagendamentos@gmail.com");
	    mailSender.setPassword("");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		transactionManager.setJpaDialect(new HibernateJpaDialect());
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
