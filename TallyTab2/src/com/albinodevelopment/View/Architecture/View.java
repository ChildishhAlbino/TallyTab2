/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import java.util.Collection;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author conno
 */
public class View extends ViewComponentParent implements IView {

    private static View instance;

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }

        return instance;
    }

    public void start(Stage stage) {
        ViewComponent viewComponent = TemplateLoaderFactory.getLoader().getViewComponent("../Home/MainWindowFXML.fxml");
        Scene scene = new Scene(viewComponent.getFromTemplate());
        stage.setScene(scene);
        children.add(viewComponent);
        stage.show();
        
    }

}
