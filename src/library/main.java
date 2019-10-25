package library;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.GL;
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
		
		
		Texture text=new Texture(""+0, 200, 200, new Font("", Font.ITALIC, 20));
		
		
		
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
		
		for(int i=0;i<1;i++) {
			rect.add(new Rect(new Vector3f((float)(Math.random()*0),-(float)(Math.random()*0),0),10, 10, new Vector4f(1f,1f,1f,1f)));
		}
		
		
		Shader s=new Shader("shaderSimple");
		
		Lights lights=new Lights();
		for(int i=0;i<100;i++) {
			lights.addLight((float)(Math.random()*600), (float)(Math.random()*600));
		}
		
		
		while(!glfwWindowShouldClose(win.getWindow())) {
			Window.drawInit();
			
			long first = System.nanoTime() /1000000;
			
			if(glfwGetKey(win.getWindow(), GLFW_KEY_ESCAPE)==GL_TRUE) {
				glfwSetWindowShouldClose(win.getWindow(), true);
			}
			if(glfwGetMouseButton(win.getWindow(), 0)==GL_TRUE) {
				System.out.println("click");
			}
			
			
			
			s.bind();
			s.setUniform("color", new Vector4f(1,1,0,1));
			for(int i=0;i<rect.size();i++) {
				//rect.get(i).translate(new Vector3f(1920/4, -1080/4, 0));
				rect.get(i).render(camera,s);
			}
			
			
			//shader.bind();
			
			
			//Y = (X-A)/(B-A) * (D-C) + C
			float xx=(float)getMouse().getX();
			float yy=(float)getMouse().getY();
			
			float xxx=(xx-0)/(1920-0) * (1.777f-(0));
			float yyy=(yy-0)/(1080-0) * (-1-0)+1;
			
			//shader.setUniform("x",xxx);
			//shader.setUniform("y",yyy);
			
			//model.render();
			
			
			//camera.setPosition(new Vector3f(-xx,yy,0));
			
			
			
			
			lights.render(camera);
			
			
			
			
			textShader.bind();
			textShader.setUniform("sampler", 0);
			textShader.setUniform("projection", camera.getProjection());
			
			text=new Texture(""+myFps, 100, 100, new Font("", Font.ITALIC, 20));
			text.bind(0);
			
			model2.render();
			
			
			
			
			
			
			
			win.swapBuffers();
			
			long last = System.nanoTime()/1000000;
			update(first,last);
		}
		glfwTerminate();
		
	}
	
	
	static int myFps=0;
	public static void update(long f,long l) {
		
		int fps=120*2;
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
