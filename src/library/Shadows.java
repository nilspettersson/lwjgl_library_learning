package library;

import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public class Shadows {
	
	private ArrayList<Matrix4f> shadowPoints;
	
	public Shadows() {
		shadowPoints=new ArrayList<>();
	}
	
	public void addShadowPoint(float x,float y) {
		shadowPoints.add(new Matrix4f().translate(new Vector3f((1920/2)+x,y,0)));
	}
	
	
	private Vector2f[] getVecFromMatrix(Camera camera) {
		Vector2f[]vecArray=new Vector2f[shadowPoints.size()];
		for(int i=0;i<shadowPoints.size();i++) {
			Matrix4f send=camera.getProjection().mul(shadowPoints.get(i));
			Vector2f vector=new Vector2f(send.m30*1.77777777f,send.m31);
			vecArray[i]=vector;
		}
		return vecArray;
	}
	
	
	public void render(Camera camera, Matrix4f position, Shader shader) {
		//creates a camera that is has a position in the middle of the screen
		Camera tempCamera=new Camera(1920, 1080);
		tempCamera.setPosition(new Vector3f(-1920/2,1080/2,0));
		
		
		
		//position=new Matrix4f();
		//position.translate(new Vector3f(0,0,0)).scale(camera.getWidth(), camera.getHeight(), 0);
		shader.setUniform("projection", tempCamera.getProjection().mul(position));
		
		shader.setUniform("shadowPointsSize", shadowPoints.size());
		shader.setUniform("shadowPoints",getVecFromMatrix(camera));//the location of the shadows for the shader.
		
		
		
	}
	

}