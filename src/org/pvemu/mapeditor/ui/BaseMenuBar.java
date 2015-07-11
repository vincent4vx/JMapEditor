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
package org.pvemu.mapeditor.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
final public class BaseMenuBar extends JMenuBar{
    //final private Map<String, JMenu> menus = new HashMap<>();

    /*public BaseMenuBar() {
        addMenu("file", file());
        addMenu("edit", edit());
        addMenu("map", map());
    }
    
    public void addMenu(String name, JMenu menu){
        if(menus.containsKey(name))
            throw new Error("The BaseMenuBar already containt a menu named " + name);
        
        menus.put(name, menu);
        add(menu);
    }
    
    public void addSubMenuInto(String menuName, JMenuItem submenu){
        JMenu menu = menus.get(menuName);
        
        if(menu == null)
            throw new Error("The menu " + menuName + " was not found");
        
        menu.add(submenu);
    }*/
    
    private JMenu file(){
        JMenu file = new JMenu("Fichier");
        file.setMnemonic('F');
        
        /*JMenuItem newMap = new JMenuItem("Nouvelle map");
        newMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        newMap.addActionListener((e) -> {
            try{
                new NewMapDialog();    
            }catch(SQLException ex){
                JMapEditor.getErrorHandler().showError("Nouvelle map : erreur", ex);
            }
        });
        file.add(newMap);
        
        JMenuItem open = new JMenuItem("Ouvrir");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        open.addActionListener((e) -> open());
        file.add(open);
        
        JMenuItem save = new JMenuItem("Sauvegarder");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        save.addActionListener((e) -> save());
        file.add(save);*/
        
        return file;
    }
    
    private JMenu edit(){
        JMenu edit = new JMenu("Edition");
        edit.setMnemonic('E');
        
        /*JMenuItem settings = new JMenuItem("Paramètres");
        edit.add(settings);
        settings.addActionListener((e) -> new SettingsDialog().setVisible(true));*/
        
        return edit;
    }
    
    private JMenu map(){
        JMenu map = new JMenu("Map");
        map.setMnemonic('M');
        
        /*JMenuItem export = new JMenuItem("Exporter");
        export.addActionListener((e) -> export());
        map.add(export);
        
        final JMenu versions = new JMenu("Versions");
        versions.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                try {
                    versions.removeAll();
                    
                    Editor handler = Editor.getCurrentHandler();
                    
                    if(handler == null)
                        return;
                    
                    MapDBHandler DBHandler = handler.getData();
                    
                    if(DBHandler == null)
                        return;
                    
                    for(MapHistory history : DBHandler.getHistoryDAO().getAll()){
                        JMenuItem item = new JMenuItem((new SimpleDateFormat("d/M/y H:m:s")).format(new Date(history.getDate())));
                        item.addActionListener((evt) -> handler.loadHistory(history));
                        versions.add(item);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(JMapEditor.getUI(), ex, "Error during loading history", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace(System.err);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {}

            @Override
            public void menuCanceled(MenuEvent e) {}
        });
        map.add(versions);*/
        
        return map;
    }
    
    /*private void open(){
        try{
            JFileChooser chooser = JMEFileChooser.getFileChooser();
            
            if(chooser.showOpenDialog(JMapEditor.getUI()) == JFileChooser.CANCEL_OPTION)
                return;
            
            File file = chooser.getSelectedFile();
            OpenMap.loadMap(file.getAbsolutePath());
        }catch(Exception ex){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), "Erreur lors de l'ouverture : " + ex, "Ouverture : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }
    
    private void save(){
        Editor handler = Editor.getCurrentHandler();

        if(handler == null){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), "Veuillez sélectionner une map à sauvegarder", "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try{
            handler.save();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), ex, "Sauvegarde : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }
    
    private void export(){
        Editor handler = Editor.getCurrentHandler();

        if(handler == null)
            return;

        /*try {
            JMapEditor.getExportHandler().export(handler.getMap());
        } catch (Exception ex){
            JOptionPane.showMessageDialog(JMapEditor.getUI(), ex, "Exportation : erreur", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }*/
        
      /*  new ExportDialog(handler);
    }*/
}
