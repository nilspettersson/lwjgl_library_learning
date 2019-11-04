package niles.lwjgl.examples;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.WGL;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.light.Lights;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Shader;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Input;
import niles.lwjgl.world.MouseCursor;
import niles.lwjgl.world.Window;

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
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWVidMode;

public class main {

	public static void main(String[] args) {
		Window win=new Window(1920, 1080,true);
		
		
		
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
		
		
		Shader textShader=new Shader("text");
		
		
		
		Camera camera=new Camera(1920, 1080);
		
		ArrayList<Entity>rect=new ArrayList<Entity>();
		
		for(int i=0;i<10;i++) {
			rect.add(new Rect(new Vector3f((float)(Math.random()*1000)-500,-(float)(Math.random()*1000)+500,0),60, 60, new Vector4f(1f,0f,0f,1f)));
		}
		
		
		Shader s=new Shader("shaderSimple");
		
		Lights lights=new Lights(win);
		for(int i=0;i<1;i++) {
			lights.addLight((float)(Math.random()*500)-250, (float)(Math.random()*500)-250,1f);
		}
		
		for(int i=0;i<rect.size();i++) {
			lights.getShadows().shadowFromGeometry(rect.get(i));
		}
		
		
		
		//lights.particleSystemInit(10, 1, 0, 0, 0, 0,11,0.01f);
		//lights.setStartVel(0, 0f, 40f, 40f);
		lights.setZ(1f);
		
		
		
		boolean down=false;
		
		
		
		
		
		
	    MouseCursor m=new MouseCursor();
	        
		m.setMouseVisible(win, false);
		
		glfwSwapInterval(0);
		while(!glfwWindowShouldClose(win.getWindow())) {
			win.drawInit(new Vector4f(0.8f, 0.8f, 0.8f, 1));
			
			long first = System.nanoTime() /1000000;
			
			if(glfwGetKey(win.getWindow(), GLFW_KEY_ESCAPE)==GL_TRUE) {
				glfwSetWindowShouldClose(win.getWindow(), true);
			}
			if(glfwGetMouseButton(win.getWindow(), 0)==GL_TRUE) {
				//System.out.println("click");
			}
			
			
			
			
			if(win.getInput().isReleased(GLFW_KEY_ENTER)) {
				System.out.println("button clicked");
			}
			 

			glfwSetScrollCallback(win.getWindow(), new GLFWScrollCallback() {
			    @Override public void invoke (long win, double dx, double dy) {
			        if(lights.getZ()>0.06 || dy<0) {
			        	//lights.setZ(lights.getZ()+(float)-dy/40);
			        	LightAdd((float) dy/10);
			        	
			        }
			    }
			});
			
			
			
			
			lightSpeed/=1.05;
			if(lights.getZ()>0.06 || lightSpeed<0) {
	        	lights.setZ(lights.getZ()+(float)-lightSpeed/40);
	        	
	        }
			
			
			
			
			
			m.moveCamera(win,camera,0.8f);
			
			
			
			lights.setParticleX(m.getX());
			lights.setParticleY(-m.getY());
			
			
			
			if(glfwGetMouseButton(win.getWindow(), 0)==1 && down==false) {
				down=true;
				lights.addLight(m.getX(), -m.getY(), 1f);
			}
			else if(glfwGetMouseButton(win.getWindow(), 0)==0) {
				down=false;
			}
			
			
			if(lights.getLights().size()>0) {
				lights.translate(0,(float)m.getX()+1920/2,-(float)m.getY());
			}
			
			
			
			
			
			//lights.particleUpdate();
			//lights.particleAdd();
			//lights.addForce(10f, 10f);
			lights.render(camera);
			
			
			
			for(int i=0;i<rect.size();i++) {
				//rect.get(i).translate(new Vector3f(100,0,0),100,100);
				rect.get(i).render(camera);
			}
			
			
			
			
			
			
			
			
			win.clean();
			
			long last = System.nanoTime()/1000000;
			update(first,last);
		}
		glfwTerminate();
		
	}
	
	static float lightSpeed=1;
    
    public static void LightAdd(float x) {
    	lightSpeed+=x;
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
		//System.out.println(cap);
	}
	else if(dif>=(1.0/fps)*1000) {
		dif=(1000/dif);
		myFps=(int) dif;
		//System.out.println(dif);
	}
}
	
	
	
	
	public static Point getMouse() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		return b;
	}
	

}
