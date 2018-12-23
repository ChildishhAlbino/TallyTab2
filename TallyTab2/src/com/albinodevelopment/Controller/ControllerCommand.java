/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Controller;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Model.Architechture.ModelCommand;
import com.albinodevelopment.Model.Components.FunctionTab;
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
        public commandResult execute(Controller commandHandler) {
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

        public commandResult execute(Controller commandHandler) {
            if (commandHandler.validateNewFunction(source, title, limit, filePath)) {
                FunctionTab functionTab = new FunctionTab(commandHandler.validateLimit(limit), commandHandler.validateMenu(filePath));
                commandHandler.handle(new PassToModelCommand(new ModelCommand.NewFunctionCommand(title, functionTab)));
            } else {
                errorCode = "Function could not be validated.";
                return commandResult.failure;
            }
            return commandResult.success;
        }

    }

}
