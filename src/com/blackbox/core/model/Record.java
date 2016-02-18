package com.blackbox.core.model;

import java.io.File;

import org.joda.time.DateTime;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias( "record" )
public class Record {
    
    @XStreamAlias( "name" )
    @XStreamAsAttribute
    private String name;
    
    @XStreamAlias( "start_date" )
    private DateTime startDate;
    
    @XStreamAlias( "end_date" )
    private DateTime endDate;
    
    @XStreamAlias( "location" )
    private Location location;
    
    @XStreamAlias( "audio_file_location" )
    private File filePath;
    
    public Record( String name, DateTime startDate, DateTime endDate, File filePath ) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.filePath = filePath;
    }
    
    public Record( String name, DateTime startDate, DateTime endDate, Location location, File filePath ) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.filePath = filePath;
    }
    
    public DateTime getEndDate() {
        return endDate;
    }
    
    public File getFilePath() {
        return filePath;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public String getName() {
        return name;
    }
    
    public DateTime getStartDate() {
        return startDate;
    }
    
    @Override
    public String toString() {
        return "Record [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate + ", location=" + location
                + ", filePath=" + filePath + "]";
    }
}
