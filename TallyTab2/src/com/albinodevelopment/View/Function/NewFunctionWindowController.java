/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Function;

import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.IO.FileIO;
import com.albinodevelopment.IO.XML.JAXBParser;
import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.Model.Components.MenuItem;
import com.albinodevelopment.View.Architecture.ViewCommand;
import com.albinodevelopment.View.Architecture.Window;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.xml.bind.JAXBException;

/**
 * FXML Controller class
 *
 * @author conno
 */
public class NewFunctionWindowController extends Window implements Initializable {

    @FXML
    private TextField functionNameTF;
    @FXML
    private TextField functionLimitTF;
    @FXML
    private Label selectedMenuLabel;
    
    private String menuDirectory = "";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onSelectMenuButtonAction(ActionEvent event) {
        String filePath = FileIO.openFileExplorer(FileIO.getMenuDirectory());
        if (filePath != null) {
            selectedMenuLabel.setText(filePath);
        }
        menuDirectory = filePath;
    }

    @FXML
    private void onSubmitButtonAction(ActionEvent event) {

        String functionTitle = (functionNameTF.getText().equals("") ? null : functionNameTF.getText());
        String functionLimit = (functionLimitTF.getText().equals("") ? "" : functionLimitTF.getText());
        
        handle(new ViewCommand.PassToControllerCommand(
                new ControllerCommand.ValidateNewFunctionCommand(functionTitle, functionLimit, menuDirectory, this)));

    }

    @Override
    public void setStage(Stage stage) {
        super.setStage(stage); //To change body of generated methods, choose Tools | Templates.
        getStage().setOnCloseRequest((WindowEvent event) -> {
            getParent().remove(this);
        });
        stage.initModality(Modality.APPLICATION_MODAL);
    }

}
