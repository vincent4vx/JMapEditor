/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pvemu.mapeditor.handler.setting;

import java.util.Objects;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public enum ParameterType {

    STRING {
                @Override
                public Object getValue(Object value) {
                    return String.valueOf(value);
                }
            },
    INT {
                @Override
                public Object getValue(Object value) {
                    return Integer.parseInt(Objects.toString(value, "0"));
                }
            },
    DOUBLE {
                @Override
                public Object getValue(Object value) {
                    return Double.parseDouble(Objects.toString(value, "0"));
                }
            },
    BOOL {
                @Override
                public Object getValue(Object value) {
                    return Boolean.parseBoolean(String.valueOf(value));
                }
            },
    NULL {
                @Override
                public Object getValue(Object value) {
                    return null;
                }
            },
    FLOAT {

                @Override
                public Object getValue(Object value) {
                    return Float.parseFloat(String.valueOf(value));
                }

            };

    abstract public Object getValue(Object value);
}
