package library;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.WGL;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWVidMode;

public class main {

	public static void main(String[] args) {
		Window win=new Window(1920, 1080);
		
		
		
		/*float[] vertices=new float[] {
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
		*/
		
		
		//Texture text=new Texture(""+0, 200, 200, new Font("", Font.ITALIC, 20));
		
		
		
		float[] vertices2=new float[] {
				-0.5f,0.5f,0,
				0.5f,0.5f,0,
				0.5f,-0.5f,0,
				-0.5f,-0.5f,0
				
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
		
		
		
		Camera camera=new Camera(1920, 1080);
		
		ArrayList<Geometry>rect=new ArrayList<Geometry>();
		
		for(int i=0;i<20;i++) {
			rect.add(new Rect(new Vector3f((float)(Math.random()*1000)-500,-(float)(Math.random()*1000)+500,0),60, 60, new Vector4f(1f,0f,0f,1f)));
		}
		
		
		Shader s=new Shader("shaderSimple");
		
		Lights lights=new Lights();
		for(int i=0;i<1;i++) {
			lights.addLight((float)(Math.random()*500)-250, (float)(Math.random()*500)-250);
		}
		
		for(int i=0;i<rect.size();i++) {
			lights.getShadows().shadowFromGeometry(rect.get(i),camera);
		}
		
		
		
		lights.particleSystemInit(1000, 1, 0, 0, 0, 0,4000);
		lights.setStartVel(0, 0, 0f, 0f);
		
		
		//glfwSetInputMode(win.getWindow(), GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
		//glfwSetInputMode(win.getWindow(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		//glfwSetInputMode(win.getWindow(), GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		
		boolean down=false;
		
		
		
		
		
		
		 boolean mouseLocked = false;
	        double newX = 1920/2;
	        double newY = 1080/2;

	        double prevX = 0;
	        double prevY = 0;

	        boolean rotX = false;
	        boolean rotY = false;
	        
	        float myX=0;
	        float myY=0;

	        glfwSetInputMode(win.getWindow(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
		
		
		
		glfwSwapInterval(0);
		while(!glfwWindowShouldClose(win.getWindow())) {
			Window.drawInit(new Vector4f(0.5f, 0.5f, 0.5f, 1));
			
			long first = System.nanoTime() /1000000;
			
			if(glfwGetKey(win.getWindow(), GLFW_KEY_ESCAPE)==GL_TRUE) {
				glfwSetWindowShouldClose(win.getWindow(), true);
			}
			if(glfwGetMouseButton(win.getWindow(), 0)==GL_TRUE) {
				//System.out.println("click");
			}
			
			
			
			//s.bind();
			//s.setUniform("color", new Vector4f(1,1,0,1));
			
			
			
			
			
			
			
			
                DoubleBuffer x = BufferUtils.createDoubleBuffer(1);
                DoubleBuffer y = BufferUtils.createDoubleBuffer(1);

                glfwGetCursorPos(win.getWindow(), x, y);
                x.rewind();
                y.rewind();

                newX = x.get();
                newY = y.get();

                double deltaX = newX - 1920/2;
                double deltaY = newY - 1080/2;

                rotX = newX != prevX;
                rotY = newY != prevY;


                prevX = newX;
                prevY = newY;


                
                myX+=deltaX;
                myY+=deltaY;

                glfwSetCursorPos(win.getWindow(), 1920/2, 1080/2);
			
			
			
                
                
			
			//shader.bind();
			
			
			//Y = (X-A)/(B-A) * (D-C) + C
			/*float xx=(float)getMouse().getX();
			float yy=(float)getMouse().getY();
			
			float xxx=(xx-0)/(1920-0) * (1.777f-(0));
			float yyy=(yy-0)/(1080-0) * (-1-0)+1;*/
			
			//shader.setUniform("x",xxx);
			//shader.setUniform("y",yyy);
			
			//model.render();
			
			
			camera.setPosition(new Vector3f(-(float)myX,(float)myY,0));
			
			
			
			lights.setParticleX(myX);
			lights.setParticleY(-myY);
			
			//lights.addForce(0f, 0.01f);
			
			if(glfwGetMouseButton(win.getWindow(), 0)==1 && down==false) {
				down=true;
				lights.particleAdd();
			}
			else if(glfwGetMouseButton(win.getWindow(), 0)==0) {
				down=false;
			}
			
			
			if(lights.getLights().size()>0) {
				lights.translate(0,(float)myX+1920/2,-(float)myY);
			}
			
			lights.particleUpdate();
			lights.render(camera);
			
			
			
			
			
			
			for(int i=0;i<rect.size();i++) {
				//rect.get(i).translate(new Vector3f(100,0,0),100,100);
				rect.get(i).render(camera);
			}
			
			
			
			win.swapBuffers();
			
			long last = System.nanoTime()/1000000;
			update(first,last);
		}
		glfwTerminate();
		
	}
	
	
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
		myFps=(int) cap;
		System.out.println(cap);
	}
	else if(dif>=(1.0/fps)*1000) {
		dif=(1000/dif);
		myFps=(int) dif;
		System.out.println(dif);
	}
}
	
	
	
	
	public static Point getMouse() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		return b;
	}
	

}
