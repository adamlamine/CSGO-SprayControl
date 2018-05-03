/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.TimerTask;

/**
 *
 * @author Adam Lamine
 */
public class RecoilCompensator extends TimerTask {
    
    
    public int[] ak47X = { -4, 4, -3, -1, 13, 8, 13, -15, -35, -17, 12, -15, -26, -3, 25, 19, 14, 27, 33, -29, 7, -7, -8, 19, 5, -24, -38, -30, -17};//{ -4, 4, -3, -1, 13, 8, 13, -15, -35, -18, 8, -15, -20, -1, 28, 40, 13, 27, 12, -28, 5, -26 /*22*/, 28, 18, 15, -39, -38, -20, -17};
    public int[] ak47Y = { 10, 20, 29, 31, 31, 28, 21, 12, -3, 2, 11, 7, -8, 4, 1, 7, 10, 0, -10, -2, 3, 9, 4, -3, 6, -1, -4, -13, 1};//{ 10, 20, 29, 31, 31, 28, 21, 12, -3, 2, 11, 7, -8, 4, 1, 9, 15, 5, -10, -2, 3, 9, 4, -3, 6, -1, -4, -13, 1};
    public int ak47Delay = 94;
    public String ak47Name = "AK47";
    
    public int[] m4a1X = { 2, 0, -3, 4, -12, -7, 16, 11, 22, -4, -13, -16, -30, -25, -2, 8, -11, -13, 2, 15, 19, 27, 11, 12, -10, 7, 5, 4, 5};
    public int[] m4a1Y = { 7, 9, 11, 15, 23, 27, 15, 13, 5, 11, 6, -4, 0, -6, 4, -4, 1, -2, 2, 3, 12, 9, 7, 6, 8, 7, 7, 2, 1};
    public int m4a1Delay = 81;
    public String m4a1Name = "M4A1";
    
    public int[] patternX = m4a1X;  //{ -4, 4, -3, -1, 13, 8, 13, -15, -35, -17, 12, -15, -26, -3, 25, 19, 14, 27, 33, -29, 7, -7, -8, 19, 5, -24, -38, -30, -17};
    public int[] patternY = m4a1Y;  //{ 10, 20, 29, 31, 31, 28, 21, 12, -3, 2, 11, 7, -8, 4, 1, 7, 10, 0, -10, -2, 3, 9, 4, -3, 6, -1, -4, -13, 1};
    
    
    
    static public int delay = 81; //delay zwischen Schüssen //89
    static double rangeMultiplier = 9;      //8.62
    
    
    public int callCounter = 0;
    
    public static String weaponName = "AK47";
    
    public int currentX = 0;
    public int currentY = 0;
    
    Robot robot;
    static int shotNr = 0;
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int) screenSize.getHeight();
    int width = (int) screenSize.getWidth();
    
    int mouseY = MouseInfo.getPointerInfo().getLocation().y;
    int mouseX = MouseInfo.getPointerInfo().getLocation().x;    
    
    
    
    static boolean mousePressed = false;
    public static boolean isToggledOn = false;
    static int distance = 40;
    
    OverlayWindow w;
    

    public void switchWeapon(int weaponID){
        
        switch(weaponID){
        
            case 0:                             //AK47
                patternX = ak47X;
                patternY = ak47Y;
                weaponName = ak47Name;
                delay = ak47Delay;
            break;
            
            case 1:                             //M4A1
                patternX = m4a1X;
                patternY = m4a1Y;
                weaponName = m4a1Name;
                delay = m4a1Delay;
            break;
            
            default:
                GlobalKeyListener.selectedWeapon = 0;
                patternX = ak47X;
                patternY = ak47Y;
                weaponName = ak47Name;
                delay = ak47Delay;
            break;
        
        }
        
    }
    
    RecoilCompensator(OverlayWindow w) throws AWTException{
        this.robot = new Robot();
        this.w = w;
        
    }
    
    public void setMousePos(){
        
        callCounter++;
        

        
        
        mouseY = MouseInfo.getPointerInfo().getLocation().y;
        mouseX = MouseInfo.getPointerInfo().getLocation().x;    
        
        if(mousePressed && shotNr < patternX.length && isToggledOn){
            
            int mouseTargetX = mouseX + (int)((patternX[shotNr]*rangeMultiplier)/delay);
            int mouseTargetY = mouseY + (int)((patternY[shotNr]*rangeMultiplier)/delay);
            
            if(callCounter == delay){       //um sicherzustellen, dass die Maus beim Schuss an der richtigen stelle ist, wird sie auf die Zielposition gesetzt, bevor der Schuss abgegeben wird
                //mouseTargetX = mouseX + (int)(patternX[shotNr]*rangeMultiplier);
                //mouseTargetY = mouseY + (int)(patternY[shotNr]*rangeMultiplier);
                
                
                shotNr++;
            }
            
            
            robot.mouseMove(mouseTargetX, mouseTargetY);
            
            
        }
        
        if(callCounter == delay){
            callCounter = 0;
        }

        if(!mousePressed){
            shotNr = 0;
            currentX = 0;
            currentY = 0;
        }
        
    }
    
    public void changeHUD(){
            w.setCircleWidth(distance);
            
            
            
            if(shotNr < patternX.length){ 
                
            currentX -= (patternX[shotNr]);
            currentY += (patternY[shotNr]);
                
            
            } else {
            w.setIndicatorCoordinates(0, 0);
            }
            
            w.setIndicatorCoordinates(currentX/(int)(delay*3.4), currentY/(int)(delay*3.4));
            
            
            w.repaint();
    }
    
    @Override
    public void run() {
        setMousePos();
        changeHUD();
        
        
        
        
    }
    
}
