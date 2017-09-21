package com.eryu.common.datasource;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * 用户库
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "managerEntityManagerFactory",
        transactionManagerRef = "managerTransactionManager",
        basePackages = {"com.eryu.core.repo.manager"})
public class RepositoryManager {

    @Resource
    private JpaProperties jpaProperties;

    @Resource
    private DataSource managerDataSource;

    @Bean
    @Primary
    public EntityManager managerEntityManager(EntityManagerFactoryBuilder builder) {
        return managerEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean managerEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(managerDataSource)
                .properties(jpaProperties.getHibernateProperties(managerDataSource))
                .packages("com.eryu.core.entity.po.manager")
                .persistenceUnit("managerPersistenceUnit")
                .build();
    }

    @Bean
    @Primary
    PlatformTransactionManager managerTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(managerEntityManagerFactory(builder).getObject());
    }
}
