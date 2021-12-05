package com.anshu.springboot.musicDemo.config;

import java.util.List;

import javax.persistence.EntityManager;

import com.anshu.springboot.musicDemo.resolver.ModelMapperResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ApplicationContext applicationContext;
    private final EntityManager entityManager;

    @Autowired
    public WebConfig(ApplicationContext applicationContext, EntityManager entityManager) {
        this.applicationContext = applicationContext;
        this.entityManager = entityManager;
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().applicationContext(this.applicationContext).build();
       resolvers.add(new ModelMapperResolver(objectMapper, entityManager));
    }
}
