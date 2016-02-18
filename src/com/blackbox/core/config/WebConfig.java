package com.blackbox.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.blackbox.core.controller.ControllerMarker;
import com.blackbox.core.rest.RestControllerMarker;

@Configuration
@EnableWebMvc
@ComponentScan( basePackageClasses = { RestControllerMarker.class, ControllerMarker.class } )
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix( "/jsp/" );
        resolver.setSuffix( ".jsp" );
        resolver.setExposeContextBeansAsAttributes( true );
        return resolver;
    }
    
    @Bean( name = "multipartResolver" )
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding( "utf-8" );
        return commonsMultipartResolver;
    }
    
    @Override
    public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer ) {
        configurer.enable();
    }
//    @Override
//    public void configureMessageConverters( List<HttpMessageConverter<?>> converters ) {
//        converters.add( new MappingJackson2HttpMessageConverter() );
//        converters.add( new MappingJackson2XmlHttpMessageConverter() );
//    }
}
