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
    private ListenerManager<ExamplePlugin> listener;

    @Override
    public void onEnable() {
        this.config = new ConfigurationLoader(this, ConfigValue.class);
        
        this.getLogger().log(Level.INFO, "Loading listener example...");
        this.listener = new ListenerManager<>(this);
        this.listener.registerListener(new ExampleListener(this));
        if (this.listener.isRegistered(ExampleListener.class)) {
            this.getLogger().log(Level.INFO, "{0} is registered!", ExampleListener.class.getName());
        }
        ExampleListener lis = this.listener.getListener(ExampleListener.class);
        if (lis != null) {
            this.getLogger().log(Level.INFO, "{0} successfully retrieved from manager!", ExampleListener.class.getName());
        }
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
