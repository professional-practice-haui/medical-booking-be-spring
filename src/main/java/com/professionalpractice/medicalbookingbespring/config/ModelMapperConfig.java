package com.professionalpractice.medicalbookingbespring.config;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.professionalpractice.medicalbookingbespring.dtos.UserDto;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(User.class, UserDto.class).addMapping(User::getRoles, UserDto::setRoles);

        return mapper;
    }

}
