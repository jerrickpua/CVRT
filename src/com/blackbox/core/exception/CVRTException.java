package com.blackbox.core.exception;

public class CVRTException extends Exception {
    
    public CVRTException() {
        super();
    }
    
    public CVRTException( String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace ) {
        super( message, cause, enableSuppression, writableStackTrace );
    }
    
    public CVRTException( String message, Throwable cause ) {
        super( message, cause );
    }
    
    public CVRTException( String message ) {
        super( message );
    }
    
    public CVRTException( Throwable cause ) {
        super( cause );
    }
    
}
