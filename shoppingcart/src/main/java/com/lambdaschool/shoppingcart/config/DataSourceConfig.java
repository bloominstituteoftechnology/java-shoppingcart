package com.lambdaschool.shoppingcart.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig
{
    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private Environment env;

    @Value("${spring.data.source.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource()
    {
        String dbValue = env.getProperty("local.run.db");

        if (dbValue.equalsIgnoreCase("POSTGRESQL"))
        {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            return new HikariDataSource(config);
        } else
        {
            return DataSourceBuilder.create()
                    .username("sa")
                    .password("")
                    .url("jdbc:h2:mem:testdb")
                    .driverClassName("org.h2.Driver")
                    .build();
        }

    }
}