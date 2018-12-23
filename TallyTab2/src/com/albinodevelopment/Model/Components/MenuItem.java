/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.Model.Architechture.Content;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author conno
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Item")
public class MenuItem extends Content {

    public enum ItemState {
        open,
        locked,
    }

    public MenuItem() {
    }

    private ItemState state = ItemState.open;
    private Double price;
    private String name;

    private int count = 0;

    public int getCount() {
        return count;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    

    public MenuItem(Double price, String name) {
        this.price = price;
        this.name = name;
    }

    protected boolean changeCount(int delta) {
        count += delta;
        return true;
    }

    public void setState(ItemState state) {
        this.state = state;
    }

    public ItemState getState() {
        return state;
    }

    @Override
    public String toString() {
        String s = name + ": " + state.toString() + " @" + getPrice();
        return s;
    }

}
