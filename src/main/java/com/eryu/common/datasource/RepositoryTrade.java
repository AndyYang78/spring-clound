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
 * 交易库
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "tradeEntityManagerFactory",
        transactionManagerRef = "tradeTransactionManager",
        basePackages = {"com.eryu.core.repo.trade"})
public class RepositoryTrade {

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private DataSource tradeDataSource;

    @Bean
    public EntityManager tradeEntityManager(EntityManagerFactoryBuilder builder) {
        return tradeEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean tradeEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(tradeDataSource)
                .properties(jpaProperties.getHibernateProperties(tradeDataSource))
                .packages("com.eryu.core.entity.po.trade")
                .persistenceUnit("tradePersistenceUnit")
                .build();
    }

    @Bean
    PlatformTransactionManager tradeTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(tradeEntityManagerFactory(builder).getObject());
    }
}
