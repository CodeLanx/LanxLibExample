/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codelanx.lanxlibexample.config;

import com.codelanx.codelanxlib.config.ConfigMarker;

/**
 * Class description for {@link ConfigValue}
 *
 * @since 1.0.0
 * @author 1Rogue
 * @version 1.0.0
 */
public enum ConfigValue implements ConfigMarker<ConfigValue> {
    
    EXAMPLE_VALUE("example", "test"),
    EXAMPLE_DOUBLE("example-double", 20.00);
    
    private final String path;
    private final Object def;
    
    private ConfigValue(String path, Object def) {
        this.path = path;
        this.def = def;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Object getDefault() {
        return this.def;
    }

}
