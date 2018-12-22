/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Controller.ControllerCommand;

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
        public commandResult execute(View commandHandler) {
            return commandHandler.getCommandHandler().handle(command);

        }

    }

    public static class OpenNewFunctionWindowCommand extends ViewCommand {

        @Override
        public commandResult execute(View commandHandler) {
            commandHandler.openNewFunctionWindow();
            return commandResult.success;
        }

    }

}
