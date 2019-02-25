/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.Model.Architechture.Content;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author conno
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlSeeAlso({FunctionTab.class, Content.class})
public class Function extends Content {

    private String title;
    private FunctionTab tab;
    private ArrayList<String> notes;

    public Function() {
    }

    public Function(String title, FunctionTab tab, ArrayList<String> notes) {
        this.title = title;
        this.tab = tab;
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public FunctionTab getTab() {
        return tab;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        String s = "\n";
        s += "Function: " + title;
        s += tab.toString();
        return s;
    }

}
