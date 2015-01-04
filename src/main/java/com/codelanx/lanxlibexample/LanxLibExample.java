/*
 * Copyright (C) 2015 Codelanx, All Rights Reserved
 *
 * This work is licensed under a Creative Commons
 * Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 *
 * This program is protected software: You are free to distrubute your
 * own use of this software under the terms of the Creative Commons BY-NC-ND
 * license as published by Creative Commons in the year 2015 or as published
 * by a later date. You may not provide the source files or provide a means
 * of running the software outside of those licensed to use it.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the Creative Commons BY-NC-ND license
 * long with this program. If not, see <https://creativecommons.org/licenses/>.
 */
package com.codelanx.lanxlibexample;

import com.codelanx.codelanxlib.listener.ListenerManager;
import com.codelanx.codelanxlib.util.CoverageUtil;
import com.codelanx.codelanxlib.util.CoverageUtil.Coverage;
import com.codelanx.codelanxlib.util.DebugUtil;
import com.codelanx.lanxlibexample.config.ConfigValue;
import com.codelanx.lanxlibexample.listener.ExampleListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Class description for {@link LanxLibExample}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public class LanxLibExample extends JavaPlugin {

    private ListenerManager<LanxLibExample> listener;

    @Override
    @Coverage(3)
    public void onEnable() {
        CoverageUtil.load(this);
        CoverageUtil.registerClasses(this, LanxLibExample.class);
        
        DebugUtil.print("Example config value: %s", ConfigValue.EXAMPLE_VALUE.as(String.class));
        DebugUtil.print("Loading listener example...");
        this.listener = new ListenerManager<>(this);
        this.listener.registerListener(new ExampleListener(this));
        if (this.listener.isRegistered(ExampleListener.class)) {
            CoverageUtil.marker(this);
            DebugUtil.print("%s is registered!", ExampleListener.class.getName());
        }
        ExampleListener lis = this.listener.getListener(ExampleListener.class);
        if (lis != null) {
            if (false) { //Example of not-activated marker
                CoverageUtil.marker(this);
            }
            DebugUtil.print("%s successfully retrieved from manager!", ExampleListener.class.getName());
        }
        CoverageUtil.marker(this);
    }

    @Override
    public void onDisable() {
        this.listener.cleanup();
    }

}
