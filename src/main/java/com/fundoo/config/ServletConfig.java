package com.fundoo.config;

import java.io.IOException;



import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;





@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackages= {"com.fundoo.dao", "com.fundoo.services"})
public class ServletConfig {

   @Autowired
   private Environment env;
   
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	   System.out.println("mangER");
      
	   LocalContainerEntityManagerFactoryBean em 
        = new LocalContainerEntityManagerFactoryBean();
      em.setDataSource(getDataSource());
      em.setPackagesToScan(new String[] { "com.fundoo.models" });
 System.out.println("tset1");
      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      em.setJpaVendorAdapter(vendorAdapter);
      return em;
   }
   
	@Bean
	public DataSource getDataSource() {
		System.out.println("data");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(env.getProperty("mysql.driver"));
	    dataSource.setUrl(env.getProperty("mysql.url"));
	    System.out.println("user");
	    dataSource.setUsername(env.getProperty("mysql.user"));
	    dataSource.setPassword(env.getProperty("mysql.password"));
	    return dataSource;
	}
	@Bean
	public SessionFactory sessionFactory() {
		System.out.println("session");
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setPackagesToScan("com.fundoo.models");
		lsfb.setHibernateProperties(hibernateProperties());
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lsfb.getObject();
	}
	@Bean
	public HibernateTransactionManager hibTransMan(){
	System.out.println("tr");
		return new HibernateTransactionManager(sessionFactory());
	
	}
       private Properties hibernateProperties() {
           System.out.println("pro");
    	   Properties properties = new Properties();
           properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
           properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
           properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
           return properties;  
           
           
           
      }	
       
       @Bean
       public String getKey()
       {
    	   return "aman";
       }
       
       
       
	
}
