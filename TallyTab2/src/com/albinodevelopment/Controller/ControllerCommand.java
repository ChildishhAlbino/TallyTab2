/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Controller;

import com.albinodevelopment.Commands.Command;
import com.albinodevelopment.Model.ModelCommand;

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

}
