package niles.lwjgl.light;

import org.joml.Matrix4f;

public class Particle {
	Matrix4f position;
	
	private float xVel;
	private float yVel;
	private float lifeTime;
	private float intensity;
	
	public Particle(Matrix4f matrix, float intensity) {
		this.intensity=intensity;
		
		position=matrix;
		xVel=0;
		yVel=0;
		lifeTime=100;
	}
	
	
	public void addForce(float x,float y) {
		xVel+=x;
		yVel+=y;
	}
	
	
	
	
	public float getLifeTime() {
		return lifeTime;
	}


	public void setLifeTime(float lifeTime) {
		this.lifeTime = lifeTime;
	}


	public float getxVel() {
		return xVel;
	}

	public void setxVel(float xVel) {
		this.xVel = xVel;
	}

	public float getyVel() {
		return yVel;
	}

	public void setyVel(float yVel) {
		this.yVel = yVel;
	}


	public float getIntensity() {
		return intensity;
	}


	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
	

}
