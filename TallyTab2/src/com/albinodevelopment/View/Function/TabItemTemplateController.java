/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Function;

import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.Model.Architechture.ModelCommand;
import com.albinodevelopment.Model.Components.MenuItem;
import com.albinodevelopment.Model.Components.TabItemContainer;
import com.albinodevelopment.View.Architecture.ContentViewComponent;
import com.albinodevelopment.View.Architecture.ViewCommand;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author conno
 */
public class TabItemTemplateController extends ContentViewComponent<TabItemContainer> implements Initializable {

    @FXML
    private Label itemName;
    @FXML
    private Label itemPrice;
    @FXML
    private Label currentAmt;
    @FXML
    private Button plusButton;
    @FXML
    private Label subtotal;

    @FXML
    private Button minusButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handlePlusButton(ActionEvent event) {
        // + 1
        handle(
                new ViewCommand.PassToControllerCommand(
                        new ControllerCommand.PassToModelCommand(
                                new ModelCommand.ChangeItemTallyCommand(
                                        getContent().getFunctionTitle(), 
                                        itemName.getText(), +1))));
    }

    @FXML
    private void handleMinusButton(ActionEvent event) {
        // - 1
         handle(
                new ViewCommand.PassToControllerCommand(
                        new ControllerCommand.PassToModelCommand(
                                new ModelCommand.ChangeItemTallyCommand(
                                        getContent().getFunctionTitle(), 
                                        itemName.getText(), -1))));
    }

    @Override
    public Parent generate(TabItemContainer content) {
        setContent(content);
        update(content);
        return getFromTemplate();
    }

    @Override
    public void update(TabItemContainer content) {
        itemName.setText(content.getItem().getName());
        itemPrice.setText(String.valueOf(content.getItem().getPrice()));
        currentAmt.setText(String.valueOf(content.getTally()));
        subtotal.setText(String.valueOf(content.getSubtotal()));
    }

}
