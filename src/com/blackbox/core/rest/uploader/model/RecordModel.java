package com.blackbox.core.rest.uploader.model;

import java.time.LocalDateTime;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.multipart.MultipartFile;

import com.blackbox.core.constant.Constant;

/**
 * @author jerrickpua
 *         
 */
public class RecordModel {
    
    private static final DateTimeFormatter format = DateTimeFormat.forPattern( Constant.DATE_TIME_FORMAT_PATTERN );
    
    @org.springframework.format.annotation.DateTimeFormat( pattern = Constant.DATE_TIME_FORMAT_PATTERN )
    private DateTime startDate;
    @org.springframework.format.annotation.DateTimeFormat( pattern = Constant.DATE_TIME_FORMAT_PATTERN )
    private DateTime endDate;
    
    private Double longitude;
    
    private Double latitude;
    
    private Double altitude;
    
    private Float speed;
    
    private MultipartFile file;
    
    public DateTime getStartDate() {
        return startDate;
    }
    
    public DateTime getEndDate() {
        return endDate;
    }
    
    public MultipartFile getFile() {
        return file;
    }
    
    public String getFolderName() {
        return String.format( "%s-%s", format.print( startDate ), format.print( endDate ) );
    }
    
    public void setStartDate( DateTime startDate ) {
        this.startDate = startDate;
    }
    
    public void setEndDate( DateTime endDate ) {
        this.endDate = endDate;
    }
    
    public void setFile( MultipartFile file ) {
        this.file = file;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude( Double longitude ) {
        this.longitude = longitude;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude( Double latitude ) {
        this.latitude = latitude;
    }
    
    public Double getAltitude() {
        return altitude;
    }
    
    public Float getSpeed() {
        return speed;
    }
    
    public void setAltitude( Double altitude ) {
        this.altitude = altitude;
    }
    
    public void setSpeed( Float speed ) {
        this.speed = speed;
    }
}
