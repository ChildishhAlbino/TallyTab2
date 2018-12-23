/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Home;

import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Architechture.ModelCommand;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.View.Architecture.ContentViewComponent;
import com.albinodevelopment.View.Architecture.TemplateLoaderFactory;
import com.albinodevelopment.View.Architecture.View;
import com.albinodevelopment.View.Architecture.ViewCommand;
import com.albinodevelopment.View.Architecture.Window;
import com.albinodevelopment.View.Function.FunctionTemplateController;
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
        handle(new ViewCommand.OpenNewFunctionWindowCommand());
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

    public void newTab(Function function) {

        ContentViewComponent cvc = TemplateLoaderFactory.getLoader().getClassFromTemplate("../Function/FunctionTemplateFXML.fxml", FunctionTemplateController.class);
        View.linkChildAndParent(this, cvc);

        Tab tab = generateTab(function.getTitle(), cvc);
        tab.setContent(cvc.generate(function));
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }

    private Tab generateTab(String title, ContentViewComponent cvc) {
        Tab tab = new Tab(title);
        tab.setOnCloseRequest((event) -> {
            remove(cvc);
            handle(new ViewCommand.PassToControllerCommand(new ControllerCommand.PassToModelCommand(new ModelCommand.RemoveFunctionCommand(title))));
        });

        return tab;
    }

}
