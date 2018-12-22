/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import com.albinodevelopment.Logging.ConnorLogger;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author conno
 */
public class ViewComponentParent implements IViewComponentParent {

    protected ArrayList<ViewComponent> children = new ArrayList<>();

    @Override
    public Collection getChildren() {
        return children;
    }

    @Override
    public <T> Collection getChildren(Class<T> classFilter) {
        ArrayList<T> childrenOfType = new ArrayList<>();

        for (ViewComponent child : children) {
            Class childClass = child.getClass();
            if (classFilter.isInstance(child)) {
                ConnorLogger.log("Child was of class - " + classFilter.toString(), ConnorLogger.Priority.zero);
                childrenOfType.add(classFilter.cast(child));
            }
        }
        return childrenOfType;
    }

    @Override
    public void remove(ViewComponent child) {
        children.remove(child);
        System.gc(); 
        ConnorLogger.log("VCParent removed child: " + child.toString(), ConnorLogger.Priority.low);
    }

}
