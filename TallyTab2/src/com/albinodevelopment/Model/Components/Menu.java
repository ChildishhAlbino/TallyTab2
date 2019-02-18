/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Architechture.Content;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author conno
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({MenuItem.class})
public class Menu extends Content {

    private String title;

    public String getTitle() {
        return title;
    }
    private ArrayList<MenuItem> items;

    public Menu() {
    }

    public Menu(String title, ArrayList<MenuItem> items) {
        this.title = title;
        this.items = items;
    }

    public Menu(String title) {
        this.title = title;
        this.items = new ArrayList<>();

    }

    public ArrayList<MenuItem> getItemsArray() {
        return items;
    }

    public boolean contains(String itemName) {
        boolean response = false;
        for (MenuItem item : items) {
            if (item.getName().equals(itemName)) {
                response = true;
                break;
            }
        }
        return response;
    }

    public MenuItem getByName(String itemName) {
        MenuItem response = null;
        if (contains(itemName)) {
            for (MenuItem item : items) {
                if (item.getName().equals(itemName)) {
                    response = item;
                }
            }
        }
        return response;
    }

    public boolean add(MenuItem item) {
        boolean response = false;
        if (contains(item.getName())) {
            ConnorLogger.log("Item: " + item.getName() + " already in list.", ConnorLogger.Priority.medium);
        } else {
            items.add(item);
            response = true;
        }
        Sort();
        return response;
    }

    public boolean remove(MenuItem item) {
        boolean response = false;
        if (contains(item.getName())) {
            items.remove(getByName(item.getName()));
            response = true;
        } else {
            ConnorLogger.log("Item: " + item.getName() + " wasn't in list.", ConnorLogger.Priority.medium);
        }
        Sort();
        return response;
    }

    @Override
    public String toString() {
        String s = "\n" + title + ":";
        for (MenuItem item : items) {
            s += "\n" + item.toString();
        }
        return s;
    }

    public void Sort() {
        Collections.sort(items);
    }

}
