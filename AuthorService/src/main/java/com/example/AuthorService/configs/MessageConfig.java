package com.example.AuthorService.configs;

import com.example.AuthorService.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename(Constants.MESSAGE_SOURCE_BASENAME);
        source.setDefaultEncoding(Constants.MESSAGE_SOURCE_DEFAULT_ENCODING);
        return source;
    }
}