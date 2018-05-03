package root;


import org.jnativehook.mouse.NativeMouseEvent;
import static org.jnativehook.mouse.NativeMouseEvent.BUTTON1;
import static org.jnativehook.mouse.NativeMouseEvent.BUTTON2;
import static org.jnativehook.mouse.NativeMouseEvent.BUTTON3;
import org.jnativehook.mouse.NativeMouseInputListener;

public class GlobalMouseListener implements NativeMouseInputListener {
    
	public void nativeMouseClicked(NativeMouseEvent e) {
		//System.out.println("Mouse Clicked: " + e.getClickCount());
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		//System.out.println("Mouse Pressed: " + e.getButton());
                if(e.getButton() == BUTTON1){
                RecoilCompensator.mousePressed = true;
                }
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
                if(e.getButton() == BUTTON1){
                RecoilCompensator.mousePressed = false;
                }
		//System.out.println("Mouse Released: " + e.getButton());
                if(e.getButton() == BUTTON3){
                RecoilCompensator.isToggledOn = !RecoilCompensator.isToggledOn;
                }
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		//System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		//System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
	}
        
        public void keyPressed(){
        
        }
}