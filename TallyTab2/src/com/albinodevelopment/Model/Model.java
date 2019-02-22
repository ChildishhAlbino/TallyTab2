/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model;

import com.albinodevelopment.Model.Architechture.ModelCommand;
import com.albinodevelopment.Commands.ICommand;
import com.albinodevelopment.Commands.ICommandHandler;
import com.albinodevelopment.Exceptions.FunctionNotFoundException;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Components.Function;
import com.albinodevelopment.Model.Components.FunctionTab;
import com.albinodevelopment.Model.Components.MenuBuilder;
import com.albinodevelopment.View.Architecture.ViewCommand;
import java.util.ArrayList;

/**
 *
 * @author conno
 */
public class Model implements ICommandHandler<ModelCommand> {

    private ICommandHandler<ViewCommand> commandHandler;
    private ArrayList<Function> functions = new ArrayList<>();

    private MenuBuilder menuBuilder;

    @Override
    public ICommandHandler<ViewCommand> getCommandHandler() {
        return commandHandler;
    }

    @Override
    public ICommand.CommandResult handle(ModelCommand command) {
        ICommand.CommandResult response = ICommand.CommandResult.success;
        if (command.execute(this) == ICommand.CommandResult.failure) {
            if (command.getErrorCode() != null) {
                ConnorLogger.log(command.getClass().getSimpleName() + ": " + command.getErrorCode(), ConnorLogger.Priority.medium);
            } else {
                ConnorLogger.log(command.getClass().getSimpleName() + ": No error code could be found.", ConnorLogger.Priority.medium);
            }
            response = ICommand.CommandResult.failure;
        }

        return response;
    }

    public void setCommandHandler(ICommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public Function newFunction(String functionName, FunctionTab tab) {
        Function f = null;
        if (!contains(functionName)) {
            f = new Function(functionName, tab, new ArrayList<>());
            functions.add(f);
        }

        return f;
    }

    public boolean contains(String functionName) {
        try {
            return getByName(functionName) != null;
        } catch (FunctionNotFoundException ex) {
            ConnorLogger.log(ex.getMessage(), ConnorLogger.Priority.low);
        } finally {
            return false;
        }
    }

    public boolean remove(String functionName) {
        try {
            Function function = getByName(functionName);
            functions.remove(function);
            return true;
        } catch (FunctionNotFoundException ex) {
            ConnorLogger.log(ex.getMessage(), ConnorLogger.Priority.high);
        } finally {
            return false;
        }
    }

    public Function getByName(String functionName) throws FunctionNotFoundException {
        Function f = null;
        for (Function function : functions) {
            if (function.getTitle().equals(functionName)) {
                f = function;
                break;
            }
        }
        if (f == null) {
            throw new FunctionNotFoundException("Function: " + functionName + " cannot be found.");
        }
        return f;
    }

    public MenuBuilder getMenuBuilder() {
        if (menuBuilder == null) {
            menuBuilder = new MenuBuilder();
        }
        return menuBuilder;
    }

}
