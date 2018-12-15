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

    private static boolean on;

    private static Priority threshold;

    public enum Priority {
        zero,
        low,
        medium,
        high,
        extreme,
    }

    public static void log(String message, Priority priority) {
        if (priority.ordinal() >= threshold.ordinal()) {
            System.out.println(priority.toString() + ": " + message);
        }
    }

}
