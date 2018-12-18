/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Logging.ConnorLogger;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author conno
 */
public class FXMLTemplateLoader implements ITemplateLoader {

    private final FXMLLoader fxmlLoader = new FXMLLoader();

    @Override
    public ContentViewComponent getContentDisplayer(String fxml) {
        fxmlLoader.setLocation(getClass().getResource(fxml));
        try {
            Parent parent = fxmlLoader.load();
            ContentViewComponent contentDisplayer = fxmlLoader.getController();
            contentDisplayer.setFromTemplate(parent);
            return contentDisplayer;
        } catch (IOException ex) {
            ConnorLogger.log(ex.getLocalizedMessage(), ConnorLogger.Priority.high);
        }

        return null;
    }

    @Override
    public ViewComponent getViewComponent(String fxml) {
        fxmlLoader.setLocation(getClass().getResource(fxml));
        try {
            Parent parent = fxmlLoader.load();
            ViewComponent viewComponent = fxmlLoader.getController();
            viewComponent.setFromTemplate(parent);
            return viewComponent;
        } catch (IOException ex) {
            ConnorLogger.log(ex.getLocalizedMessage(), ConnorLogger.Priority.high);
        }

        return null;
    }

    @Override
    public <T extends ViewComponent> T getClassFromTemplate(String fxml, Class<T> clazz) {
         fxmlLoader.setLocation(getClass().getResource(fxml));
        try {
            Parent parent = fxmlLoader.load();
            T t = fxmlLoader.getController();
            if(clazz.isInstance(t)){
                t.setFromTemplate(parent);
                return t;
            } else {
                ConnorLogger.log("Document controller was not of type: " + clazz.toString(), ConnorLogger.Priority.extreme);
            }
        } catch (IOException ex) {
            ConnorLogger.log(ex.getLocalizedMessage(), ConnorLogger.Priority.high);
        }

        return null;
    }

}
