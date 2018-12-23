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
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Class that defines the current limit, balance, and available menu items of a
 * function.
 *
 * @author conno
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@XmlSeeAlso({Menu.class})
public class FunctionTab extends Content {

    private Double limit;
    private Double currentBalance;
    private Menu menu;
    private Double percent;

    public FunctionTab() {

    }

    public FunctionTab(Double limit, Double currentBalance, Menu menu, Double percent) {
        this.limit = limit;
        this.currentBalance = currentBalance;
        this.menu = menu;
        this.percent = percent;
    }

    public Double getLimit() {
        return limit;
    }

    public double getCurrentBalance() {
        calculateValues();
        return currentBalance;
    }

    public Menu getMenu() {
        return menu;
    }

    private void calculateValues() {
        currentBalance = 0d;
        for (MenuItem item : menu.getItemsArray()) {
            currentBalance += item.getPrice() * item.getCount();
        }
        percent = currentBalance / getLimit();
    }

    public boolean changeMenuItemCount(int delta, String itemName) {
        boolean response = menu.changeMenuItemCount(delta, itemName);
        calculateValues();
        return response;
    }

    @Override
    public String toString() {
        String s = "\n";
        s += "Limit: " + String.valueOf(limit) + ", Current Balance: " + getCurrentBalance() + ", Current %: " + percent;
        s += menu.toString();
        return s;
    }

}
