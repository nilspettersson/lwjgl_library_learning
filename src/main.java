import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;

public class main {

	public static void main(String[] args) {
		if(!glfwInit()) {
			throw new IllegalStateException("Failed ti initialze GLFW!");
		}
		
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		long window=glfwCreateWindow(1980, 1080, "testing", glfwGetPrimaryMonitor(), 0);
		if(window==0) {
			throw new IllegalStateException("failed to create window");
		}
		
		//GLFWVidMode videoMode=glfwGetVideoMode(glfwGetPrimaryMonitor());
		//glfwSetWindowPos(window, (videoMode.width()-1920)/2, (videoMode.height()-1080)/2);
		
		
		
		
		glfwShowWindow(window);
		
		
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		
		glEnable(GL_TEXTURE_2D);
		
		
		
		
		float[] vertices=new float[] {
				-1f,1f,0,
				1f,1f,0,
				1f,-1f,0,
				-1f,-1f,0
				
		};
		float[] texture=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		int[] indices=new int[] {
				0,1,2,
				2,3,0
		};
		
		
		
		Model model=new Model(vertices, texture,indices);
		Shader shader=new Shader("shader");
		
		
		
		Texture text=new Texture("this is my text", false);
		
		
		
		float[] vertices2=new float[] {
				-1f,1f,0,
				0f,1f,0,
				0f,0f,0,
				0f,0f,0
				
		};
		float[] texture2=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		int[] indices2=new int[] {
				0,1,2,
				2,3,0
		};
		
		
		
		Model model2=new Model(vertices2, texture2,indices2);
		Shader textShader=new Shader("text");
		
		
		
		
		while(!glfwWindowShouldClose(window)) {
			long first = System.nanoTime() /1000000;
			
			if(glfwGetKey(window, GLFW_KEY_ESCAPE)==GL_TRUE) {
				glfwSetWindowShouldClose(window, true);
			}
			if(glfwGetMouseButton(window, 0)==GL_TRUE) {
				System.out.println("asdasdasd");
			}
			glfwPollEvents();
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			
			
			//glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
			
			
			/*shader2.bind();
			model2.render();*/
			
			
			
			
			shader.bind();
			
			
			//Y = (X-A)/(B-A) * (D-C) + C
			float xx=(float)getMouse().getX();
			float yy=(float)getMouse().getY();
			
			xx=(xx-0)/(1920-0) * (1.777f-(0));
			yy=(yy-0)/(1080-0) * (-1-0)+1;
			
			System.out.println(xx+"    "+yy);
			
			shader.setUniform("x",xx);
			shader.setUniform("y",yy);
			
			shader.setUniform("lsx1",0.2f);
			shader.setUniform("lsy1",0.2f);
			shader.setUniform("lex1",0.8f);
			shader.setUniform("ley1",0.8f);
			
			
			model.render();
			
			
			textShader.bind();
			textShader.setUniform("sampler", 0);
			
			if(i==10) {
				text=new Texture(""+total/10, false);
				i=0;
				total=0;
			}
			else {
				total+=myFps;
			}
			i++;
			text.bind(0);
			
			model2.render();
			
			
			glfwSwapBuffers(window);
			
			long last = System.nanoTime()/1000000;
			update(first,last);
		}
		glfwTerminate();
		
	}
	static int i=0;
	static int total=0;
	
	
	static int myFps=0;
	public static void update(long f,long l) {
		
		int fps=120;
		//setting up the fps cap.
	double cap = (1.0/fps)*1000;
	
	//finding the difference in time. cap-difference. 
	double dif=l-f;
	//if the difference is to much then don't do more. if more then error in Thread.sleep.
	if(dif>=cap){
	cap-=dif;
	}
	
	
	if(cap<=0){
		cap=1;
	}
	try {
		Thread.sleep((long) cap);
	}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	// checking fps
	if(dif<(1.0/fps)*1000) {
		cap=(1000/cap);
		System.out.println(cap);
		myFps=(int) cap;
	}
	else if(dif>=(1.0/fps)*1000) {
		dif=(1000/dif);
		System.out.println(dif);
		myFps=(int) dif;
	}
}
	
	
	
	
	public static Point getMouse() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		return b;
	}
	

}
