/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.IO;

import com.albinodevelopment.Logging.ConnorLogger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author conno
 */
public class FileIO {

    private static final String RELATIVE_FOLDER_PATH = "/TallyTab2";
    private static final String MENU_SUBFOLDER = "/Menus";
    private static final String FUNCTION_SUBFOLDER = "/Functions";
    private static final String DEFAULT_DIRECTORY = getLocalAppFolder() + RELATIVE_FOLDER_PATH;

    private static final String ILLEGAL_CHARACTERS = "[\\Q/\\?!\\E]";

    public static String getIllegalCharacters() {
        return ILLEGAL_CHARACTERS;
    }

    public static String getApplicationDirectory() {
        return DEFAULT_DIRECTORY;
    }

    public static String getMenuDirectory() {
        return DEFAULT_DIRECTORY + MENU_SUBFOLDER;
    }

    public static String getFunctionDirectory() {
        return DEFAULT_DIRECTORY + FUNCTION_SUBFOLDER;
    }
    
    public static String getMenuDirectory(String fileName) {
        return DEFAULT_DIRECTORY + MENU_SUBFOLDER + "\\" + fileName;
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

    public static String openFileExplorer(String directory) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");
        fileChooser.setInitialDirectory(new File(directory));
        File selected = fileChooser.showOpenDialog(new Stage());
        String s = null;
        if (selected != null) {
            s = selected.getAbsolutePath();
        } else {
            ConnorLogger.log("ERROR: File selector was cancelled.", ConnorLogger.Priority.medium);
        }
        return s;
    }

    public static String openDirectoryWindow() {
        return openDirectoryWindow(getApplicationDirectory());
    }

    public static String openDirectoryWindow(String directory) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a Directory");
        directoryChooser.setInitialDirectory(new File(directory));
        File selected = directoryChooser.showDialog(new Stage());
        String s = null;
        if (selected != null) {
            s = selected.getAbsolutePath();
        } else {
            ConnorLogger.log("ERROR: Directory selector was cancelled.", ConnorLogger.Priority.medium);
        }
        return s;
    }

    public static void createErrorFile(String errorCode) {
        PrintWriter printWriter = null;
        try {
            File file = new File("error.txt");
            printWriter = new PrintWriter(file);
            printWriter.println(errorCode);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            printWriter.close();
        }
    }

}
