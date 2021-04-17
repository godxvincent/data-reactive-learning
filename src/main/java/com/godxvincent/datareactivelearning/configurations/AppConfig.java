package com.godxvincent.datareactivelearning.configurations;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class AppConfig {

    @Bean
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager reactiveTransactionManager) {
        return TransactionalOperator.create(reactiveTransactionManager);
    }
    @Bean
    public ReactiveTransactionManager r2dbcTransactionManager(ConnectionFactory connectionFactory) {
         return new R2dbcTransactionManager(connectionFactory);
     }
}
