/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.Exceptions.ViewComponentNotFoundException;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.View.Function.FunctionTemplateController;
import com.albinodevelopment.View.Function.NewFunctionWindowController;
import com.albinodevelopment.View.Home.MainWindow;
import com.albinodevelopment.View.View;
import java.util.Collection;

/**
 *
 * @author conno
 */
public abstract class ViewCommand extends Command<View> {

    public static class PassToControllerCommand extends ViewCommand {

        private final ControllerCommand command;

        public PassToControllerCommand(ControllerCommand command) {
            this.command = command;
        }

        @Override
        public CommandResult execute(View commandHandler) {
            return commandHandler.getCommandHandler().handle(command);
        }

    }

    public static class OpenNewFunctionWindowCommand extends ViewCommand {

        @Override
        public CommandResult execute(View commandHandler) {
            commandHandler.openNewFunctionWindow();
            return CommandResult.success;
        }

    }

    public static class GenerateFunctionGUICommand extends ViewCommand {

        private final Function function;

        public GenerateFunctionGUICommand(Function function) {
            this.function = function;
        }

        @Override
        public CommandResult execute(View commandHandler) {
            try {
                Collection<NewFunctionWindowController> col = commandHandler.getChildren(NewFunctionWindowController.class);
                col.iterator().next().close();
                commandHandler.GenerateFunctionGUI(function);
                return CommandResult.success;
            } catch (ViewComponentNotFoundException ex) {
                ConnorLogger.log(ex.getMessage(), ConnorLogger.Priority.high);
                return CommandResult.failure;
            }

        }

    }

    public static class UpdateFunctionComponent extends ViewCommand {

        private final Function function;

        public UpdateFunctionComponent(Function function) {
            this.function = function;
        }

        @Override
        public CommandResult execute(View commandHandler) {
            try {
                Collection<MainWindow> col = commandHandler.getChildren(MainWindow.class);
                MainWindow mainWindow = col.iterator().next();
                Collection<FunctionTemplateController> functions = mainWindow.getChildren(FunctionTemplateController.class);
                for (FunctionTemplateController ftc : functions) {
                    if (ftc.getContent().getTitle() == function.getTitle()) {
                        ftc.generate(function);
                        break;
                    }
                }
                return CommandResult.success;
            } catch (ViewComponentNotFoundException ex) {
                ConnorLogger.log(ex.getMessage(), ConnorLogger.Priority.medium);
                return CommandResult.failure;
            }
        }
    }

    public static class UpdateMenuBuilderWindowCommand extends ViewCommand {

        private final Menu menu;

        public UpdateMenuBuilderWindowCommand(Menu menu) {
            this.menu = menu;
        }

        @Override
        public CommandResult execute(View commandHandler) {
            try {
                Collection<MainWindow> col = commandHandler.getChildren(MainWindow.class);
                MainWindow mainwindow = col.iterator().next();
                mainwindow.updateMenuBuilderComponent(menu);

                return CommandResult.success;
            } catch (ViewComponentNotFoundException ex) {
                return CommandResult.success;
            }
        }
    }

}
