/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.Model.Architechture.Content;

/**
 *
 * @author conno
 */
public class TabItemContainer extends Content {

    private final MenuItem item;
    private final Double subtotal;
    private final int tally;
    private final String functionTitle;

    public TabItemContainer(MenuItem item, Double subtotal, int tally, String functionTitle) {
        this.item = item;
        this.subtotal = subtotal;
        this.tally = tally;
        this.functionTitle = functionTitle;
    }

    public MenuItem getItem() {
        return item;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public int getTally() {
        return tally;
    }

    public String getFunctionTitle() {
        return functionTitle;
    }

}
