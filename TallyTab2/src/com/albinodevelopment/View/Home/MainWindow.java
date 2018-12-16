/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Home;

import com.albinodevelopment.View.Architecture.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * FXML Controller class
 *
 * @author conno
 */
public class MainWindow extends Window implements Initializable {

    @FXML
    private TabPane tabPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleNewButton(ActionEvent event) {
        Tab tab = new Tab("LMAO");
        
        tabPane.getTabs().add(tab);
    }

    @FXML
    private void handleOpenButton(ActionEvent event) {
    }

    @FXML
    private void handleSaveButton(ActionEvent event) {
    }

    @FXML
    private void handleCloseButton(ActionEvent event) {
    }

    @FXML
    private void handleDrinksListButton(ActionEvent event) {
    }

    @FXML
    private void handlePreferencesButton(ActionEvent event) {
    }

}
