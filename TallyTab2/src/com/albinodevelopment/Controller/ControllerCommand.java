/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Controller;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Model.Architechture.ModelCommand;
import com.albinodevelopment.Model.Components.FunctionTab;
import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.Model.Components.MenuItem;
import com.albinodevelopment.View.Architecture.OutputViewComponent;

/**
 *
 * @author conno
 */
public abstract class ControllerCommand extends Command<Controller> {

    public static class PassToModelCommand extends ControllerCommand {

        private final ModelCommand command;

        public PassToModelCommand(ModelCommand command) {
            this.command = command;
        }

        @Override
        public CommandResult execute(Controller commandHandler) {
            return commandHandler.getCommandHandler().handle(command);
        }

    }

    public static class ValidateNewFunctionCommand extends ControllerCommand {

        private final String title;
        private final String limit;
        private final String filePath;
        private final OutputViewComponent source;

        public ValidateNewFunctionCommand(String title, String limit, String filePath, OutputViewComponent source) {
            this.title = title;
            this.limit = limit;
            this.filePath = filePath;
            this.source = source;
        }

        @Override

        public CommandResult execute(Controller commandHandler) {
            if (commandHandler.validateNewFunction(source, title, limit, filePath)) {
                FunctionTab functionTab = new FunctionTab(commandHandler.validateLimit(limit), commandHandler.validateMenu(filePath));
                CommandResult result = commandHandler.handle(new PassToModelCommand(new ModelCommand.NewFunctionCommand(title, functionTab)));
                if (result == CommandResult.failure) {
                    source.output("Function already exists.");
                }
            } else {
                errorCode = "Function could not be validated.";
                return CommandResult.failure;
            }
            return CommandResult.success;
        }

    }

    public static class ValidateLoadedMenuForBuilderCommand extends ControllerCommand {

        private final String filePath;

        public ValidateLoadedMenuForBuilderCommand(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public CommandResult execute(Controller commandHandler) {
            Menu menu = commandHandler.validateMenu(filePath);
            if (menu != null) {
                commandHandler.handle(new PassToModelCommand(new ModelCommand.LoadMenuIntoBuilderCommand(menu)));
            }
            return CommandResult.success;
        }

    }

    public static class ValidateMenuItemCommand extends ControllerCommand {

        private final String name;
        private final String price;

        public ValidateMenuItemCommand(String name, String price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public CommandResult execute(Controller commandHandler) {
            CommandResult response = CommandResult.failure;
            MenuItem item = commandHandler.validateMenuItem(name, price);
            if (item != null) {
                response = commandHandler.handle(new PassToModelCommand(new ModelCommand.AddMenuItemCommand(item)));
            } else {
                errorCode = "Item was null.";
            }
            return response;
        }

    }

    public static class ValidateRemoveMenuItemCommand extends ControllerCommand {

        private final String name;
        private final String price;

        public ValidateRemoveMenuItemCommand(String name, String price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public CommandResult execute(Controller commandHandler) {
            CommandResult response = CommandResult.failure;
            MenuItem item = commandHandler.validateMenuItem(name, price);
            if (item != null) {
                response = commandHandler.handle(new PassToModelCommand(new ModelCommand.RemoveMenuItemCommand(item)));
            } else {
                errorCode = "Menu item was null.";
            }
            return response;
        }

    }

    public static class ValidateMenuTitleChangeCommand extends ControllerCommand {

        private final String newTitle;

        public ValidateMenuTitleChangeCommand(String newTitle) {
            this.newTitle = newTitle;
        }

        @Override
        public CommandResult execute(Controller commandHandler) {
            boolean validTitle = commandHandler.validateMenuTitleChange(newTitle);
            commandHandler.handle(new PassToModelCommand(new ModelCommand.ChangeMenuTitleCommand(newTitle)));
            return (validTitle == true ? CommandResult.success : CommandResult.failure);
        }

    }

    public static class ValidateNewLimitCommand extends ControllerCommand {

        private final String input;
        private final String functionTitle;

        public ValidateNewLimitCommand(String input, String functionTitle) {
            this.input = input;
            this.functionTitle = functionTitle;
        }

        @Override
        public CommandResult execute(Controller commandHandler) {
            Double valid = commandHandler.validateLimit(input);
            if(valid != null){
                return commandHandler.handle(new PassToModelCommand(new ModelCommand.SetLimitCommand(functionTitle, valid)));
            }
            errorCode = "Input was invalid as a limit.";
            return CommandResult.failure;
        }

    }

}
