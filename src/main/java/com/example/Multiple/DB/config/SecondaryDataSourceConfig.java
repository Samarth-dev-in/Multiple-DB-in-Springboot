package com.example.Multiple.DB.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.Multiple.DB.secondary.repository",
    entityManagerFactoryRef = "secondaryEntityManagerFactory",
    transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryDataSourceConfig {
	
	@Value("${spring.datasource.secondary.url}")
	private String url;
	@Value("${spring.datasource.secondary.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.secondary.username}")
	private String username;
	@Value("${spring.datasource.secondary.password}")
	private String password;
	@Value("${spring.datasource.secondary.jpa.properties.hibernate.dialect}")
	private String dialect;
	@Value("${spring.datasource.secondary.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
    	return DataSourceBuilder.create()
    			.url(url) // explicitly setting the JDBC URL
                .driverClassName(driverClassName) 
                .username(username) 
                .password(password) 
                .build();
    }
    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            @Qualifier("secondaryDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] { "com.example.Multiple.DB.secondary.entity" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
     // Set JPA properties including ddl-auto
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", dialect);
        jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
        em.setJpaProperties(jpaProperties);
        return em;
    }
    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}