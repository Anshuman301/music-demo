package com.anshu.springboot.musicDemo.model.projection;

import org.springframework.beans.factory.annotation.Value;

public interface ActorData {
    @Value("#{target.firstName} #{target.lastName}")
    String getFullName();
}
