package niles.lwjgl.extra;

import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.world.Camera;

public class Player {
	
	private float xvel;
	private float yvel;
	private Entity rect;
	public Player(float x, float y, float width, float height) {
		
		xvel=0;
		yvel=0;
		rect=new Rect(new Vector3f(x, y, 0), width, height, new Vector4f(1,1,1,1));
	}
	
	
	public void update(Camera camera,float gravity) {
		yvel+=gravity;
		
		rect.move(xvel, yvel);
		
		
		rect.render(camera);
		
	}
	
	public void move(float x,float y) {
		rect.move(x, y);
	}
	public void translate(float x,float y) {
		rect.translate(new Vector3f(x, y, 0), rect.getWidth(), rect.getHeight());
	}
	
	

	public float getX() {
		return rect.getX();
	}
	public float getY() {
		return rect.getY();
	}

	public float getXvel() {
		return xvel;
	}


	public void setXvel(float xvel) {
		this.xvel = xvel;
	}


	public float getYvel() {
		return yvel;
	}


	public void setYvel(float yvel) {
		this.yvel = yvel;
	}


	public Entity getRect() {
		return rect;
	}


	public void setRect(Entity rect) {
		this.rect = rect;
	}
	
	
	

}
