/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

/**
 *
 * @author conno
 */
public class TemplateLoaderFactory {

    public static ITemplateLoader getLoader() {
        return new FXMLTemplateLoader();
    }

}
