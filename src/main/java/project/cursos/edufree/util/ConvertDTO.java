package project.cursos.edufree.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConvertDTO {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
