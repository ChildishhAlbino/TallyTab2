/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Architechture;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.Model.Components.FunctionTab;
import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.Model.Components.MenuItem;
import com.albinodevelopment.Model.Model;
import com.albinodevelopment.View.Architecture.ViewCommand;

/**
 *
 * @author conno
 */
public abstract class ModelCommand extends Command<Model> {

    public static class PassToViewCommand extends ModelCommand {

        private final ViewCommand command;

        public PassToViewCommand(ViewCommand command) {
            this.command = command;
        }

        @Override
        public CommandResult execute(Model commandHandler) {
            return commandHandler.getCommandHandler().handle(command);
        }

    }

    public static class NewFunctionCommand extends ModelCommand {

        private final String title;
        private final FunctionTab functionTab;

        public NewFunctionCommand(String title, FunctionTab functionTab) {
            this.title = title;
            this.functionTab = functionTab;
        }

        @Override
        public CommandResult execute(Model commandHandler) {
            Function function = commandHandler.newFunction(title, functionTab);
            if (function == null) {
                errorCode = "Function: " + title + " already exists.";
                return CommandResult.failure;
            } else {
                commandHandler.handle(new PassToViewCommand(new ViewCommand.GenerateFunctionGUICommand(function)));
            }
            return CommandResult.success;
        }

    }

    public static class RemoveFunctionCommand extends ModelCommand {

        private final String title;

        public RemoveFunctionCommand(String title) {
            this.title = title;
        }

        @Override
        public CommandResult execute(Model commandHandler) {
            commandHandler.remove(title);
            return CommandResult.success;
        }

    }

    public static class GetMenuInConstructionCommand extends ModelCommand {

        @Override
        public CommandResult execute(Model commandHandler) {
            Menu menu = commandHandler.getMenuBuilder().get();
            if (menu == null) {
                errorCode = "Menu was null.";
                return CommandResult.failure;
            }
            commandHandler.handle(new PassToViewCommand(new ViewCommand.UpdateMenuBuilderWindowCommand(menu)));
            return CommandResult.success;
        }

    }

    public static class LoadMenuIntoBuilderCommand extends ModelCommand {

        private final Menu menu;

        public LoadMenuIntoBuilderCommand(Menu menu) {
            this.menu = menu;
        }

        @Override
        public CommandResult execute(Model commandHandler) {
            CommandResult response = CommandResult.failure;
            if (commandHandler.getMenuBuilder().load(menu)) {
                response = CommandResult.success;
                commandHandler.handle(new GetMenuInConstructionCommand());
            }

            return response;
        }

    }

    public static class AddMenuItemCommand extends ModelCommand {

        private final MenuItem item;

        public AddMenuItemCommand(MenuItem item) {
            this.item = item;
        }

        @Override
        public CommandResult execute(Model commandHandler) {
            CommandResult response = CommandResult.failure;
            if (item != null) {
                boolean added = commandHandler.getMenuBuilder().get().add(item);
                if (added) {
                    response = CommandResult.success;
                    commandHandler.handle(new PassToViewCommand(new ViewCommand.UpdateMenuBuilderWindowCommand(commandHandler.getMenuBuilder().get())));
                } else {
                    errorCode = "Couldn't add drink to menu.";
                }
            } else {
                errorCode = "Item was null.";
            }
            return response;
        }

    }

}
