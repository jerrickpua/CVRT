package com.blackbox.core.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias( "location" )
public class Location {
    
    @XStreamAlias( "longitude" )
    private Double longitude;
    
    @XStreamAlias( "latitude" )
    private Double latitude;
    
    public Location( Double longitude, Double latitude ) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    @Override
    public String toString() {
        return String.format( "%s, %s", longitude, latitude );
    }
    
    public boolean isEmpty() {
        return latitude == null || longitude == null;
    }
    
}
