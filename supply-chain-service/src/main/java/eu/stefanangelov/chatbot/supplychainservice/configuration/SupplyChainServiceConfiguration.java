package eu.stefanangelov.chatbot.supplychainservice.configuration;

import com.coxautodev.graphql.tools.ObjectMapperConfigurer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Stefan Angelov - Delta Source Bulgaria on 29.12.18.
 */
@Configuration
public class SupplyChainServiceConfiguration {

    @Bean
    public ObjectMapperConfigurer objectMapperConfigurer(){
        return (mapper, context) -> mapper.registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

}
