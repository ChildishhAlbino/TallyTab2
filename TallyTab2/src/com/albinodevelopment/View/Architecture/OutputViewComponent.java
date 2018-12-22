/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Logging.ConnorLogger;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author conno
 */
public abstract class OutputViewComponent extends ViewComponent implements IOutput {

    @FXML
    private Label output;

    private final ArrayList<String> backlog;

    private final Timer timer;

    private final Long MESSAGE_LENGTH = 3500L;

    public OutputViewComponent() {
        this.backlog = new ArrayList<>();
        this.timer = new Timer("outputTimer");

    }

    @Override
    public void output(String messsage) {
        if (backlog.isEmpty()) {
            output.setText(messsage);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        cycle();
                    });

                }
            }, MESSAGE_LENGTH);
        }
        backlog.add(messsage);
        ConnorLogger.log(backlog.toString(), ConnorLogger.Priority.low);
    }

    void cycle() {
        if (output.getText().equals("") == false) {
            backlog.remove(0);
            ConnorLogger.log(backlog.toString(), ConnorLogger.Priority.low);
            if (backlog.size() > 0) {
                output.setText(backlog.get(0));
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            cycle();
                        });
                    }
                }, MESSAGE_LENGTH);
            } else {
                output.setText("");
            }
        }

    }

}
