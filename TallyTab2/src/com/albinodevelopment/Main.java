/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment;

import com.albinodevelopment.IO.FileIO;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.View.Architecture.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author conno
 */
public class Main extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConnorLogger.log("Program started.", ConnorLogger.Priority.extreme);
        if (!FileIO.fileStructureExists()) {
            ConnorLogger.log("Program file structure didn't exist. Attempting to create new structure.", ConnorLogger.Priority.extreme);
            if (FileIO.createFileStructure() == false) {
                System.exit(0);
            } else {
                ConnorLogger.log("Program file structure created successfully!", ConnorLogger.Priority.extreme);
            }
        }
        View view = View.getInstance();
        view.start(primaryStage);
    }

}
