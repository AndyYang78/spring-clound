package com.eryu.common.datasource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * 内容库
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "contentEntityManagerFactory",
        transactionManagerRef = "contentTransactionManager",
        basePackages = {"com.eryu.core.repo.content"})
public class RepositoryContent {

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private DataSource contentDataSource;

    @Bean
    public EntityManager contentEntityManager(EntityManagerFactoryBuilder builder) {
        return contentEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean contentEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(contentDataSource)
                .properties(jpaProperties.getHibernateProperties(contentDataSource))
                .packages("com.eryu.core.entity.po.content")
                .persistenceUnit("contentPersistenceUnit")
                .build();
    }

    @Bean
    PlatformTransactionManager contentTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(contentEntityManagerFactory(builder).getObject());
    }
}
