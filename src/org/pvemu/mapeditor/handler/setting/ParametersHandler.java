package org.pvemu.mapeditor.handler.setting;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import org.pvemu.mapeditor.common.SQLiteConnection;
import org.pvemu.mapeditor.data.db.dao.JMEParameterDAO;
import org.pvemu.mapeditor.data.db.model.JMEParameter;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class ParametersHandler extends AbstractParametersHandler<JMEParameter>{    
    final private JMEParameterDAO parameterDAO;
    final private List<JMEParameter> parameters;
    final private Map<String, JMEParameter> paramByName = new HashMap<>();
    
    final private AbstractTableModel model = new AbstractTableModel() {
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
                    return param.getValue();
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
            switch(columnIndex){
                case 1:
                    updateParameter(param, (ParameterType) aValue, param.getValue());
                    break;
                case 2:
                    updateParameter(param, param.getType(), aValue);
                    break;
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
    };

    public ParametersHandler(SQLiteConnection connection) throws SQLException {
        parameterDAO = new JMEParameterDAO(connection);
        parameterDAO.createTable();
        parameters = parameterDAO.getAll();
        
        for(JMEParameter param : parameters){
            paramByName.put(param.getName(), param);
        }
    }
    
    @Override
    JMEParameter getParameter(String name){
        JMEParameter param = paramByName.get(name);
        
        if(param == null){
            try{
                param = parameterDAO.getByName(name);
            }catch(SQLException ex){
                throw new RuntimeException(ex);
            }
            
            if(param != null){
                paramByName.put(name, param);
                parameters.add(param);
            }
        }
        
        return param;
    }

    @Override
    JMEParameter createParameter(String name, ParameterType type, Object value) {
        if(exists(name))
            throw new IllegalArgumentException("The Parameter " + name + " already exists !");
        
        value = type.getValue(value); //test and cast the value
        JMEParameter param = new JMEParameter(name, type, value);
        
        try{
            parameterDAO.create(param);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        paramByName.put(name, param);
        parameters.add(param);
        
        model.fireTableRowsInserted(parameters.size() - 1, parameters.size() - 1);
        
        return param;
    }

    @Override
    void updateParameter(JMEParameter param, ParameterType type, Object value) {
        value = type.getValue(value);
        param.setType(type);
        param.setValue(value);
        
        try{
            parameterDAO.update(param);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        int index = parameters.indexOf(param);
        
        if(index != -1)
            model.fireTableRowsUpdated(index, index);
    }

    @Override
    void removeParameter(JMEParameter param) {
        try{
            parameterDAO.delete(param);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        paramByName.remove(param.getName());
        int index = parameters.indexOf(param);
        
        if(index != -1){
            model.fireTableRowsDeleted(index, index);
        }
        
        parameters.remove(index);
    }
    
    public void removeParameterRow(int rowIndex){
        if(rowIndex == -1 || rowIndex >= parameters.size())
            return;
        
        JMEParameter param = parameters.get(rowIndex);
        removeParameter(param);
    }

    public AbstractTableModel getModel() {
        return model;
    }
}
