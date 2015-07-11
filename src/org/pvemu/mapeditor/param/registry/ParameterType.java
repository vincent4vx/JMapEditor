/* 
 * Copyright (C) 2014 Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pvemu.mapeditor.param.registry;

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
