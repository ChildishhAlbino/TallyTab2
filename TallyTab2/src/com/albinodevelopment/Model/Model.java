/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model;

import com.albinodevelopment.Commands.ICommand;
import com.albinodevelopment.Commands.ICommandHandler;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.View.Architecture.ViewCommand;

/**
 *
 * @author conno
 */
public class Model extends Content implements ICommandHandler<ModelCommand> {

    private ICommandHandler<ViewCommand> commandHandler;

    @Override
    public ICommandHandler<ViewCommand> getCommandHandler() {
        return commandHandler;
    }

    @Override
    public ICommand.commandResult handle(ModelCommand command) {
        ICommand.commandResult response = ICommand.commandResult.success;
        if (command.execute(this) == ICommand.commandResult.failure) {
            ConnorLogger.log(command.getErrorCode(), ConnorLogger.Priority.medium);
            response = ICommand.commandResult.failure;
        }

        return response;
    }

    public void setCommandHandler(ICommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

}
