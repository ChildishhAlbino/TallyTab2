/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.MenuBuilder;

import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.Model.Components.MenuItem;
import com.albinodevelopment.View.Architecture.ContentViewComponent;
import com.albinodevelopment.View.Architecture.ViewCommand;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author conno
 */
public class MenuBuilderItemTemplateController extends ContentViewComponent<MenuItem> implements Initializable {

    @FXML
    private Label itemNameLabel;
    @FXML
    private Label itemPriceLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void removeButtonAction(ActionEvent event) {
        String name = itemNameLabel.getText();
        String price = itemPriceLabel.getText();
        handle(new ViewCommand.PassToControllerCommand(new ControllerCommand.ValidateRemoveMenuItemCommand(name, price)));
    }

    @Override
    public Parent generate(MenuItem content) {
        update(content);

        return getFromTemplate();
    }

    @Override
    public void update(MenuItem content) {
        setContent(content);
        itemNameLabel.setText(content.getName());
        itemPriceLabel.setText(String.valueOf(content.getPrice()));
    }

}
