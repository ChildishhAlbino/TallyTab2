/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Function;

import com.albinodevelopment.View.Architecture.Window;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onSelectMenuButtonAction(ActionEvent event) {
    }

    @FXML
    private void onSubmitButtonAction(ActionEvent event) {
    }

    @FXML
    private void onCancelButtonAction(ActionEvent event) {
    }
    
}
