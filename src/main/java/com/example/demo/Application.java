package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        SpringApplicationBuilder springApplicationBuilder = null;
        try {
            springApplicationBuilder = application.sources(Application.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return springApplicationBuilder;
    }
    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
