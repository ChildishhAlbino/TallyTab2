/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Model.IContent;
import java.util.Collection;
import java.util.HashMap;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

/**
 *
 * @author conno
 */
public abstract class ViewComponent extends ViewComponentParent implements IViewComponent {

    protected final View view;
    private Parent fromTemplate;

    public ViewComponent() {
        view = View.getInstance();
        
    }

    public Parent getFromTemplate() {
        return fromTemplate;
    }

    public void setFromTemplate(Parent fromTemplate) {
        this.fromTemplate = fromTemplate;
    }

}
