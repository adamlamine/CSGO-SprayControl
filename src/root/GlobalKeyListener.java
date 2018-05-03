/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.awt.Color;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;



public class GlobalKeyListener implements NativeKeyListener {
    
        RecoilCompensator rc;
        static int selectedWeapon = 0;
        
   
	public void nativeKeyPressed(NativeKeyEvent e) {
                        //System.out.println(e.getKeyCode());
                
		if (e.getKeyCode() == 57416) { //UP
                    selectedWeapon++;
                    if(selectedWeapon > 1) selectedWeapon = 0;
                    rc.switchWeapon(selectedWeapon);
                    
                }
                
                if (e.getKeyCode() == 57424) { //DOWN
                    selectedWeapon--;
                    if(selectedWeapon < 0) selectedWeapon = 1;
                    rc.switchWeapon(selectedWeapon);
		}
                
                if (e.getKeyCode() == 57419) { //LEFT
                    
		}
                
                if (e.getKeyCode() == 57421) { //RIGHT
		}
                
		

                
        }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
    }
    
    public void setRecoilCompensator(RecoilCompensator rc){
        this.rc = rc;
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}