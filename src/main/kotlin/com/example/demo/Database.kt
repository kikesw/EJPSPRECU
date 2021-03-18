package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Database {
    companion object{
        var logger = LoggerFactory.getLogger(Database.javaClass)
    }

    @Bean
    fun initDatabase(Repository: Repositorio): CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            logger.info("Preloading " + Repository.save(Usuario("Manolo", 123,"123")))
            logger.info("Preloading " + Repository.save(Usuario("Carlos", 456,"456")))
            logger.info("Preloading " + Repository.save(Usuario("Roberto", 789,"789")))

        }

    }
}