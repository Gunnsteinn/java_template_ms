package com.template.app.commons.configuration;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Configuration
public class ModelMapperConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        final var modelMapper = new ModelMapper();

        Converter<LocalDateTime, ZonedDateTime> toZonedDateTime = new AbstractConverter<>() {
            protected ZonedDateTime convert(LocalDateTime source) {
                return source == null ? null : ZonedDateTime.ofLocal(source, ZoneId.systemDefault(), null);
            }
        };
        modelMapper.addConverter(toZonedDateTime);

        return modelMapper;
    }
}



