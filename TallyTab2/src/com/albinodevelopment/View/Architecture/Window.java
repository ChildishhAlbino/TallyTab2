/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import javafx.stage.Stage;

/**
 *
 * @author conno
 */
public abstract class Window extends OutputViewComponent implements IWindow {

    protected String title;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void hide() {
        stage.hide();
    }

    @Override
    public void show() {
        stage.show();
        stage.requestFocus();
    }

}
