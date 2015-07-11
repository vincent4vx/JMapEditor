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
package org.pvemu.mapeditor.splashscreen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import org.pvemu.mapeditor.JMapEditor;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SplashScreen extends JWindow{
    final private SplashConfigSet configSet = new SplashConfigSet();
    
    final private JLabel info = new JLabel(){

        @Override
        protected void paintComponent(Graphics g) {
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            super.paintComponent(g);
        }
        
    };
    final private JProgressBar progressBar = new JProgressBar();
    
    private class SplashPanel extends JPanel{
        final private Image bg;

        public SplashPanel() throws IOException {
            setLayout(null);
            setPreferredSize(new Dimension(
                    configSet.SPLASH_SCREEN_WIDTH.getValue(), 
                    configSet.SPLASH_SCREEN_HEIGHT.getValue()
            ));
            bg = ImageIO.read(new File(configSet.SPLASH_BACKGROUND.getValue()));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg, 0, 0, this);
        }
        
    }

    public SplashScreen() throws HeadlessException, IOException {
        JMapEditor.getParameters().getConfig().handle(configSet);
        JPanel panel = new SplashPanel();
        setContentPane(panel);
        
        info.setText("chargement...");
        add(info);
        info.setForeground(Color.WHITE);
        info.setBounds(70, 202, 350, 20);
        info.setFont(info.getFont().deriveFont(Font.ITALIC, 10));
        
        add(progressBar);
        progressBar.setBounds(65, 220, 370, 5);
        progressBar.setBackground(new Color(0, 0, 0, 0f));
        progressBar.setForeground(Color.WHITE);
        progressBar.setBorder(null);
        progressBar.setBorderPainted(false);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    public void setInfoText(String text){
        info.setText(text);
    }
    
    public void setValue(int value){
        progressBar.setValue(value);
    }
    
}
