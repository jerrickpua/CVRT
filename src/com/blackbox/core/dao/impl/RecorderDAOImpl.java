package com.blackbox.core.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeComparator;

import com.blackbox.core.dao.RecorderDAO;
import com.blackbox.core.exception.CVRTException;
import com.blackbox.core.model.Location;
import com.blackbox.core.model.Record;
import com.blackbox.core.rest.uploader.model.RecordModel;
import com.blackbox.core.xstream.converter.LocalDateTimeConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

//This should use a database but due to time constraint will be saving it on file system as xml and mp3 file
public class RecorderDAOImpl implements RecorderDAO {
    
    private static final String XML_FILE_NAME = "record.xml";
    
    private final String outputPath;
    
    private final String recordExtension;
    
    private XStream xStream;
    
    public RecorderDAOImpl( String outputPath, String recordExtension ) {
        if ( StringUtils.isEmpty( outputPath ) )
            throw new IllegalArgumentException( "Output path must not be empty" );
        if ( StringUtils.isEmpty( recordExtension ) )
            throw new IllegalArgumentException( "Record extension must not be empty" );
        if ( outputPath.endsWith( "/" ) && outputPath.length() > 1 )
            outputPath = outputPath.substring( 0, outputPath.lastIndexOf( "/" ) ); // remove slash if it ended with one
        if ( !new File( outputPath ).canWrite() ) {
            throw new IllegalArgumentException( String
                    .format( "Invalid output path. Lack of permission to write to output path '%s'", outputPath ) );
        }
        this.recordExtension = recordExtension;
        this.outputPath = outputPath;
        
        new File( outputPath ).mkdirs();
        this.xStream = new XStream();
        xStream.registerConverter( new LocalDateTimeConverter() );
    }
    
    @Override
    public void save( RecordModel recordUploadModel ) throws CVRTException {
        if ( recordUploadModel == null )
            throw new IllegalArgumentException( "Record model is null" );
            
        File audioDirectory = new File( String.format( "%s/%s", outputPath, recordUploadModel.getFolderName() ) );
        audioDirectory.mkdirs();
        File audioFile = new File( audioDirectory,
                String.format( "%s.%s", recordUploadModel.getFolderName(), recordExtension ) );
        try ( FileOutputStream fos = new FileOutputStream( audioFile ) ) {
            IOUtils.copy( recordUploadModel.getFile().getInputStream(), fos );
        } catch ( Exception e ) {
            System.err.println( e );
            throw new CVRTException( e );
        }
        writeRecordMetaToFileSystem( recordUploadModel, audioFile );
        
    }
    
    private void writeRecordMetaToFileSystem( RecordModel recordModel, File recordPath ) throws CVRTException {
        Record record = new Record( recordModel.getFolderName(), recordModel.getStartDate(), recordModel.getEndDate(),
                new Location( recordModel.getLongitude(), recordModel.getLatitude(), recordModel.getAltitude(),
                        recordModel.getSpeed() ),
                recordPath );
        File xmlFile = new File( recordPath.getParentFile(), XML_FILE_NAME );
        try ( OutputStream fos = new FileOutputStream( xmlFile ) ) {
            xStream.toXML( record, fos );
        } catch ( Exception e ) {
            throw new CVRTException( e );
        }
    }
    
    @Override
    public List<Record> list() throws CVRTException {
        File file = new File( outputPath );
        List<File> files = Arrays.asList( file.listFiles() );
        return files.parallelStream().map( ( File f ) -> {
            File temp = new File( f, "record.xml" );
            try ( InputStream fis = new FileInputStream( temp ) ) {
                return ( Record ) xStream.fromXML( fis );
            } catch ( Exception e ) {
                return null;
            }
        } ).filter( ( Record record ) -> record != null ).sorted( ( Record r1, Record r2 ) -> DateTimeComparator
                .getInstance().reversed().compare( r1.getEndDate(), r2.getEndDate() ) ).collect( Collectors.toList() );
    }
    
    @Override
    public Record get( String name ) throws CVRTException {
        return list().parallelStream().filter( record -> record.getName().equals( name ) ).findFirst().get();
    }
    
    @Override
    public void delete( String name ) throws CVRTException {
        Record record = get( name );
        try {
            FileUtils.deleteDirectory( record.getFilePath() );
        } catch ( IOException e ) {
            throw new CVRTException( e );
        }
    }
    
}
