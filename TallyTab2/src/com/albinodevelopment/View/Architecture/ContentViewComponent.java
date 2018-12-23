/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Model.Architechture.IContent;
import javafx.scene.Parent;

/**
 *
 * @author conno
 * @param <U> The type of IContent this component uses to update it's children.
 */
public abstract class ContentViewComponent<U extends IContent> extends ViewComponent implements IContentViewComponent<U> {

    private U content;

    public U getContent() {
        return content;
    }

    public void setContent(U content) {
        this.content = content;
    }
}
