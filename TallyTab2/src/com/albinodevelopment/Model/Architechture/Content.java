/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Architechture;

import com.albinodevelopment.Exceptions.ContentException;
import com.albinodevelopment.IO.XML.XML_able;
import com.albinodevelopment.Logging.ConnorLogger;
import java.io.File;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author conno
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public abstract class Content implements IContent, XML_able {
    
    protected File file;

    public void setFile(File file) {
        if(file == null){
            ConnorLogger.log("File to be set was null.", ConnorLogger.Priority.low);
        }
        this.file = file;
    }

    public File getFile() throws ContentException {
        if (this.file != null) {
            ConnorLogger.log("Returning content file.", ConnorLogger.Priority.low);
            return file;
        } else {
            throw new ContentException("Content file was null.");
        }
    }

}
