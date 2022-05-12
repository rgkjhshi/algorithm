package com.mk.algorithm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * @author song.shi
 * @since 2018-01-18
 */
@SpringBootApplication(scanBasePackages = {"com.mk.algorithm"}, exclude = {DataSourceAutoConfiguration.class})
@ImportResource({"classpath:spring/root.xml"})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        // 采用内置tomcat启动
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }
}
