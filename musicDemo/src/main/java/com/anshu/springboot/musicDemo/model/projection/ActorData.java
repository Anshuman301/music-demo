package com.anshu.springboot.musicDemo.model.projection;

import com.anshu.springboot.musicDemo.model.entity.Actor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "actorData", types = {Actor.class})
public interface ActorData {
    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();
}
