package niles.lwjgl.world;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class KeyEvent {
	private Window window;
	
	public KeyEvent(Window window) {
		this.window=window;
	}
	
	
	public boolean isPressed(int GLFW_KEY) {
		if(glfwGetKey(window.getWindow(), GLFW_KEY)==GL_TRUE) {
			return true;
		}
		return false;
	}
	
	public boolean isDown(int GLFW_KEY) {
		if(glfwGetKey(window.getWindow(), GLFW_KEY)==GL_TRUE) {
			return true;
		}
		return false;
	}

}
