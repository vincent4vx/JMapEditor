/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pvemu.mapeditor.ui;

import java.awt.Color;
import java.awt.Dimension;
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
import org.pvemu.mapeditor.common.Constants;

/**
 *
 * @author Vincent Quatrevieux <quatrevieux.vincent@gmail.com>
 */
public class SplashScreen extends JWindow{
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
            setPreferredSize(new Dimension(440, 286));
            bg = ImageIO.read(new File(Constants.SPLASH_IMG));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bg, 0, 0, this);
        }
        
    }

    public SplashScreen() throws HeadlessException, IOException {
        JPanel panel = new SplashPanel();
        setContentPane(panel);
        
        info.setText("chargement...");
        add(info);
        info.setForeground(Color.WHITE);
        info.setBounds(70, 200, 350, 20);
        
        add(progressBar);
        progressBar.setBounds(65, 220, 375, 5);
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
