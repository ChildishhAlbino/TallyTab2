/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.MenuBuilder;

import com.albinodevelopment.Commands.ICommand.CommandResult;
import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.IO.FileIO;
import com.albinodevelopment.Model.Components.ApplicationSettings;
import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.Model.Components.MenuItem;
import com.albinodevelopment.View.Architecture.ContentViewComponent;
import com.albinodevelopment.View.Architecture.TemplateLoaderFactory;
import com.albinodevelopment.View.Architecture.ViewCommand;
import com.albinodevelopment.View.View;
import java.net.URL;
import java.util.ArrayList;
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
        String name = itemNameTF.getText();
        String price = itemPriceTF.getText();
        CommandResult result = handle(new ViewCommand.PassToControllerCommand(new ControllerCommand.ValidateMenuItemCommand(name, price)));
        if (result.equals(CommandResult.success)) {
            itemNameTF.clear();
            itemPriceTF.clear();
        }
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
    }

    @FXML
    private void openButtonAction(ActionEvent event) {
        String filePath = FileIO.openFileExplorer(FileIO.getMenuDirectory());
        if (filePath != null) {
            handle(new ViewCommand.PassToControllerCommand(new ControllerCommand.ValidateLoadedMenuForBuilderCommand(filePath)));
        }
    }

    @Override
    public Parent generate(Menu content) {
        update(content);

        return getFromTemplate();
    }

    private void generateItemGUI(MenuItem item, VBox vbox) {
        URL template = MenuBuilderItemTemplateController.class.getResource("MenuBuilderItemTemplate.fxml");
        ContentViewComponent cvc = TemplateLoaderFactory.getLoader().getClassFromTemplate(template, MenuBuilderItemTemplateController.class);
        View.linkParentAndChild(this, cvc);

        vbox.getChildren().add(cvc.generate(item));
    }

    @Override
    public void update(Menu content) {
        menuTitle.setText(content.getTitle().toUpperCase());
        setContent(content);
        clearVBox(scrollVboxLive);
        clearVBox(scrollVboxMaster);
        generateMenuGUI(content, scrollVboxLive);
        generateMenuGUI(ApplicationSettings.getMasterFile(), scrollVboxMaster);
    }

    private void generateMenuGUI(Menu menu, VBox vbox) {
        if (menu != null) {
            for (MenuItem item : menu.getItemsArray()) {
                generateItemGUI(item, vbox);
            }
        }
    }

    private void clearVBox(VBox vbox) {
        ArrayList<MenuBuilderItemTemplateController> cvcs = getChildren(MenuBuilderItemTemplateController.class);
        for (ContentViewComponent cvc : cvcs) {
            if (vbox.getChildren().contains(cvc.getFromTemplate())) {
                vbox.getChildren().remove(cvc.getFromTemplate());
                remove(cvc);
            }
        }
    }

}
