/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.awt.AWTException;
import java.awt.Color;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

/**
 *
 * @author Adam Lamine
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AWTException {
        // TODO code application logic here
        OverlayWindow w = new OverlayWindow();
        RecoilCompensator rc = new RecoilCompensator(w);
        
        
        
        Timer timer = new Timer();
            timer.schedule(rc, 0, 1);
        
        
        
        		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		// Construct the example object.
		GlobalMouseListener gm = new GlobalMouseListener();
                GlobalKeyListener gk = new GlobalKeyListener();
                gk.setRecoilCompensator(rc);

		// Add the appropriate listeners.
		GlobalScreen.addNativeMouseListener(gm);
		GlobalScreen.addNativeMouseMotionListener(gm);
                GlobalScreen.addNativeKeyListener(gk);
                
                
                LogManager.getLogManager().reset();

                // Get the logger for "org.jnativehook" and set the level to off.
                Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                logger.setLevel(Level.OFF); 
    }
}
    

