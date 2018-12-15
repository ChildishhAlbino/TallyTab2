/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Logging;

/**
 *
 * @author conno
 */
public class ConnorLogger {

    public static boolean on = true;

    private static Priority threshold = Priority.extreme;

    public static enum Priority {
        zero,
        low,
        medium,
        high,
        extreme,
    }

    public static void log(String message, Priority priority) {
        if (on && priority.ordinal() >= threshold.ordinal()) {
            System.out.println(priority.toString().toUpperCase() + ": " + message);
        }
    }

    public static void setPriority(Priority priority) {
       threshold = priority;
    }

}
