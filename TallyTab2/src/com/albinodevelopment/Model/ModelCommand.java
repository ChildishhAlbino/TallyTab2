/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model;

import com.albinodevelopment.Commands.Command;
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

}
