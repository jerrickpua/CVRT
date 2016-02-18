package com.blackbox.core.rest.uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.core.dao.RecorderDAO;
import com.blackbox.core.exception.CVRTException;
import com.blackbox.core.rest.model.APIResponse;
import com.blackbox.core.rest.uploader.model.RecordModel;

/**
 * @author jerrickpua
 *         
 */
@RestController
@RequestMapping( "api" )
public class RestRecorderService {
    
    @Autowired
    private RecorderDAO recorderDao;
    
    @RequestMapping( value = "record/upload", method = RequestMethod.POST )
    public APIResponse bam( @ModelAttribute RecordModel recordUploadModel ) throws CVRTException {
        recorderDao.save( recordUploadModel );
        return new APIResponse( "OK", 200 );
    }
    
}
