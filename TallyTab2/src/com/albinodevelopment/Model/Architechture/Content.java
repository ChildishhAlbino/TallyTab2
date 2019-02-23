/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Architechture;

import com.albinodevelopment.IO.XML.XML_able;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author conno
 */
public abstract class Content implements IContent, XML_able {
    protected File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() throws FileNotFoundException{
        if(file == null){
            throw new FileNotFoundException("Content file was null.");
        }
        return file;
    }
    
}
