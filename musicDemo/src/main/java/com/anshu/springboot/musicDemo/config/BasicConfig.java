package com.anshu.springboot.musicDemo.config;
import com.anshu.springboot.musicDemo.model.entity.Actor;
import com.anshu.springboot.musicDemo.model.entity.Film;
import com.anshu.springboot.musicDemo.model.projection.ActorData;
import com.anshu.springboot.musicDemo.model.projection.FilmData;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class BasicConfig implements RepositoryRestConfigurer{

    @Bean
    public RepresentationModelProcessor<EntityModel<Actor>> personProcessor() {

    return new RepresentationModelProcessor<EntityModel<Actor>>() {

        @Override
        public EntityModel<Actor> process(EntityModel<Actor> model) {
        model.add(Link.of("http://localhost:8080/people", "added-link"));

        return model;
        }
    };
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        System.out.println("HIiiiiiiiiiii");
       config.exposeIdsFor(Actor.class, Film.class);
       config.setReturnBodyForPutAndPost(false);
       config.useHalAsDefaultJsonMediaType(false);
       config.getProjectionConfiguration().addProjection(ActorData.class, Actor.class);
       config.getProjectionConfiguration().addProjection(FilmData.class, Film.class);

       /**
        * Config for url identifier
         config.withEntityLookup().forRepository(ActorRespo.class)
        .withIdMapping(Actor :: getFirstName).withLookup(ActorRespo::findByFirstName);
        */ 

       config.setBasePath("/");
    }

}

