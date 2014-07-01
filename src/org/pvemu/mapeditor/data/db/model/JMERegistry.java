package org.pvemu.mapeditor.data.db.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.db.dao.JMERegistryDAO;
import org.pvemu.mapeditor.handler.ParametersHandler;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class JMERegistry extends JMEParameter{
    final private int id;
    final private JMERegistry parent;
    
    final private Map<String, JMERegistry> children = new HashMap<>();
    final private JMERegistryDAO dao;

    public JMERegistry(int id, JMERegistry parent, String name, ParametersHandler.ParameterType type, Object value, JMERegistryDAO dao) {
        super(name, type, value);
        this.id = id;
        this.parent = parent;
        this.dao = dao;
    }

    public int getId() {
        return id;
    }

    public JMERegistry getParent() {
        return parent;
    }
    
    private void checkChildren(){
        if(children.isEmpty()){
            try{
                for(JMERegistry child : dao.getChildren(this)){
                    children.put(child.getName(), child);
                }
            }catch(SQLException e){
                JMapEditor.getErrorHandler().showError("Registre : erreur", e);
            }
        }
    }
    
    public Collection<JMERegistry> getChildren(){
        checkChildren();
        return children.values();
    }
    
    public JMERegistry get(String name){
        checkChildren();
        return children.get(name);
    }
    
    public JMERegistry put(String name, ParametersHandler.ParameterType type, Object value){
        JMERegistry child = null;
        if(children.containsKey(name)){
            child = children.get(name);
            Object nValue = type.getValue(value.toString());
            child.setType(type);
            child.setValue(nValue);
            try{
                dao.update(child);
            }catch(SQLException e){
                JMapEditor.getErrorHandler().showError("Registre : erreur", e);
            }
        }else{
            try{
                child = dao.create(name, this, type, value);
                children.put(name, child);
            }catch(SQLException e){
                JMapEditor.getErrorHandler().showError("Registre : erreur", e);
            }
        }
        
        return child;
    }
    
    public void remove(String name){
        JMERegistry registry = children.get(name);
        
        if(registry == null)
            return;
        
        try{
            registry.delete();
        }catch(SQLException e){
            JMapEditor.getErrorHandler().showError("Registre : erreur", e);
        }
    }
    
    public void delete() throws SQLException{
        parent.children.remove(getName());
        
        Collection<JMERegistry> tmp = new ArrayList<>(children.values());
        
        for(JMERegistry child : tmp){
            child.delete();
        }
        
        children.clear();
        setValue(null);
        setType(ParametersHandler.ParameterType.NULL);
        dao.delete(this);
    }
}
