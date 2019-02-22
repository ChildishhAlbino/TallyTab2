/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Exceptions.ViewComponentNotFoundException;
import java.util.Collection;

/**
 *
 * @author conno
 */
public interface IViewComponentParent {

    Collection getChildren();

    <T> Collection getChildren(Class<T> classFilter) throws ViewComponentNotFoundException;

    void remove(ViewComponent child);
}
