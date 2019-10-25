package library;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Lights {
	float[] vertices=new float[] {
			0f,0f,0,
			1f,0f,0,
			1f,-1f,0,
			0f,-1f,0
			
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
	
	
	
	private Model model;
	//private Matrix4f position=new Matrix4f(); 
	
	
	private ArrayList<Matrix4f> lights;
	private Shader shader;
	
	public Lights() {
		lights=new ArrayList<Matrix4f>();
		shader=new Shader("shaderLights");
		model=new Model(vertices, texture,indices);
	
		
		addLight(0, 0);
	}
	
	public void addLight(float x,float y) {
		lights.add(new Matrix4f().translate(new Vector3f(1920/2,0,0)));
	}
	
	
	
	private Matrix4f position=new Matrix4f(); 
	
	
	
	
	//m30=x.
	//m31=y.
	public void render(Camera camera) {
		//creates a camera that is has a position in the middle of the screen
		Camera tempCamera=new Camera(1920, 1080);
		tempCamera.setPosition(new Vector3f(-1920/2,1080/2,0));
		
		shader.bind();
		position=new Matrix4f();
		position.translate(new Vector3f(0,0,0)).scale(1920, 1080, 0);
		shader.setUniform("projection", tempCamera.getProjection().mul(position));
		
		//for(int i=0;i<lights.size();i++) {
			Matrix4f send=camera.getProjection().mul(lights.get(0));
			System.out.println("x="+send.m30+"  +y="+send.m31);
			shader.setUniform("location",new Vector4f(send.m30*1.77777777f,send.m31,send.m32,send.m33));
			
		//}
		
		
		//shader.setUniform("x",0);
		//shader.setUniform("y",0);
		
		
		model.render();
		
	}
	
	public static Point getMouse() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		return b;
	}
	
	

}
