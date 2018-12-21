/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.IO;

import java.io.File;

/**
 *
 * @author conno
 */
public class FileIO {

    private static final String RELATIVE_FOLDER_PATH = "/TallyTab2";
    private static final String MENU_SUBFOLDER = "/Menus";
    private static final String FUNCTION_SUBFOLDER = "/Functions";
    private static final String DEFAULT_DIRECTORY = getLocalAppFolder() + RELATIVE_FOLDER_PATH;

    public static String getApplicationDirectory() {
        return DEFAULT_DIRECTORY;
    }

    public static String getMenuDirectory() {
        return DEFAULT_DIRECTORY + MENU_SUBFOLDER;
    }

    public static String getFunctionDirectory() {
        return DEFAULT_DIRECTORY + FUNCTION_SUBFOLDER;
    }

    private static String getLocalAppFolder() {
        String directory;
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            directory = System.getenv("Appdata");
        } else {
            directory = System.getProperty("user.home");
            if (os.contains("Mac")) {
                directory += "/Documents";
            }
        }
        return directory;
    }

    public static boolean fileStructureExists() {
        boolean ret = true;
        if (!new File(getApplicationDirectory()).exists()) {
            ret = false;
        }

        if (!new File(getFunctionDirectory()).exists()) {
            ret = false;
        }

        if (!new File(getMenuDirectory()).exists()) {
            ret = false;
        }
        return ret;
    }

    public static boolean createFileStructure() {
        new File(getApplicationDirectory()).mkdir();
        new File(getFunctionDirectory()).mkdir();
        new File(getMenuDirectory()).mkdir();
        
        return fileStructureExists();
    }

}
