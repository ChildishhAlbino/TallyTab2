/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View;

import com.albinodevelopment.Commands.ICommand;
import com.albinodevelopment.Commands.ICommandHandler;
import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.View.Architecture.IView;
import com.albinodevelopment.View.Architecture.TemplateLoaderFactory;
import com.albinodevelopment.View.Architecture.ViewCommand;
import com.albinodevelopment.View.Architecture.ViewComponent;
import com.albinodevelopment.View.Architecture.ViewComponentParent;
import com.albinodevelopment.View.Architecture.Window;
import com.albinodevelopment.View.Function.NewFunctionWindowController;
import com.albinodevelopment.View.Home.MainWindow;
import java.net.URL;
import java.util.Collection;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author conno
 */
public class View extends ViewComponentParent implements IView {

    private ICommandHandler<ControllerCommand> commandHandler;

    @Override
    public ICommandHandler<ControllerCommand> getCommandHandler() {
        return commandHandler;
    }

    public void start(Stage stage) {
        URL url = MainWindow.class.getResource("MainWindowTemplate.fxml");
        Window window = newWindow(url, Window.class, stage);
        window.getStage().setOnCloseRequest((event) -> {
            Platform.runLater(() -> {
                System.exit(0);
            });
        });

        linkParentAndChild(this, window);
    }

    public static <T extends Window> T newWindow(URL fxml, Class<T> clazz, Stage stage) {
        T window = TemplateLoaderFactory.getLoader().getClassFromTemplate(fxml, clazz);
        Scene scene = new Scene(window.getFromTemplate());
        stage.setScene(scene);
        window.setStage(stage);
        window.show();

        return window;
    }

    public static void linkParentAndChild(ViewComponentParent parent, ViewComponent child) {
        parent.getChildren().add(child);
        child.setParent(parent);
    }

    public void openNewFunctionWindow() {
        Collection<NewFunctionWindowController> newFunctionWindows = getChildren(NewFunctionWindowController.class);
        if (newFunctionWindows.isEmpty()) {
            Stage stage = new Stage();
            URL url = NewFunctionWindowController.class.getResource("NewFunctionWindowTemplate.fxml");
            Window window = newWindow(url, NewFunctionWindowController.class, stage);
            linkParentAndChild(this, window);
        } else {
            Window window = newFunctionWindows.stream().findFirst().get();
            window.show();
        }
    }

    @Override
    public ICommand.CommandResult handle(ViewCommand command) {
        ICommand.CommandResult response = ICommand.CommandResult.success;
        if (command.execute(this) == ICommand.CommandResult.failure) {
            if (command.getErrorCode() != null) {
                ConnorLogger.log(command.getClass().getSimpleName() + ": " + command.getErrorCode(), ConnorLogger.Priority.medium);
            }
            response = ICommand.CommandResult.failure;
        }

        return response;
    }

    public void setCommandHandler(ICommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public void GenerateFunctionGUI(Function function) {
        Collection<MainWindow> col = getChildren(MainWindow.class);
        col.iterator().next().newTab(function);
    }

}
