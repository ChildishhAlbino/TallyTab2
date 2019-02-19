/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.IO.FileIO;
import com.albinodevelopment.IO.XML.JAXBParser;
import com.albinodevelopment.Logging.ConnorLogger;
import java.io.File;
import javax.xml.bind.JAXBException;

/**
 *
 * @author conno
 */
public class MenuBuilder {

    private Menu menu;

    public Menu get() {
        if (menu == null) {
            menu = new Menu("New Menu");
        }
        return menu;
    }

    public boolean save() {
        boolean response = true;
        try {
            if (menu.getFile() == null) {
                // new file
                File file = new File(FileIO.getMenuDirectory(menu.getTitle() + ".xml"));
                menu.setFile(file);
                JAXBParser.getParser(Menu.class).write(file.getAbsolutePath(), menu);
            } else {
                JAXBParser.getParser(Menu.class).write(menu.getFile(), menu);
            }
        } catch (JAXBException ex) {
            response = false;
            ConnorLogger.log("Exception thrown while saving file: " + menu.getTitle(), ConnorLogger.Priority.high);
        }

        return response;
    }

    public boolean load(String filePath) {
        boolean response = false;
        if (filePath != null) {
            try {
                Menu loaded = JAXBParser.getParser(Menu.class).read(Menu.class, filePath);
                if (loaded != null) {
                    menu = loaded;
                    response = true;
                }
            } catch (JAXBException ex) {
                ConnorLogger.log(ex.getMessage(), ConnorLogger.Priority.medium);
            }
        }
        return response;
    }

    public boolean load(Menu menu) {
        boolean response = false;
        if (menu != null) {
            this.menu = menu;
            response = true;
        }

        return response;
    }

    public void reset() {
        menu = null;
    }
}
