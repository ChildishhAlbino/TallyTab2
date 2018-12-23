/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Controller.ControllerCommand;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.View.Function.NewFunctionWindowController;
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
            Collection<Window> col = commandHandler.getChildren(NewFunctionWindowController.class);
            col.iterator().next().close();
            commandHandler.GenerateFunctionGUI(function);

            return CommandResult.success;
        }

    }

}
