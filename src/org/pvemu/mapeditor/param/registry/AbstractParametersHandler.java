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

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract class AbstractParametersHandler<T extends Parameter>{
    
    abstract T getParameter(String name);
    
    abstract T createParameter(String name, ParameterType type, Object value);
    
    private T getWithDefault(String name, ParameterType type, Object defaultValue){
        T param = getParameter(name);
        
        if(param == null){
            param = createParameter(name, type, defaultValue);
        }
        
        return param;
    }
    
    abstract void updateParameter(T param, ParameterType type, Object value);
    
    public boolean exists(String name){
        return getParameter(name) != null;
    }
    
    public void set(String name, ParameterType type, Object value){
        T param = getParameter(name);
        
        if(param == null){
            throw new IndexOutOfBoundsException("The parameter " + name + " does not exists.");
        }else{
            updateParameter(param, type, value);
        }
    }
    
    public void add(String name, ParameterType type, Object value){
        createParameter(name, type, value);
    }
    
    public void setOrAdd(String name, ParameterType type, Object value){
        if(exists(name))
            set(name, type, value);
        else
            add(name, type, value);
    }
    
    abstract void removeParameter(T param);
    
    public void unset(String name){
        T param = getParameter(name);
        
        if(param == null)
            return;
        
        removeParameter(param);
    }
    
    private Object getAndVerify(String name, ParameterType type){
        T parameter = getParameter(name);
        
        if(parameter == null)
            throw new IndexOutOfBoundsException("The parameter " + name + " does not exists.");
        
        parameter = verifyParameter(parameter, type);
        return parameter.getValue();
    }
    
    private T verifyParameter(T parameter, ParameterType type){
        if(parameter == null)
            throw new IllegalArgumentException("The parameter is null");
        
        if(parameter.getType() != type)
            throw new IllegalArgumentException("The parameter '" + parameter.getName() + "' is not type of '" + type + "' but '" + parameter.getType() + "' !");
        
        return parameter;
    }
    
    private Object getAndVerifyWithDefault(String name, ParameterType type, Object defaultValue){
        T parameter = verifyParameter(getWithDefault(name, type, defaultValue), type);
        return parameter.getValue();
    }
    
    public int getInt(String name){
        return (int)getAndVerify(name, ParameterType.INT);
    }
    
    public int getIntDefault(String name, int def){
        return (int)getAndVerifyWithDefault(name, ParameterType.INT, def);
    }
    
    public void setInt(String name, int value){
        set(name, ParameterType.INT, value);
    }
    
    public String getString(String name){
        return (String)getAndVerify(name, ParameterType.STRING);
    }
    
    public String getStringDefault(String name, String def){
        return (String)getAndVerifyWithDefault(name, ParameterType.STRING, def);
    }
    
    public void setString(String name, String value){
        set(name, ParameterType.STRING, value);
    }
    
    public boolean getBool(String name){
        return (boolean)getAndVerify(name, ParameterType.BOOL);
    }
    
    public boolean getBoolDefault(String name, boolean def){
        return (boolean)getAndVerifyWithDefault(name, ParameterType.BOOL, def);
    }
    
    public void setBool(String name, boolean value){
        set(name, ParameterType.BOOL, value);
    }
    
    public double getDouble(String name){
        return (double)getAndVerify(name, ParameterType.DOUBLE);
    }
    
    public double getDoubleDefault(String name, double def){
        return (double)getAndVerifyWithDefault(name, ParameterType.DOUBLE, def);
    }
    
    public void setDouble(String name, double value){
        set(name, ParameterType.DOUBLE, value);
    }
    
    
    public float getFloat(String name){
        return (float)getAndVerify(name, ParameterType.FLOAT);
    }
    
    public float getFloatDefault(String name, float def){
        return (float)getAndVerifyWithDefault(name, ParameterType.FLOAT, def);
    }
    
    public void setFloat(String name, float value){
        set(name, ParameterType.FLOAT, value);
    }
}

