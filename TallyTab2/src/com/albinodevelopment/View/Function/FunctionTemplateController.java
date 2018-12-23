/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Function;

import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.View.Architecture.ContentViewComponent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author conno
 */
public class FunctionTemplateController extends ContentViewComponent<Function> implements Initializable {

    @FXML
    private VBox drinksVbox;
    @FXML
    private Label title;
    @FXML
    private Label limit;
    @FXML
    private Label currentVal;
    @FXML
    private Label percentage;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private TextField limitEditTF;
    @FXML
    private Label output;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void saveFunctionButtonAction(ActionEvent event) {
    }

    @FXML
    private void swapMenuButtonAction(ActionEvent event) {
    }

    @FXML
    private void editLimitButtonAction(ActionEvent event) {
    }

    @Override
    public Parent generate(Function content) {
        setContent(content);
        title.setText(content.getTitle());
        currentVal.setText(String.valueOf(content.getTab().getCurrentBalance()));
        limit.setText(String.valueOf(content.getTab().getLimit()));

        return getFromTemplate();
    }

}
