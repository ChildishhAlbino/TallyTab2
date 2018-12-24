/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.View.Architecture;

import java.net.URL;

/**
 *
 * @author conno
 */
public interface ITemplateLoader {

    ContentViewComponent getContentDisplayer(String fxml);

    ViewComponent getViewComponent(String fmxl);

    <T extends ViewComponent> T getClassFromTemplate(URL fxml, Class<T> clazz);
}
