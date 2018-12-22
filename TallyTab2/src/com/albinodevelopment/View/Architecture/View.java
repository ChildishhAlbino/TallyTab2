/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Commands.ICommand;
import com.albinodevelopment.Commands.ICommandHandler;
import com.albinodevelopment.Logging.ConnorLogger;
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
            Stage stage = new Stage();
            Window window = newWindow("../Function/NewFunctionWindowFXML.fxml", NewFunctionWindowController.class, stage);
            linkChildAndParent(this, window);
        } else {
            Window window = newFunctionWindows.stream().findFirst().get();
            window.show();
        }
    }

    @Override
    public boolean handle(ViewCommand command) {
        boolean response = true;
        if (command.execute(this) == ICommand.commandResult.failure) {
            ConnorLogger.log(command.getErrorCode(), ConnorLogger.Priority.medium);
            response = false;
        }

        return response;
    }

}
