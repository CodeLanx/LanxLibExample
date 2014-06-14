/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codelanx.lanxlibexample;

import com.codelanx.codelanxlib.config.ConfigurationLoader;
import com.codelanx.codelanxlib.listener.ListenerManager;
import com.codelanx.lanxlibexample.config.ConfigValue;
import com.codelanx.lanxlibexample.listener.ExampleListener;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class description for {@link ExamplePlugin}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class ExamplePlugin extends JavaPlugin {

    private ConfigurationLoader config;
    private ListenerManager listener;

    @Override
    public void onEnable() {
        this.config = new ConfigurationLoader(this, ConfigValue.class);
        
        this.listener = new ListenerManager(this);
        this.listener.registerListener(new ExampleListener(this));
    }

    @Override
    public void onDisable() {
        this.listener.cleanup();
        try {
            this.config.saveConfig();
        } catch (IOException ex) {
            this.getLogger().log(Level.SEVERE, "Error saving config values!");
        }
    }

}
