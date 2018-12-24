/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.View.View;
import java.io.IOException;
import java.net.URL;
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
        fxmlLoader.setLocation(View.class.getResource(fxml));
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
    /**
     * Generates a ViewComponent of type T from an FXML template.
     * @param <T> The specific class you want the FXML controller to be returned as.
     * @param fxml The URL of the template. Done as URL rather than a relative String
     * as Java runtime does not like relative file paths. Pass a URL from the 
     * Controller class as long as they are in the same package.
     * @param clazz Class object of type T. Refer to T.
     * @return ViewComponent of type T that is the controller of the FXML template.
     */
    
    @Override
    public <T extends ViewComponent> T getClassFromTemplate(URL fxml, Class<T> clazz) {
        fxmlLoader.setLocation(fxml);
        try {
            Parent parent = fxmlLoader.load();
            T t = fxmlLoader.getController();
            if (clazz.isInstance(t)) {
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
