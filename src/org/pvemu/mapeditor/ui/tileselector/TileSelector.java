package org.pvemu.mapeditor.ui.tileselector;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.data.TilesListContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class TileSelector extends JToolBar{
    final private Map<TilesListContainer, FolderSelector> selectors = new HashMap<>();
    final private TileListUI listUI = new TileListUI();
    final private JPanel panel = new JPanel(new BorderLayout());
    private FolderSelector current;

    public TileSelector() {
        panel.add(listUI, BorderLayout.CENTER);
        add(panel);
        setTilesListContainer(JMapEditor.getLayerHandler().getSelected().getTiles());
    }
    
    public void setTilesListContainer(TilesListContainer tlc){
        if(!selectors.containsKey(tlc))
            selectors.put(tlc, new FolderSelector(tlc, listUI));
        
        listUI.clear();
        if(current != null)
            panel.remove(current);
        
        current = selectors.get(tlc);
        panel.add(current, BorderLayout.WEST);
        panel.repaint();
        panel.revalidate();
    }
    
}
