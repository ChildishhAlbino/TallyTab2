/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.Model.Architechture.Content;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
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

    private HashMap<MenuItem, Integer> tally;

    public FunctionTab() {

    }

    public FunctionTab(Double limit, Menu menu) {
        this.limit = limit;
        this.currentBalance = 0d;
        this.menu = menu;
        this.percent = 0d;
        this.tally = new HashMap<>();
        initTally();
    }

    public Double getPercent() {
        return round(percent, 2);
    }

    public FunctionTab(Double limit, Double currentBalance, Menu menu, Double percent) {
        this.limit = limit;
        this.currentBalance = currentBalance;
        this.menu = menu;
        this.percent = percent;
        this.tally = new HashMap<>();
        initTally();
    }

    public Double getLimit() {
        return round(limit, 2);
    }

    public boolean setLimit(Double limit) {
        if (limit >= getCurrentBalance()) {
            this.limit = limit;
            return true;
        }
        return false;
    }

    public double getCurrentBalance() {
        calculateValues();
        return round(currentBalance, 2);
    }

    public Menu getMenu() {
        return menu;
    }

    private void calculateValues() {
        currentBalance = 0d;
        for (MenuItem item : menu.getItemsArray()) {
            currentBalance += getItemSubtotal(item.getName());
        }
        percent = currentBalance / getLimit();
    }

    public boolean changeMenuItemCount(int delta, String itemName) {
        Double funds = availableFunds();
        if (funds >= getItemSubtotal(itemName, delta)) {
            MenuItem item = menu.getByName(itemName);
            int current = tally.get(item);
            tally.put(item, current + delta);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "\n";
        s += "Limit: " + String.valueOf(limit) + ", Current Balance: " + getCurrentBalance() + ", Current %: " + percent;
        s += menu.toString();
        return s;
    }

    private void initTally() {
        for (MenuItem item : menu.getItemsArray()) {
            tally.put(item, 0);
        }
    }

    public Double getItemSubtotal(String itemName) {
        MenuItem item = menu.getByName(itemName);
        return getItemSubtotal(itemName, tally.get(item));
    }

    private Double getItemSubtotal(String itemName, int amount) {
        MenuItem item = menu.getByName(itemName);
        Double subtotal = item.getPrice() * amount;

        return round(subtotal, 2);
    }

    public Double availableFunds() {
        calculateValues();
        return limit - currentBalance;
    }

    public int getTally(String itemName) {
        return tally.get(menu.getByName(itemName));
    }

    public static double round(double value, int places) {
        if (Double.isInfinite(value) == false) {
            if (places < 0) {
                throw new IllegalArgumentException();
            }

            BigDecimal bd = new BigDecimal(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
        return value;
    }
}
