package org.pvemu.mapeditor.handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.dao.JMEParameterDAO;
import org.pvemu.mapeditor.data.db.model.JMEParameter;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ParametersHandler extends AbstractTableModel{
    final private JMEParameterDAO parameterDAO;
    final private List<JMEParameter> parameters;
    final private Map<String, JMEParameter> paramByName = new HashMap<>();

    public ParametersHandler(SQLiteConnection connection) throws SQLException {
        parameterDAO = new JMEParameterDAO(connection);
        parameterDAO.createTable();
        parameters = parameterDAO.getAll();
        
        for(JMEParameter param : parameters){
            paramByName.put(param.getName(), param);
        }
    }
    
    private JMEParameter getParameter(String name) throws SQLException{
        JMEParameter param = paramByName.get(name);
        
        if(param == null){
            param = parameterDAO.getByName(name);
            
            if(param != null){
                paramByName.put(name, param);
                parameters.add(param);
            }
        }
        
        return param;
    }
    
    private JMEParameter getWithDefault(String name, JMEParameter.ParameterType type, Object defaultValue) throws SQLException{
        JMEParameter param = getParameter(name);
        
        if(param == null){
            param = new JMEParameter(name, type, defaultValue);
            paramByName.put(name, param);
            parameters.add(param);
            parameterDAO.create(param);
        }
        
        return param;
    }
    
    public void setParameter(String name, JMEParameter.ParameterType type, Object value) throws SQLException, Exception{
        JMEParameter param = paramByName.get(name);
        
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
                return JMEParameter.ParameterType.class;
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
                    JMEParameter.ParameterType type = (JMEParameter.ParameterType)aValue;
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
    
    public void removeParameter(String name) throws SQLException{
        JMEParameter param = paramByName.get(name);
        
        if(param == null)
            return;
        
        removeParameterRow(parameters.indexOf(param));
    }
    
    public void removeParameterRow(int rowIndex) throws SQLException{
        if(rowIndex == -1 || rowIndex >= parameters.size())
            return;
        
        JMEParameter param = parameters.get(rowIndex);
        
        parameters.remove(rowIndex);
        paramByName.remove(param.getName());
        parameterDAO.delete(param);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    private Object getAndVerify(String name, JMEParameter.ParameterType type) throws SQLException{
        JMEParameter parameter = verifyParameter(getParameter(name), type);
        return parameter.getValue();
    }
    
    private JMEParameter verifyParameter(JMEParameter parameter, JMEParameter.ParameterType type){
        if(parameter == null)
            throw new IllegalArgumentException("The parameter is null");
        
        if(parameter.getType() != type)
            throw new IllegalArgumentException("The parameter '" + parameter.getName() + "' is not type of '" + type + "' but '" + parameter.getType() + "' !");
        
        return parameter;
    }
    
    private Object getAndVerifyWithDefault(String name, JMEParameter.ParameterType type, Object defaultValue) throws SQLException{
        JMEParameter parameter = verifyParameter(getWithDefault(name, type, defaultValue), type);
        return parameter.getValue();
    }
    
    public int getInt(String name) throws SQLException{
        return (int)getAndVerify(name, JMEParameter.ParameterType.INT);
    }
    
    public int getIntDefault(String name, int def) throws SQLException{
        return (int)getAndVerifyWithDefault(name, JMEParameter.ParameterType.INT, def);
    }
    
    public void setInt(String name, int value) throws Exception{
        setParameter(name, JMEParameter.ParameterType.INT, value);
    }
    
    public String getString(String name) throws SQLException{
        return (String)getAndVerify(name, JMEParameter.ParameterType.STRING);
    }
    
    public String getStringDefault(String name, String def) throws SQLException{
        return (String)getAndVerifyWithDefault(name, JMEParameter.ParameterType.STRING, def);
    }
    
    public void setString(String name, String value) throws Exception{
        setParameter(name, JMEParameter.ParameterType.STRING, value);
    }
    
    public boolean getBool(String name) throws SQLException{
        return (boolean)getAndVerify(name, JMEParameter.ParameterType.BOOL);
    }
    
    public boolean getBoolDefault(String name, boolean def) throws SQLException{
        return (boolean)getAndVerifyWithDefault(name, JMEParameter.ParameterType.BOOL, def);
    }
    
    public void setBool(String name, boolean value) throws Exception{
        setParameter(name, JMEParameter.ParameterType.BOOL, value);
    }
    
    public double getDouble(String name) throws SQLException{
        return (double)getAndVerify(name, JMEParameter.ParameterType.DOUBLE);
    }
    
    public double getDoubleDefault(String name, double def) throws SQLException{
        return (double)getAndVerifyWithDefault(name, JMEParameter.ParameterType.DOUBLE, def);
    }
    
    public void setDouble(String name, double value) throws Exception{
        setParameter(name, JMEParameter.ParameterType.DOUBLE, value);
    }
}
