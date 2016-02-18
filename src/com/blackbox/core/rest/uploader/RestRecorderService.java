package com.blackbox.core.rest.uploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blackbox.core.dao.RecorderDAO;
import com.blackbox.core.exception.CVRTException;
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
    public String bam( @ModelAttribute RecordModel recordUploadModel ) throws CVRTException {
        recorderDao.save( recordUploadModel );
        return "wew";
    }
    
}
