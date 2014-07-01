package org.pvemu.mapeditor.handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.dao.JMEParameterDAO;
import org.pvemu.mapeditor.data.db.dao.JMERegistryDAO;
import org.pvemu.mapeditor.data.db.model.JMEParameter;
import org.pvemu.mapeditor.data.db.model.JMERegistry;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ParametersHandler extends AbstractTableModel{
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
        },
        NULL{
            @Override
            public Object getValue(String value) {
                return null;
            }
        }
        ;
        abstract public Object getValue(String value);
    }
    
    final private JMEParameterDAO parameterDAO;
    final private JMERegistryDAO registryDAO;
    final private List<JMEParameter> parameters;
    final private Map<String, JMEParameter> paramByName = new HashMap<>();
    final private JMERegistry registry;

    public ParametersHandler(SQLiteConnection connection) throws SQLException {
        parameterDAO = new JMEParameterDAO(connection);
        registryDAO = new JMERegistryDAO(connection);
        registryDAO.createTable();
        parameterDAO.createTable();
        parameters = parameterDAO.getAll();
        registry = registryDAO.getRoot();
        
        for(JMEParameter param : parameters){
            paramByName.put(param.getName(), param);
        }
    }
    
    private JMEParameter getParameter(String name){
        JMEParameter param = paramByName.get(name);
        
        if(param == null){
            try{
                param = parameterDAO.getByName(name);
            }catch(SQLException ex){
                JMapEditor.getErrorHandler().showError("Impossible de récupérer le paramètre...", ex);
            }
            
            if(param != null){
                paramByName.put(name, param);
                parameters.add(param);
            }
        }
        
        return param;
    }
    
    private JMEParameter getWithDefault(String name, ParameterType type, Object defaultValue){
        JMEParameter param = getParameter(name);
        
        if(param == null){
            param = new JMEParameter(name, type, defaultValue);
            try{
                parameterDAO.create(param);
            }catch(SQLException e){
                JMapEditor.getErrorHandler().showError("Sauvegarde du paramètre impossible !", e);
                return null;
            }
            paramByName.put(name, param);
            parameters.add(param);
        }
        
        return param;
    }
    
    public void setParameter(String name, ParameterType type, Object value){
        JMEParameter param = paramByName.get(name);
        
        try{
            if(param == null){
                param = new JMEParameter(name, type, value);
                parameterDAO.create(param);
                paramByName.put(name, param);
                parameters.add(param);
                fireTableRowsInserted(parameters.size() - 1, parameters.size() - 1);
            }else{
                param.setValue(value);
                param.setType(type);
                parameterDAO.update(param);
            }
        }catch(SQLException e){
            JMapEditor.getErrorHandler().showError("Sauvegarde du paramètre impossible !", e);
        }
    }

    @Override
    public int getRowCount() {
        return parameters.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        JMEParameter param = parameters.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return param.getName();
            case 1:
                return param.getType();
            case 2:
                return param.getValue().toString();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return ParameterType.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        JMEParameter param = parameters.get(rowIndex);
        
        try{
            switch(columnIndex){
                case 1:{
                    ParameterType type = (ParameterType)aValue;
                    Object nValue = type.getValue(param.getValue().toString());
                    param.setValue(nValue);
                    param.setType(type);
                }   break;
                case 2:
                    param.setValue(param.getType().getValue((String)aValue));
                    break;
            }
        
            parameterDAO.update(param);
        }catch(IllegalArgumentException ex){
            JMapEditor.getErrorHandler().showError("Modification d'un paramètre : erreur", "Valeur invalide.");
        }catch(Exception ex){
            JMapEditor.getErrorHandler().showError("Modification d'un paramètre : erreur", ex);
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0:
                return "Nom";
            case 1:
                return "Type";
            case 2:
                return "Valeur";
            default:
                return null;
        }
    }
    
    public void removeParameter(String name){
        JMEParameter param = paramByName.get(name);
        
        if(param == null)
            return;
        
        removeParameterRow(parameters.indexOf(param));
    }
    
    public void removeParameterRow(int rowIndex){
        if(rowIndex == -1 || rowIndex >= parameters.size())
            return;
        
        JMEParameter param = parameters.get(rowIndex);
        
        try{
            parameterDAO.delete(param);
        }catch(SQLException e){
            JMapEditor.getErrorHandler().showError("Suppression du paramètre impossible...", e);
            return;
        }
        
        parameters.remove(rowIndex);
        paramByName.remove(param.getName());
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    private Object getAndVerify(String name, ParameterType type){
        JMEParameter parameter = verifyParameter(getParameter(name), type);
        return parameter.getValue();
    }
    
    private JMEParameter verifyParameter(JMEParameter parameter, ParameterType type){
        if(parameter == null)
            throw new IllegalArgumentException("The parameter is null");
        
        if(parameter.getType() != type)
            throw new IllegalArgumentException("The parameter '" + parameter.getName() + "' is not type of '" + type + "' but '" + parameter.getType() + "' !");
        
        return parameter;
    }
    
    private Object getAndVerifyWithDefault(String name, ParameterType type, Object defaultValue){
        JMEParameter parameter = verifyParameter(getWithDefault(name, type, defaultValue), type);
        return parameter.getValue();
    }
    
    public int getInt(String name){
        return (int)getAndVerify(name, ParameterType.INT);
    }
    
    public int getIntDefault(String name, int def){
        return (int)getAndVerifyWithDefault(name, ParameterType.INT, def);
    }
    
    public void setInt(String name, int value){
        setParameter(name, ParameterType.INT, value);
    }
    
    public String getString(String name){
        return (String)getAndVerify(name, ParameterType.STRING);
    }
    
    public String getStringDefault(String name, String def){
        return (String)getAndVerifyWithDefault(name, ParameterType.STRING, def);
    }
    
    public void setString(String name, String value){
        setParameter(name, ParameterType.STRING, value);
    }
    
    public boolean getBool(String name){
        return (boolean)getAndVerify(name, ParameterType.BOOL);
    }
    
    public boolean getBoolDefault(String name, boolean def){
        return (boolean)getAndVerifyWithDefault(name, ParameterType.BOOL, def);
    }
    
    public void setBool(String name, boolean value){
        setParameter(name, ParameterType.BOOL, value);
    }
    
    public double getDouble(String name){
        return (double)getAndVerify(name, ParameterType.DOUBLE);
    }
    
    public double getDoubleDefault(String name, double def){
        return (double)getAndVerifyWithDefault(name, ParameterType.DOUBLE, def);
    }
    
    public void setDouble(String name, double value){
        setParameter(name, ParameterType.DOUBLE, value);
    }

    public JMERegistry getRegistry() {
        return registry;
    }
}
