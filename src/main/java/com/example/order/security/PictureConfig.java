package com.example.order.security;

import com.example.order.web.Path;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Chencj
 * @description
 * @date 2020/6/30
 */
@Configuration
public class PictureConfig extends WebMvcConfigurationSupport {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/picture/**").addResourceLocations("file:"+ Path.derectory+Path.separator+"picture"+Path.separator);
        super.addResourceHandlers(registry);
    }
}
