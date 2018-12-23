/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Architechture;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.Model.Components.FunctionTab;
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
        public commandResult execute(Model commandHandler) {
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
        public commandResult execute(Model commandHandler) {
            Function function = commandHandler.newFunction(title, functionTab);
            if(function == null){
                errorCode = "Error creating function for some reason.";
                return commandResult.failure;
            } else {
                commandHandler.handle(new PassToViewCommand(new ViewCommand.GenerateFunctionGUICommand(function)));
            }
            return commandResult.success;
        }

    }

}
