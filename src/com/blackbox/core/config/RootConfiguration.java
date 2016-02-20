package com.blackbox.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 * @author jerrickpua
 *
 */
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.blackbox.core.constant.PropertiesConstant;
import com.blackbox.core.dao.RecorderDAO;
import com.blackbox.core.dao.impl.RecorderDAOImpl;

@Configuration
@PropertySource( value = "classpath:conf/app.properties" )
public class RootConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public RecorderDAO recorderDAO( @Value( "${" + PropertiesConstant.FILE_LOCATION_KEY + "}" ) String outputPath,
            @Value( "${" + PropertiesConstant.FILE_EXTENSION_KEY + "}" ) String recordExtension) {
        RecorderDAO recorderDao = new RecorderDAOImpl( outputPath, recordExtension );
        return recorderDao;
    }
}
