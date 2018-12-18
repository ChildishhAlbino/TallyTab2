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
public class OutputViewComponent extends ViewComponent implements IOutput {

    @FXML
    private Label output;

    private final ArrayList<String> backlog;

    private final Timer timer;

    private final Long CYCLE_TIME = 3500L;

    public OutputViewComponent() {
        this.backlog = new ArrayList<>();
        this.timer = new Timer("outputTimer");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    cycle();
                });
            }
        }, 0, CYCLE_TIME);
    }

    @Override
    public void output(String messsage) {
        backlog.add(messsage);
    }

    @Override
    public void cycle() {
        if (backlog.size() > 0) {
            ConnorLogger.log(backlog.toString(), ConnorLogger.Priority.low);
            output.setText(backlog.get(0));
            backlog.remove(0);
        } else {
            output.setText("");
        }
    }

}
