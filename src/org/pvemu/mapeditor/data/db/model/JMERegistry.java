package org.pvemu.mapeditor.data.db.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.pvemu.mapeditor.handler.setting.Parameter;
import org.pvemu.mapeditor.handler.setting.ParameterType;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMERegistry implements Parameter{
    final private int id;
    final private String name;
    final private JMERegistry parent;
    private ParameterType type;
    private Object value;
    final private Map<String, JMERegistry> children = new HashMap<>();

    public JMERegistry(int id, String name, JMERegistry parent, ParameterType type, Object value) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.type = type;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public JMERegistry getParent() {
        return parent;
    }

    @Override
    public ParameterType getType() {
        return type;
    }

    @Override
    public void setType(ParameterType type) {
        this.type = type;
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
    
    public Collection<JMERegistry> getChildren(){
        return children.values();
    }
    
    public JMERegistry get(String name){
        return children.get(name);
    }
    
    public void add(JMERegistry registry){
        children.put(registry.name, registry);
    }
    
    public void remove(String name){
        children.remove(name);
    }
    
    public void clear(){
        children.clear();
    }
}
