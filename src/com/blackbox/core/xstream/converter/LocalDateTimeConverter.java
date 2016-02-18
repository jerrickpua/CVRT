package com.blackbox.core.xstream.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.blackbox.core.constant.Constant;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class LocalDateTimeConverter extends AbstractSingleValueConverter {
    
    @Override
    public boolean canConvert( Class type ) {
        return LocalDateTime.class.isAssignableFrom( type );
    }
    
    @Override
    public Object fromString( String str ) {
        return DateTimeFormatter.ofPattern( Constant.DATE_TIME_FORMAT_PATTERN ).parse( str );
    }
    
    @Override
    public String toString( Object obj ) {
        LocalDateTime localDateTime = ( LocalDateTime ) obj;
        return DateTimeFormatter.ofPattern( Constant.DATE_TIME_FORMAT_PATTERN ).format( localDateTime );
    }
}
