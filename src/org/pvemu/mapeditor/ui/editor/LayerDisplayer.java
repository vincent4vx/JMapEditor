package org.pvemu.mapeditor.ui.editor;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import org.pvemu.mapeditor.ui.CellObjectRenderer;
import java.awt.Graphics2D;
import java.util.EnumMap;
import org.pvemu.mapeditor.action.JMapEditor;
import org.pvemu.mapeditor.common.Constants;
import org.pvemu.mapeditor.data.Cell;
import org.pvemu.mapeditor.data.CellObject;
import org.pvemu.mapeditor.data.MapData;
import org.pvemu.mapeditor.handler.layer.Layer;
import org.pvemu.mapeditor.handler.tool.AddTool;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
abstract class LayerDisplayer {
    final static private EnumMap<Layer, LayerDisplayer> displayers = new EnumMap<>(Layer.class);
    
    static{
        displayers.put(Layer.GRID, new GridLayerDisplayer());
        displayers.put(Layer.BACKGROUND, new BackgroundLayerDisplayer());
        
        for(Layer layer : Layer.values()){
            if(!layer.isEditable())
                continue;
            displayers.put(layer, new LayerObjectDisplayer());
        }
        
        displayers.put(Layer.WALKABLE, new WalkableLayerDisplayer());
        displayers.put(Layer.LINE_OF_SIGHT, new LineOfSightDisplayer());
    }

    static public void display(Layer layer, EditorGrid grid, Graphics2D g, boolean isEdit){
        if (isEdit && !layer.isVisible()) {
            return;
        }
        
        LayerDisplayer displayer = displayers.get(layer);
        
        if(displayer == null){
            System.err.println("No displayer found for layer " + layer);
            return;
        }

        Composite tmp = g.getComposite();
        
        if(isEdit)
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, layer.getAlpha()));

        displayer.draw(layer, grid, g, isEdit);

        g.setComposite(tmp);
    }

    private LayerDisplayer() {
    }

    abstract void draw(Layer layer, EditorGrid grid, Graphics2D g, boolean isEdit);

    static private class LayerObjectDisplayer extends LayerDisplayer {

        @Override
        void draw(Layer layer, EditorGrid grid, Graphics2D g, boolean isEdit) {
            Cell currentCell = grid.getHandler().getSelectedCell();
            for (GridCell shape : grid.getShapes()) {
                CellObject obj = shape.getCell().getObjectAt(layer);

                if (obj == null && isEdit) {
                    //display object to add
                    if(shape.isHovered() && layer.isSelected() && JMapEditor.getToolsHandler().getTool() instanceof AddTool){
                        CellObjectRenderer.render(g, JMapEditor.getToolsHandler().getCurrentObject(), shape, false);
                    }
                    continue;
                }

                boolean hightlight = (shape.isHovered()
                        || currentCell == shape.getCell()) && layer.isSelected();

                CellObjectRenderer.render(g, obj, shape, hightlight);
            }
        }
    }

    static private class GridLayerDisplayer extends LayerDisplayer {

        @Override
        void draw(Layer layer, EditorGrid grid, Graphics2D g, boolean isEdit) {
            if(!isEdit && !JMapEditor.getParameters().getBool("EXPORT_IMG_GRID"))
                return;
            
            for (GridCell cell : grid.getShapes()) {

                g.setColor(Constants.GRID_COLOR);

                g.drawLine(
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][0][0] + cell.getX()),
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][0][1] + cell.getY()),
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][0] + cell.getX()),
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][1] + cell.getY())
                );
                g.drawLine(
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][0] + cell.getX()),
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][1][1] + cell.getY()),
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][2][0] + cell.getX()),
                        (int) (Constants.CELL_COORD[cell.getCell().getGroundSlope()][2][1] + cell.getY())
                );
            }
        }
    }
    
    static private class BackgroundLayerDisplayer extends LayerDisplayer{
        @Override
        void draw(Layer layer, EditorGrid grid, Graphics2D g, boolean isEdit) {
            MapData map = grid.getMap();
            
            if(map.getBackground() != null){
                g.drawImage(map.getBackground().getImage(), 0, 0, grid.getSize().width, grid.getSize().height , grid);
            }
        }
    }
    
    static private class WalkableLayerDisplayer extends LayerDisplayer{

        @Override
        void draw(Layer layer, EditorGrid grid, Graphics2D g, boolean isEdit) {
            for(GridCell cell : grid.getShapes()){
                g.setColor(Constants.UNWALKABLE_COLOR);
                
                if(cell.getCell().getMovement() != 4){
                    g.fill(cell);
                    g.setColor(Color.BLACK);
                    g.drawString(cell.getCell().getMovement() + "", cell.getX(), cell.getY());
                }
            }
        }
        
    }
    
    static private class LineOfSightDisplayer extends LayerDisplayer{

        @Override
        void draw(Layer layer, EditorGrid grid, Graphics2D g, boolean isEdit) {
            for(GridCell cell : grid.getShapes()){
                g.setColor(Constants.SIGHT_BLOCK_COLOR);
                
                if(!cell.getCell().isLineOfSight())
                    g.fill(cell);
            }
        }
        
    }
}
