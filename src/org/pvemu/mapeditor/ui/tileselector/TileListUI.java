package org.pvemu.mapeditor.ui.tileselector;

import java.awt.Dimension;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.Tile;
import org.pvemu.mapeditor.data.TilesList;
import org.pvemu.mapeditor.handler.tool.Tools;
import org.pvemu.mapeditor.ui.TileRenderer;
import org.pvemu.mapeditor.ui.rightmenu.ObjectTab;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class TileListUI extends JScrollPane{
    final private JList<Tile> list = new JList<>();

    public TileListUI() {
        setPreferredSize(new Dimension(getPreferredSize().width, 120));
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new TileRenderer());
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        
        list.addListSelectionListener((e) -> {
            Tile tile = list.getSelectedValue();
            
            if(tile == null)
                return;
            
            CellObject obj = new CellObject(tile);
            JMapEditor.getToolsHandler().setCurrentObject(obj);
            JMapEditor.getUI().getRightMenu().setObjectTab(new ObjectTab(obj));
            JMapEditor.getToolsHandler().setTool(Tools.ADD);
        });
        
        setViewportView(list);
    }

    public void setTiles(TilesList tiles) {
        list.setModel(tiles);
    }
    
    public void clear(){
        list.setModel(new AbstractListModel<Tile>() {

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public Tile getElementAt(int index) {
                return null;
            }
        });
    }
    
}
