/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Home;

import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.Model.Architechture.ModelCommand;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.View.Architecture.ContentViewComponent;
import com.albinodevelopment.View.Architecture.TemplateLoaderFactory;
import com.albinodevelopment.View.Architecture.ViewCommand;
import com.albinodevelopment.View.Architecture.Window;
import com.albinodevelopment.View.Function.FunctionTemplateController;
import com.albinodevelopment.View.MenuBuilder.MenuBuilderTemplateController;
import com.albinodevelopment.View.View;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    
    private Tab menuBuilderTab;

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
    private void handleMenuBuilderButton(ActionEvent event) {
        Collection col = getChildren(MenuBuilderTemplateController.class);
        if (col.isEmpty()) {
            handle(new ViewCommand.PassToControllerCommand(new ControllerCommand.PassToModelCommand(new ModelCommand.GetMenuInConstructionCommand())));
        } else {
            tabPane.getSelectionModel().select(menuBuilderTab);
        }
    }
    
    @FXML
    private void handlePreferencesButton(ActionEvent event) {
    }
    
    public void openMenuBuilderTab(Menu menu) {
        URL url = MenuBuilderTemplateController.class.getResource("MenuBuilderTemplate.fxml");
        ContentViewComponent cvc = TemplateLoaderFactory.getLoader().getClassFromTemplate(url, MenuBuilderTemplateController.class);
        View.linkParentAndChild(this, cvc);
        
        Tab tab = generateMenuBuilderTab(cvc);
        tab.setContent(cvc.generate(menu));
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        menuBuilderTab = tab;
    }
    
    public void newTab(Function function) {
        URL url = FunctionTemplateController.class.getResource("FunctionTemplate.fxml");
        ContentViewComponent cvc = TemplateLoaderFactory.getLoader().getClassFromTemplate(url, FunctionTemplateController.class);
        View.linkParentAndChild(this, cvc);
        
        Tab tab = generateTab(function.getTitle(), cvc);
        tab.setContent(cvc.generate(function));
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
    }
    
    private Tab generateTab(String title, ContentViewComponent cvc) {
        Tab tab = new Tab(title);
        tab.setOnCloseRequest((event) -> {
            if (confirmClose()) {
                remove(cvc);
                handle(new ViewCommand.PassToControllerCommand(new ControllerCommand.PassToModelCommand(new ModelCommand.RemoveFunctionCommand(title))));
            } else {
                event.consume();
            }
        });
        
        return tab;
    }
    
    private Tab generateMenuBuilderTab(ContentViewComponent cvc) {
        Tab tab = new Tab("Menu Builder");
        tab.setOnCloseRequest((event) -> {
            if (confirmClose()) {
                remove(cvc);
                menuBuilderTab = null;
            } else {
                event.consume();
            }
        });
        return tab;
    }
    
    public boolean confirmClose() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("You are closing a tab.");
        alert.setContentText("You could lose important data. Please make sure you've saved everything you don't want to lose before closing.");
        
        Optional<ButtonType> result = alert.showAndWait();
        return result.get().equals(ButtonType.OK);
    }
}
