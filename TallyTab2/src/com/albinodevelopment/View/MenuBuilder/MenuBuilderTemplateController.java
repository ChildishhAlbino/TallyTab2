/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.MenuBuilder;

import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.Model.Components.MenuItem;
import com.albinodevelopment.View.Architecture.ContentViewComponent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author conno
 */
public class MenuBuilderTemplateController extends ContentViewComponent<Menu> implements Initializable {

    @FXML
    private Label menuTitle;
    @FXML
    private TextField menuTitleTF;
    @FXML
    private TextField itemNameTF;
    @FXML
    private TextField itemPriceTF;
    @FXML
    private Button addItemButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label output;
    @FXML
    private VBox scrollVboxLive;
    @FXML
    private VBox scrollVboxMaster;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void changeDrinksListNameButtonAction(ActionEvent event) {
    }

    @FXML
    private void addItemButtonAction(ActionEvent event) {
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
    }

    @FXML
    private void openButtonAction(ActionEvent event) {
    }

    @Override
    public Parent generate(Menu content) {
        setContent(content);
        menuTitle.setText(content.getTitle());

        return getFromTemplate();
    }

    private void generateItemGUI(MenuItem item) {

    }

}
