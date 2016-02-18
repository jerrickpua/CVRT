package com.blackbox.core.dao;

import java.util.List;

import com.blackbox.core.exception.CVRTException;
import com.blackbox.core.model.Record;
import com.blackbox.core.rest.uploader.model.RecordModel;

/**
 * @author jerrickpua
 *         
 */
public interface RecorderDAO {
    
    public Record get( String name ) throws CVRTException;
    
    public void save( RecordModel recordUploadModel ) throws CVRTException;
    
    public void delete( String name ) throws CVRTException;
    
    public List<Record> list() throws CVRTException;
}
