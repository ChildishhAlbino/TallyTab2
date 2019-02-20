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
import com.albinodevelopment.Model.Components.MenuItem;
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
    public ICommand.CommandResult handle(ControllerCommand command) {
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

    public MenuItem validateMenuItem(String name, String price) {
        MenuItem response = null;
        Double dblPrice = Double.valueOf(price);
        if (dblPrice != Double.NaN) {
            Matcher matcher = Pattern.compile(FileIO.getIllegalCharacters()).matcher(name);
            if (!matcher.find()) {
                response = new MenuItem(dblPrice, name);
            }
        }
        return response;
    }
    
    public boolean validateMenuTitleChange(String newTitle){
        boolean response = true;
        Pattern pattern = Pattern.compile(FileIO.getIllegalCharacters());
        Matcher matcher = pattern.matcher(newTitle);
        if (matcher.matches()) {
            ConnorLogger.log("New title contained illegal characters.", ConnorLogger.Priority.high);
            response = false;
        }
        return response;
    }
}
