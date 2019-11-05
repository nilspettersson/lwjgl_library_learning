package niles.lwjgl.light;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.util.ArrayList;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.util.Model;
import niles.lwjgl.util.Shader;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;

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
	
	public static int REMOVE_LIGHT=0;
	public static int ADD_LIGHT=1;
	private int LightType;
	
	
	
	private Model model;
	
	
	private ArrayList<Particle> lights;
	private Shader shader;
	
	private Shadows shadows;
	
	private float z;
	private Vector3f ambient;
	
	
	private Window window;  //used in shader to get correct width and height
	
	public Lights(Window window,float z, int LightType) {
		lights=new ArrayList<Particle>();
		shader=new Shader("shaderLights");
		model=new Model(vertices, texture,indices);
		
	
		shadows=new Shadows();
		
		
		this.z=z;
		this.LightType=LightType;
		ambient=new Vector3f(0,0,0);
		
		
		this.window=window;
	}
	
	public void addLight(float x,float y,float intensity) {
		lights.add(new Particle(new Matrix4f().translate(new Vector3f((1920/2)+x,0+y,0)),intensity));
		
	}
	
	
	
	
	private int particleMax=1;
	private int particlesPerFrame=1;
	private float particleX=0;
	private float particleY=0;
	private float particleRandomX=0;
	private float particleRandomY=0;
	private float lifeTime=0;
	private float particleIntesity=0;
	
	private float particleXVel=0;
	private float particleYVel=0;
	private float particleRandomXVel=0;
	private float particleRandomYVel=0;
	public void particleSystemInit(int particleMax,int particlesPerFrame, float particleX,float particleY,float particleRandomX,float particleRandomY,float lifeTime, float particleIntesity) {
		this.particleMax=particleMax;
		this.particlesPerFrame=particlesPerFrame;
		this.particleX=particleX;
		this.particleY=particleY;
		this.particleRandomX=particleRandomX;
		this.particleRandomY=particleRandomY;
		this.lifeTime=lifeTime;
		this.particleIntesity=particleIntesity;
		
		
	}
	public void setStartVel(float particleXVel,float particleYVel,float particleRandomXVel,float particleRandomYVel) {
		this.particleXVel=particleXVel;
		this.particleYVel=particleYVel;
		this.particleRandomXVel=particleRandomXVel;
		this.particleRandomYVel=particleRandomYVel;
	}
	public float getParticleX() {
		return particleX;
	}

	public void setParticleX(float particleX) {
		this.particleX = particleX;
	}

	public float getParticleY() {
		return particleY;
	}

	public void setParticleY(float particleY) {
		this.particleY = particleY;
	}

	
	
	public void particleUpdate() {
		for(int i=0;i<lights.size();i++) {
			translate(i, lights.get(i).position.m30+lights.get(i).getxVel(), lights.get(i).position.m31+lights.get(i).getyVel());
			lights.get(i).setLifeTime(lights.get(i).getLifeTime()-1);
		}
		for(int i=0;i<lights.size();i++) {
			if(lights.get(i).getLifeTime()<=0) {
				lights.remove(i);
			}
		}
		
	}
	public void particleAdd() {
		if(lights.size()<particleMax-particlesPerFrame) {
			for(int i=0;i<particlesPerFrame;i++) {
				addLight(particleX+(float)(Math.random()*particleRandomX)-particleRandomX/2, particleY+(float)(Math.random()*particleRandomY)-particleRandomY/2,particleIntesity);
				lights.get(lights.size()-1).setLifeTime(lifeTime);
				lights.get(lights.size()-1).setxVel(particleXVel+(float)(Math.random()*particleRandomXVel)-particleRandomXVel/2);
				lights.get(lights.size()-1).setyVel(particleYVel+(float)(Math.random()*particleRandomYVel)-particleRandomYVel/2);
			}
		}
	}
	
	public void addForce(float x,float y) {
		for(int i=0;i<lights.size();i++) {
			lights.get(i).addForce(x, y);
		}
	}

	public void setIntensity(float intensity) {
		for(int i=0;i<lights.size();i++) {
			lights.get(i).setIntensity(intensity);
		}
	}
	
	
	
	
	
	
	private Matrix4f position=new Matrix4f(); 
	
	
	private Vector4f[] getVecFromMatrix(Camera camera) {
		Vector4f[]vecArray=new Vector4f[lights.size()];
		for(int i=0;i<lights.size();i++) {
			Matrix4f send=camera.getProjection().mul(lights.get(i).position);
			
			Vector4f vector=new Vector4f(send.m30*1.77777777f,send.m31,z,lights.get(i).getIntensity());//x  y  z  intensity
			vecArray[i]=vector;
		}
		return vecArray;
	}
	
	public void translate(int index,float x,float y) {
		lights.get(index).position=new Matrix4f();
		lights.get(index).position.translate(new Vector3f(x,y,0));
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
		shader.setUniform("ambient", ambient);
		shader.setUniform("type", LightType); //this is the type of light.
		
		
		shader.setUniform("scale", new Vector2f(window.getWidth(),window.getHeight()));
		
		
		//translate(0,getMouse().x,-getMouse().y+1080/2);
		
		shader.setUniform("location",getVecFromMatrix(camera));//the location of the lights for the shader.
		
		
		shadows.render(camera, position, shader);
		
		
		
		model.render();
		
	}
	
	public static Point getMouse() {
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
		return b;
	}

	
	
	
	
	
	public Shadows getShadows() {
		return shadows;
	}

	public void setShadows(Shadows shadows) {
		this.shadows = shadows;
	}

	public ArrayList<Particle> getLights() {
		return lights;
	}

	public void setLights(ArrayList<Particle> lights) {
		this.lights = lights;
	}
	
	
	

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public Vector3f getAmbient() {
		return ambient;
	}

	public void setAmbient(Vector3f ambient) {
		this.ambient = ambient;
	}
	
	
	

}
