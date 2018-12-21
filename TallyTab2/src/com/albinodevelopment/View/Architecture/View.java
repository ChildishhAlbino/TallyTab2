/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.View.Function.NewFunctionWindowController;
import java.util.Collection;
import javafx.application.Platform;
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
        Window window = newWindow("../Home/MainWindowFXML.fxml", Window.class, stage);
        window.getStage().setOnCloseRequest((event) -> {
            Platform.runLater(() -> {
                System.exit(0);
            });
        });

        linkChildAndParent(this, window);
    }

    public static <T extends Window> T newWindow(String fxml, Class<T> clazz, Stage stage) {
        T window = TemplateLoaderFactory.getLoader().getClassFromTemplate(fxml, clazz);
        Scene scene = new Scene(window.getFromTemplate());
        stage.setScene(scene);
        window.setStage(stage);
        window.show();

        return window;
    }

    public static void linkChildAndParent(ViewComponentParent parent, ViewComponent child) {
        parent.children.add(child);
        child.setParent(parent);
    }

    public void openNewFunctionWindow() {
        Collection<Window> newFunctionWindows = getChildren(NewFunctionWindowController.class);
        if (newFunctionWindows.isEmpty()) {
            Window window = newWindow("../Function/NewFunctionWindowFXML.fxml", NewFunctionWindowController.class, new Stage());
            children.add(window);
        } else {
            Window window = newFunctionWindows.stream().findFirst().get();
            window.show();
        }
    }

}
