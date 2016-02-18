package com.blackbox.core.rest.uploader.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.blackbox.core.constant.Constant;

/**
 * @author jerrickpua
 *         
 */
public class RecordModel {
    
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern( Constant.DATE_TIME_FORMAT_PATTERN );
    
    @DateTimeFormat( pattern = Constant.DATE_TIME_FORMAT_PATTERN )
    private LocalDateTime startDate;
    @DateTimeFormat( pattern = Constant.DATE_TIME_FORMAT_PATTERN )
    private LocalDateTime endDate;
    
    private Double longitude;
    
    private Double latitude;
    
    private MultipartFile file;
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public MultipartFile getFile() {
        return file;
    }
    
    public String getFolderName() {
        return String.format( "%s-%s", format.format( startDate ), format.format( endDate ) );
    }
    
    public void setStartDate( LocalDateTime startDate ) {
        this.startDate = startDate;
    }
    
    public void setEndDate( LocalDateTime endDate ) {
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
}
