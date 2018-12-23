/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Commands.ICommand;
import com.albinodevelopment.Commands.ICommandHandler;
import javafx.scene.Parent;

/**
 *
 * @author conno
 */
public abstract class ViewComponent extends ViewComponentParent implements IViewComponent {

    private Parent fromTemplate;
    private ViewComponentParent parent;

    @Override
    public Parent getFromTemplate() {
        return fromTemplate;
    }

    @Override
    public void setFromTemplate(Parent fromTemplate) {
        this.fromTemplate = fromTemplate;
    }

    public ViewComponentParent getParent() {
        return parent;
    }

    public void setParent(ViewComponentParent parent) {
        this.parent = parent;
    }

    @Override
    public ICommand.CommandResult handle(ViewCommand command) {
        return parent.handle(command);
    }

    @Override
    public ICommandHandler getCommandHandler() {
        return parent.getCommandHandler();
    }

}
