package com.example.demo2.conf;

import com.example.demo2.obj.AA;
import com.example.demo2.obj.BB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean //=@Component
    public AA aa() {
        return new AA("뢉");
    }

    @Bean
    public BB bb() {
        return new BB();
    }

}
