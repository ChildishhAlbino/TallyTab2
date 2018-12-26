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
 */
public interface IContentViewComponent<U extends IContent> {

    Parent generate(U content);
    void update(U content);
}
