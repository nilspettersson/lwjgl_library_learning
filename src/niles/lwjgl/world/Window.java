package niles.lwjgl.world;

import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.*;

import org.joml.Vector4f;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

public class Window {
	
	private long window;
	
	private int width;
	private int height;
	
	
	private Input input;
	
	public Window(int width,int height, boolean fullScreen) {
		this.width=width;
		this.height=height;
		
		if(!glfwInit()) {
			throw new IllegalStateException("Failed ti initialze GLFW!");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		if(fullScreen) {
			window=glfwCreateWindow(width, height, "", glfwGetPrimaryMonitor(), 0);
		}
		else {
			window=glfwCreateWindow(width, height, "", 0, 0);
			
			
			GLFWVidMode videoMode=glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(window, (videoMode.width()-width)/2, (videoMode.height()-height)/2);
		}
		
		
		
		
		if(window==0) {
			throw new IllegalStateException("failed to create window");
		}
		
		
		
		
		
		glfwShowWindow(window);
		
		
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		
		glEnable(GL_TEXTURE_2D);
		
		
		
		input=new Input(this);
		
	}
	
	
	public void swapBuffers() {
		glfwSwapBuffers(window);
		
		
		input.update();
		
		if(input.isDown(GLFW_KEY_F12)) {
			glfwSetWindowShouldClose(getWindow(), true);
		}
	}
	
	
	public boolean shouldClose(){
		return !glfwWindowShouldClose(getWindow());
	}
	
	
	
	
	
	public Input getInput() {
		return input;
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
	
	
	
	public void drawInit(Vector4f clearColor) {
		glfwPollEvents();
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(clearColor.x, clearColor.y, clearColor.z, clearColor.w);
		glClear(GL_COLOR_BUFFER_BIT);
		
		
	}
	
	

}
