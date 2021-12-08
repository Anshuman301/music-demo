package com.anshu.springboot.musicDemo.resolver;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import com.anshu.springboot.musicDemo.annotation.MapperDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

public class ModelMapperResolver extends RequestResponseBodyMethodProcessor {

    @Autowired
    private ModelMapper modelMapper;

    private EntityManager entityManager;

    public ModelMapperResolver(ObjectMapper objectMapper, EntityManager entityManager) {
        super(Collections.singletonList(new MappingJackson2HttpMessageConverter(objectMapper)));
        this.entityManager = entityManager;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(MapperDto.class);
    }

    @Override
    protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
        binder.validate();
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object dto = super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
        // System.out.println(dto);
        Object id = getEntityId(dto);
        if (id == null) {
            return modelMapper.map(dto, parameter.getParameterType());
        } else {
            System.out.println("Id:" + id.toString());
            Object persistedObject = entityManager.find(parameter.getParameterType(), id);
            modelMapper.map(dto, persistedObject);
            return persistedObject;
        }
    }

    @Override
    protected Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
        for (Annotation ann : parameter.getParameterAnnotations()) {
            MapperDto dtoType = AnnotationUtils.getAnnotation(ann, MapperDto.class);
            if (dtoType != null) {
                return super.readWithMessageConverters(inputMessage, parameter, dtoType.type());
            }
        }
        throw new RuntimeException();
    }

    private Object getEntityId(@NonNull Object dto) {
        for (Field field : dto.getClass().getDeclaredFields()) {
            if (field.getAnnotation(Id.class) != null) {
                try {
                    field.setAccessible(true);
                    return field.get(dto);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
