package org.pvemu.mapeditor.base.ui.builder;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import org.pvemu.mapeditor.ui.BaseMenuBar;
import org.pvemu.mapeditor.common.ui.UIBuilderComponent;
import org.pvemu.mapeditor.common.ui.UIBuilderContainer;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class BaseMenuBarBuilder extends UIBuilderContainer<BaseMenuBar, BaseMenuBarBuilder.MenuBuilder>{
    abstract static public class MenuBuilder extends UIBuilderContainer<JMenu, UIBuilderComponent<JMenuItem>>{
        @Override
        protected void addToContainerUI(JMenu container, UIBuilderComponent<JMenuItem> component) {
            container.add(component.getUI());
        }
    }
    
    public enum BaseMenu{
        FILE("Fichier", 'F'),
        EDIT("Edition", 'E'),
        MAP("Map", 'M'),
        ;
        
        final private String name;
        final private char mnemonic;

        private BaseMenu(String name, char mnemonic) {
            this.name = name;
            this.mnemonic = mnemonic;
        }

        private MenuBuilder getContainer(){
            return new MenuBuilder() {

                @Override
                protected JMenu buildContainerUI() {
                    JMenu menu = new JMenu(name);
                    menu.setMnemonic(mnemonic);
                    return menu;
                }

                @Override
                public String getName() {
                    return name();
                }
            };
        }
    }

    public BaseMenuBarBuilder() {
        for(BaseMenu menu : BaseMenu.values()){
            add(menu.getContainer());
        }
    }

    @Override
    public String getName() {
        return "BASE_MENU_BAR";
    }

    @Override
    protected BaseMenuBar buildContainerUI() {
        return new BaseMenuBar();
    }
    
}
