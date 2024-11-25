package com.example.NIA;

import org.jline.reader.LineReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfig {
    @Bean
    ExHandler exceptionHandler(){
        return new ExHandler();
    }
    @Bean
    public ShellReader shellReader(@Lazy LineReader lineReader){
        return new ShellReader(lineReader);
    }
}

