/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.IO.FileIO;

/**
 *
 * @author conno
 * @param <T> the data type of this settings' value.
 */
public abstract class Setting<T> {

    private T value;
    protected T defaultValue;

    public T getValue() {
        return value;
    }

    public void setToDefault() {
        value = defaultValue;
    }

    public boolean setValue(T newValue) {
        boolean response = false;
        if (newValue != null) {
            value = newValue;
            response = true;
        }
        return response;
    }

    public static class MasterMenuFilePathSetting extends Setting<String> {

        public MasterMenuFilePathSetting() {
            defaultValue = FileIO.getMenuDirectory() + "/MASTER.xml";
        }

    }
}
