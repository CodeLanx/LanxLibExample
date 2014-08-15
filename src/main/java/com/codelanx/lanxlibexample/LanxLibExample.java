/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codelanx.lanxlibexample;

import com.codelanx.codelanxlib.config.ConfigurationLoader;
import com.codelanx.codelanxlib.listener.ListenerManager;
import com.codelanx.codelanxlib.util.CoverageUtil;
import com.codelanx.codelanxlib.util.CoverageUtil.Coverage;
import com.codelanx.lanxlibexample.config.ConfigValue;
import com.codelanx.lanxlibexample.listener.ExampleListener;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class description for {@link LanxLibExample}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class LanxLibExample extends JavaPlugin {

    private ConfigurationLoader config;
    private ListenerManager<LanxLibExample> listener;

    @Override
    @Coverage(3)
    public void onEnable() {
        CoverageUtil.load(this);
        CoverageUtil.registerClasses(this, LanxLibExample.class);
        this.config = new ConfigurationLoader(this, ConfigValue.class);
        
        this.getLogger().log(Level.INFO, "Loading listener example...");
        this.listener = new ListenerManager<>(this);
        this.listener.registerListener(new ExampleListener(this));
        if (this.listener.isRegistered(ExampleListener.class)) {
            CoverageUtil.marker(this);
            this.getLogger().log(Level.INFO, "{0} is registered!", ExampleListener.class.getName());
        }
        ExampleListener lis = this.listener.getListener(ExampleListener.class);
        if (lis != null) {
            if (false) {
                CoverageUtil.marker(this);
            }
            this.getLogger().log(Level.INFO, "{0} successfully retrieved from manager!", ExampleListener.class.getName());
        }
        CoverageUtil.marker(this);
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
