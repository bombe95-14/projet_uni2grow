package com.example.spring_web.backendprojet.integration;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestConfiguration( proxyBeanMethods = false )
@Testcontainers
public class PostgresContainerTest {
 //   private static PostgreSQLContainer<?> postgresContainer;

   /*  @BeforeAll
    public static void setUp() {
        postgresContainer = new PostgreSQLContainer<>("postgres:14")
                .withDatabaseName("postgres")
                .withUsername("bombe1")
                .withPassword("bombe1")
                            .withReuse(true);;
        postgresContainer.start();
    }

     static {
        postgres.start();
        System.setProperty("DB_URL", postgres.getJdbcUrl());
        System.setProperty("DB_USERNAME", postgres.getUsername());
        System.setProperty("DB_PASSWORD", postgres.getPassword());
    }

    @AfterAll
    public static void tearDown() {
        postgresContainer.stop();
    } */

    @Bean
    @ServiceConnection
    //public static
     public PostgreSQLContainer<?> postgreSQLContainer() {
        PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:14")
                .withDatabaseName("postgres")
                .withUsername("bombe1")
                .withPassword("bombe1")
                .waitingFor(Wait.forListeningPort()) // our faire patienter vos tests jusqu'à ce que le conteneur soit prêt.
                .withReuse(true); // 
       // postgresContainer.start();
        return postgresContainer;
    }
  
   
}