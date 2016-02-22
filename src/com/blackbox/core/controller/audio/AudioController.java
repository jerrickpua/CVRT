package com.blackbox.core.controller.audio;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blackbox.core.dao.RecorderDAO;
import com.blackbox.core.model.Record;
import com.blackbox.core.utils.MultipartFileSender;

@Controller
public class AudioController {
    @Autowired
    private RecorderDAO recorderDAO;
    
    @RequestMapping( value = "/audio/{name}.mp3" )
    public void getMedia( @PathVariable String name, HttpServletRequest request,
            HttpServletResponse httpServletResponse ) throws Exception {
        if ( recorderDAO == null ) {
            throw new IllegalStateException(
                    "Recorder DAO not initialized properly. Please check logs for details. Possible causes: Output path lacks write permission." );
        }
        Record record = recorderDAO.get( name );
        MultipartFileSender.fromFile( record.getFilePath() ).with( request ).with( httpServletResponse )
                .serveResource();
        // OutputStream stream = httpServletResponse.getOutputStream();
        // IOUtils.copy( new FileInputStream( record.getFilePath() ), stream );
        
    }
}
