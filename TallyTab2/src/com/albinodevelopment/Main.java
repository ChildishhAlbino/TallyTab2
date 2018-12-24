/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment;

import com.albinodevelopment.Controller.Controller;
import com.albinodevelopment.IO.FileIO;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Model;
import com.albinodevelopment.View.View;
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
                FileIO.createErrorFile("File structure could not be created.");
                System.exit(0);
            } else {
                ConnorLogger.log("Program file structure created successfully!", ConnorLogger.Priority.extreme);
            }
        }
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller();

        view.setCommandHandler(controller);
        controller.setCommandHandler(model);
        model.setCommandHandler(view);

        view.start(primaryStage);
    }

}
