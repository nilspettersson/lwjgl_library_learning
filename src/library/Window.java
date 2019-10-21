package library;

import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.opengl.GL;

public class Window {
	
	private long window;
	
	private int width;
	private int height;
	
	public Window(int width,int height) {
		this.width=width;
		this.height=height;
		
		if(!glfwInit()) {
			throw new IllegalStateException("Failed ti initialze GLFW!");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		window=glfwCreateWindow(1980, 1080, "", glfwGetPrimaryMonitor(), 0);
		if(window==0) {
			throw new IllegalStateException("failed to create window");
		}
		
		
		//GLFWVidMode videoMode=glfwGetVideoMode(glfwGetPrimaryMonitor());
		//glfwSetWindowPos(window, (videoMode.width()-1920)/2, (videoMode.height()-1080)/2);
		
		
		glfwShowWindow(window);
		
		
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		
		glEnable(GL_TEXTURE_2D);
		
	}
	
	
	public void swapBuffers() {
		glfwSwapBuffers(window);
	}
	
	
	
	
	public long getWindow() {
		return window;
	}
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
	public static void drawInit() {
		glfwPollEvents();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	

}
