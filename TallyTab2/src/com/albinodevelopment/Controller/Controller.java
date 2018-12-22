/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Controller;

import com.albinodevelopment.Commands.ICommand;
import com.albinodevelopment.Commands.ICommandHandler;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.ModelCommand;

/**
 *
 * @author conno
 */
public class Controller implements ICommandHandler<ControllerCommand> {
    
    private ICommandHandler<ModelCommand> commandHandler;
    
    @Override
    public boolean handle(ControllerCommand command) {
        boolean response = true;
        if (command.execute(this) == ICommand.commandResult.failure) {
            ConnorLogger.log(command.getErrorCode(), ConnorLogger.Priority.medium);
            response = false;
        }

        return response;
    }

    public void setCommandHandler(ICommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }



}
