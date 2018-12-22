/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Commands;

/**
 *
 * @author conno
 */
public abstract class Command<U extends ICommandHandler> implements ICommand {

    protected String errorCode;

    @Override
    public String getErrorCode() {
        return errorCode;
    }
    
}
