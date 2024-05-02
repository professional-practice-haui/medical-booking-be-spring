package com.professionalpractice.medicalbookingbespring.config;

import com.professionalpractice.medicalbookingbespring.dtos.UserDTO;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(User.class, UserDTO.class).addMapping(User::getRoles, UserDTO::setRoles);

        return mapper;
    }

}
