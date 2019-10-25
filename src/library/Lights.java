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
	
		
	}
	
	public void addLight(float x,float y) {
		lights.add(new Matrix4f().translate(new Vector3f((1920/2)+x,0+y,0)));
	}
	
	
	
	private Matrix4f position=new Matrix4f(); 
	
	
	private Vector4f[] getVecFromMatrix(Camera camera) {
		Vector4f[]vecArray=new Vector4f[lights.size()];
		for(int i=0;i<lights.size();i++) {
			Matrix4f send=camera.getProjection().mul(lights.get(i));
			Vector4f vector=new Vector4f(send.m30*1.77777777f,send.m31,send.m32,send.m33);
			vecArray[i]=vector;
		}
		return vecArray;
	}
	
	public void translate(int index,float x,float y) {
		lights.set(index, new Matrix4f());
		lights.get(index).translate(new Vector3f(x,y,0));
	}
	
	
	//m30=x.
	//m31=y.
	public void render(Camera camera) {
		//creates a camera that is has a position in the middle of the screen
		Camera tempCamera=new Camera(1920, 1080);
		tempCamera.setPosition(new Vector3f(-1920/2,1080/2,0));
		
		
		shader.bind();
		
		
		position=new Matrix4f();
		position.translate(new Vector3f(0,0,0)).scale(camera.getWidth(), camera.getHeight(), 0);
		shader.setUniform("projection", tempCamera.getProjection().mul(position));
		
		shader.setUniform("size", lights.size());
		
		translate(0,getMouse().x,-getMouse().y+1080/2);
		
		shader.setUniform("location",getVecFromMatrix(camera));//the location of the lights for the shader.
		
		
		
		
		model.render();
		
	}
	
	public static Point getMouse() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		return b;
	}
	
	

}
