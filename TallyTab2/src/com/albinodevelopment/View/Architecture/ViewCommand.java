/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Commands.Command;

/**
 *
 * @author conno
 */
public abstract class ViewCommand extends Command<View> {

    public static class PassToControllerCommand extends ViewCommand {

        @Override
        public commandResult execute(View commandHandler) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
