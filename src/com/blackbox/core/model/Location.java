package com.blackbox.core.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias( "location" )
public class Location {
    
    @XStreamAlias( "longitude" )
    private Double longitude;
    
    @XStreamAlias( "latitude" )
    private Double latitude;
    
    @XStreamAlias( "altitude" )
    private Double altitude;
    
    @XStreamAlias( "speed" )
    private Float speed;
    
    public Location( Double longitude, Double latitude, Double altitude, Float speed ) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.speed = speed;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public Double getAltitude() {
        return altitude;
    }
    
    public Float getSpeed() {
        return speed;
    }
    
    @Override
    public String toString() {
        return String.format( "%s, %s", longitude, latitude );
    }
    
    public boolean isEmpty() {
        return latitude == null || longitude == null;
    }
    
}
