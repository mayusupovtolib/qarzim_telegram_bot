package uz.qarzim.qarzim_uz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTamplateConfiguration {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
