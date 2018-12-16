/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Model.IContent;
import javafx.scene.Parent;

/**
 *
 * @author conno
 */
public interface IContentDisplayer<U extends IContent> {

    Parent generate(U content);
}
