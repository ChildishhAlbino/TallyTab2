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

}
