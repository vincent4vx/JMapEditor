/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.handler.setting;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ImmutableParameter implements BasicParameter{
    final private String name;
    final private ParameterType type;
    final private Object value;

    public ImmutableParameter(String name, ParameterType type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }
    
    public ImmutableParameter(Parameter parameter){
        name = parameter.getName();
        type = parameter.getType();
        value = parameter.getValue();
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
    public Object getValue() {
        return value;
    }
    
    
}
