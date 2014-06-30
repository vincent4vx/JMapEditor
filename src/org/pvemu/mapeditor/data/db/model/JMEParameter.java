/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.data.db.model;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMEParameter {
    public enum ParameterType{
        STRING{
            @Override
            public Object getValue(String value) {
                return value;
            }
        },
        INT{
            @Override
            public Object getValue(String value) {
                return Integer.parseInt(value);
            }
        },
        DOUBLE{
            @Override
            public Object getValue(String value) {
                return Double.parseDouble(value);
            }
        },
        BOOL{
            @Override
            public Object getValue(String value) {
                return Boolean.parseBoolean(value);
            }
        }
        ;
        abstract public Object getValue(String value);
    }
    
    final private String name;
    private ParameterType type;
    private Object value;

    public JMEParameter(String name, ParameterType type, Object value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }
    
}
