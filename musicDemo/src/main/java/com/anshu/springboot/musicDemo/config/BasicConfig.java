package com.anshu.springboot.musicDemo.config;
import com.anshu.springboot.musicDemo.model.Actor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class BasicConfig implements RepositoryRestConfigurer{
    @Bean
    public Converter<Actor, EntityModel<Actor>> getConverter() {
        return new Converter<Actor, EntityModel<Actor>>() {
            @Override
            public EntityModel<Actor> convert(Actor source) {
                System.out.println("olllllllllllllaaaaaaaaaaaaaaa");
                EntityModel<Actor> model = EntityModel.of(source).removeLinks();
                return model;
            }
        };
    }
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        System.out.println("HIiiiiiiiiiii");
       config.exposeIdsFor(Actor.class);
       config.useHalAsDefaultJsonMediaType(false);
       config.setReturnBodyForPutAndPost(false);
    }
    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {
        System.out.println("hiiiiiiiiiiiii....");
        conversionService.addConverter(Actor.class, EntityModel.class, getConverter());;
        RepositoryRestConfigurer.super.configureConversionService(conversionService);
    }

}

