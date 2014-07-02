/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data.db.model;

import org.pvemu.mapeditor.handler.setting.Parameter;
import org.pvemu.mapeditor.handler.setting.ParameterType;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMEParameter implements Parameter{
    
    final private String name;
    private ParameterType type;
    private Object value;

    public JMEParameter(String name, ParameterType type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ParameterType getType() {
        return type;
    }

    @Override
    public void setType(ParameterType type) {
        this.type = type;
    }
    
}
