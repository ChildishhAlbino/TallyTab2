/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Controller;

import com.albinodevelopment.Commands.ICommand;
import com.albinodevelopment.Commands.ICommandHandler;
import com.albinodevelopment.IO.FileIO;
import com.albinodevelopment.IO.XML.JAXBParser;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Architechture.ModelCommand;
import com.albinodevelopment.Model.Components.Menu;
import com.albinodevelopment.View.Architecture.OutputViewComponent;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBException;

/**
 *
 * @author conno
 */
public class Controller implements ICommandHandler<ControllerCommand> {

    private ICommandHandler<ModelCommand> commandHandler;

    @Override
    public ICommandHandler<ModelCommand> getCommandHandler() {
        return commandHandler;
    }

    @Override
    public ICommand.commandResult handle(ControllerCommand command) {
        ICommand.commandResult response = ICommand.commandResult.success;
        if (command.execute(this) == ICommand.commandResult.failure) {
            if (command.getErrorCode() != null) {
                ConnorLogger.log(command.getClass().getSimpleName() + ": " + command.getErrorCode(), ConnorLogger.Priority.medium);
            }
            response = ICommand.commandResult.failure;
        }

        return response;
    }

    public void setCommandHandler(ICommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    public boolean validateNewFunction(OutputViewComponent source, String title, String limit, String filePath) {
        boolean response = true;
        if (!validateTitle(title)) {
            source.output("Title cannot be blank or contain illegal characters.");
            response = false;
        }

        if (validateLimit(limit) == null) {
            source.output("Please enter a valid limit.");
            response = false;
        }

        if (validateMenu(filePath) == null) {
            source.output("Please select a valid menu file.");
            response = false;
        }

        return response;
    }

    public boolean validateTitle(String title) {
        boolean response = false;
        if (title != null) {
//            ConnorLogger.log("Regex!", ConnorLogger.Priority.low);
            Matcher matcher = Pattern.compile(FileIO.getIllegalCharacters()).matcher(title);
            if (!matcher.find()) {
                response = true;
            } else {
                ConnorLogger.log(title, ConnorLogger.Priority.medium);
            }
        }

        return response;
    }

    public Double validateLimit(String limit) {
        Double response = null;
        if (limit != null) {
            if (!limit.equals("")) {
                Double parsed = Double.valueOf(limit);
                if (!parsed.equals(Double.NaN)) {
                    response = parsed;
                }
            } else {
                response = Double.POSITIVE_INFINITY;
            }
        }
        return response;
    }

    public Menu validateMenu(String filePath) {
        Menu response = null;
        if (new File(filePath).exists()) {
            try {
                Menu menu = JAXBParser.getParser(Menu.class).read(Menu.class, filePath);
                if (!menu.equals(null)) {
                    response = menu;
                }
            } catch (JAXBException ex) {
                ex.printStackTrace();
                ConnorLogger.log(ex.getErrorCode(), ConnorLogger.Priority.high);
            }
        }

        return response;
    }
}
