package com.example.Multiple.DB.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
    basePackages = "com.example.Multiple.DB.primary.repository",
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {
	@Value("${spring.datasource.primary.url}")
	private String url;
	@Value("${spring.datasource.primary.driver-class-name}")
	private String driverClassName;
	@Value("${spring.datasource.primary.username}")
	private String username;
	@Value("${spring.datasource.primary.password}")
	private String password;
	@Value("${spring.datasource.primary.jpa.properties.hibernate.dialect}")
	private String dialect;
	@Value("${spring.datasource.primary.jpa.hibernate.ddl-auto}")
	private String ddlAuto;
	
    @Bean(name = "primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
    	return DataSourceBuilder.create()
                .url(url) // explicitly setting the JDBC URL
                .driverClassName(driverClassName) 
                .username(username) 
                .password(password) 
                .build();
    }
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            @Qualifier("primaryDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] { "com.example.Multiple.DB.primary.entity" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        
     // Set JPA properties including ddl-auto
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", dialect);
        jpaProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
        em.setJpaProperties(jpaProperties);
        return em;
    }
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}