/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.IO.XML;

import com.albinodevelopment.Model.Architechture.Content;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author conno
 */
public class JAXBParser {

    private JAXBContext jaxbContext;

    public <T> JAXBParser(Class<T> clazz) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(clazz);
    }

    public boolean write(File file, Content toXML) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(toXML, file);
        return true;
    }

    public boolean write(String directory, Content toXML) throws JAXBException {
        return write(new File(directory), toXML);
    }

    public <T extends Content> T read(Class<T> clazz, String directory) throws JAXBException {
        return read(clazz, new File(directory));
    }

    public <T extends Content> T read(Class<T> clazz, File file) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        T t = clazz.cast(unmarshaller.unmarshal(file));
        t.setFile(file);
        return t;
    }

    public static <T> JAXBParser getParser(Class<T> clazz) throws JAXBException {
        return new JAXBParser(clazz);
    }
}
