package org.pvemu.mapeditor.ui.rightmenu;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.handler.tool.StateTool;
import org.pvemu.mapeditor.handler.tool.Tools;
import org.pvemu.mapeditor.ui.Icons;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class EditTab extends JPanel{
    final private JToggleButton select = new JToggleButton(Icons.SELECT);
    final private JToggleButton add = new JToggleButton(Icons.ADD);
    final private JToggleButton remove = new JToggleButton(Icons.REMOVE);
    final private JToggleButton state = new JToggleButton(Icons.STATE);
    final private JComboBox<StateTool.CellState> stateSelector = new JComboBox<>(StateTool.CellState.values());
    
    final private ButtonGroup group = new ButtonGroup();

    public EditTab() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        tilesTools();
        stateTools();
        utilsTools();
    }
    
    private void tilesTools(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Objets"));
        
        select.setPreferredSize(new Dimension(20, 20));
        select.setSelected(true);
        select.setToolTipText("Sélectionner");
        select.addActionListener((e) -> JMapEditor.getToolsHandler().setTool(Tools.SELECT));
        panel.add(select);
        
        add.setPreferredSize(new Dimension(20, 20));
        add.setToolTipText("Ajouter une tile");
        add.addActionListener((e) -> JMapEditor.getToolsHandler().setTool(Tools.ADD));
        panel.add(add);
        
        remove.setPreferredSize(new Dimension(20, 20));
        remove.setToolTipText("Supprimer");
        remove.addActionListener((e) -> JMapEditor.getToolsHandler().setTool(Tools.REMOVE));
        panel.add(remove);
        
        group.add(select);
        group.add(remove);
        group.add(add);
        
        add(panel);
    }
    
    private void stateTools(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "État"));
        
        state.setPreferredSize(new Dimension(20, 20));
        state.setToolTipText("Changer l'état de la cellule");
        state.addActionListener((e) -> JMapEditor.getToolsHandler().setTool(Tools.STATE));
        panel.add(state);
        
        panel.add(stateSelector);
        
        group.add(state);
        
        add(panel);
    }
    
    private void utilsTools(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Outils"));
        
        JToggleButton preview = new JToggleButton(Icons.PREVIEW);
        
        preview.setPreferredSize(new Dimension(20, 20));
        preview.setToolTipText("Apperçue de la map");
        preview.addActionListener((e) -> JMapEditor.getToolsHandler().setTool(Tools.PREVIEW));
        panel.add(preview);
        
        group.add(preview);
        
        add(panel);
    }

    public JToggleButton getSelect() {
        return select;
    }

    public JToggleButton getAdd() {
        return add;
    }

    public JToggleButton getRemove() {
        return remove;
    }

    public JComboBox<StateTool.CellState> getStateSelector() {
        return stateSelector;
    }
}
