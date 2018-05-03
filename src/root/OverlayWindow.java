/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Adam Lamine
 */
public class OverlayWindow extends JPanel {
    
    JFrame frame;
    JLabel shotLabel;
    JLabel delayLabel;
    JLabel sensLabel;
    JLabel weaponLabel;
   
    private int circleWidth = 40;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private int screenWidth = dim.width;
    private int screenHeight = dim.height;
    
    private int indicatorX = screenWidth/2;
    private int indicatorY = screenHeight/2;
    private int indicatorSize = 8;
    
    OverlayWindow(){
        frame = new JFrame();
        shotLabel = new JLabel();
        delayLabel = new JLabel();
        sensLabel = new JLabel();
        weaponLabel = new JLabel();
        
        frame.setUndecorated(true);
        frame.setBackground(new Color(0f, 0f, 0f, 0f));
        this.setBackground(new Color(0, 0, 0, 0));
        this.setOpaque(false);
        frame.setSize(400, 400);
        this.setSize(400,400);
        frame.setLocationRelativeTo(null);
        //frame.setLocation(screenWidth/2-frame.getSize().width/2, screenHeight/2-frame.getSize().height/2);
        
        frame.setAlwaysOnTop(true);
        
        frame.setVisible(true);
        frame.add(this);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.add(shotLabel);
        this.add(delayLabel);
        this.add(sensLabel);
        shotLabel.setLocation(this.getWidth()/2 - circleWidth/2, this.getHeight()/2 - circleWidth/2 + 20);
        delayLabel.setLocation(this.getWidth()/2 - circleWidth/2, this.getHeight()/2 - circleWidth/2 + 20 - 80);
        sensLabel.setLocation(this.getWidth()/2 - circleWidth/2, this.getHeight()/2 - circleWidth/2 + 20  - 120);
        weaponLabel.setLocation(this.getWidth()/2 - circleWidth/2, this.getHeight()/2 - 800);
        
        shotLabel.setForeground (Color.red);
        delayLabel.setForeground (Color.red);
        sensLabel.setForeground (Color.red);
        weaponLabel.setForeground (Color.red);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if(RecoilCompensator.isToggledOn){
        
        g.setColor(Color.GREEN);
        g.drawOval(this.getWidth()/2 - circleWidth/2, this.getHeight()/2 - circleWidth/2 + 20, circleWidth, circleWidth);
        
        if(indicatorY < this.getHeight()/2 - 10){
            g.fillOval(indicatorX, indicatorY, indicatorSize, indicatorSize);
            g.drawLine(indicatorX + indicatorSize/2, indicatorY, this.getWidth()/2, this.getHeight()/2);
        }
        
        //shotLabel.setText("shot: " + RecoilCompensator.shotNr);
        //delayLabel.setText("delay: " + RecoilCompensator.delay);
        //sensLabel.setText("sens: " + RecoilCompensator.rangeMultiplier);
        sensLabel.setText("Waffe: " + RecoilCompensator.weaponName);
        
        
        }
        
    }
    
    public void setIndicatorCoordinates(int x, int y){
        indicatorX = this.getWidth()/2 + x - indicatorSize/2;
        indicatorY = this.getWidth()/2 - y + circleWidth/2 - indicatorSize;
    }
    
    public int getCircleWidth(){
    return circleWidth;
    }
    
    public void setCircleWidth(int c){
    this.circleWidth = c;
    }
}
