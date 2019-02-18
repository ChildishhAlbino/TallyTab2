/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albinodevelopment.Model.Components;

import com.albinodevelopment.IO.FileIO;
import com.albinodevelopment.IO.XML.JAXBParser;
import com.albinodevelopment.Logging.ConnorLogger;
import com.albinodevelopment.Model.Components.Setting.MasterMenuFilePathSetting;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author conno
 */
public class ApplicationSettings {

    private static class SettingsManager {

        public enum SettingType {
            masterFile,
        }

        private SettingsManager() {
            this.settings = new HashMap<>();
            init();
        }

        private HashMap<SettingType, Setting> settings;

        public Setting get(SettingType settingType) {
            return settings.get(settingType);
        }

        private void init() {
            for (SettingType s : SettingType.values()) {
                settings.put(s, getSettingFromEnum(s));
            }
        }

        private Setting getSettingFromEnum(SettingType type) {
            Setting response = null;
            switch (type) {
                case masterFile:
                    response = new MasterMenuFilePathSetting();
                    break;
                default:
                    break;
            }
            return response;
        }
    }

    private static SettingsManager instance;

    public static Menu getMasterFile() {
        Menu response = null;
        try {
            getInstance();
            String masterFilePath = String.valueOf(instance.get(SettingsManager.SettingType.masterFile).getValue());
            if (masterFilePath != null) {

                Menu menu = JAXBParser.getParser(Menu.class).read(Menu.class, masterFilePath);
                if (menu != null) {
                    response = menu;
                }
            }
        } catch (JAXBException ex) {
            ConnorLogger.log(ex.toString(), ConnorLogger.Priority.high);
        }
        return response;
    }

    private static void getInstance() {
        if (instance == null) {
            try {
                SettingsManager loaded = JAXBParser.getParser(SettingsManager.class).read(SettingsManager.class, FileIO.getApplicationDirectory() + "/settings.xml");
                if (loaded == null) {
                    instance = new SettingsManager();
                }
            } catch (JAXBException ex) {
                instance = new SettingsManager();
                ConnorLogger.log(ex.toString(), ConnorLogger.Priority.high);
            }
        }
    }

}
