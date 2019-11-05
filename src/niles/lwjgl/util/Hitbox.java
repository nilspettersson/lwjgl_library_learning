package niles.lwjgl.util;

public class Hitbox {
	
	private float x;
	private float y;
	private float width;
	private float height;
	
	
	public Hitbox(float x, float y, float width, float height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
	public boolean intersect(Hitbox hitbox) {
		if(x<hitbox.x+hitbox.width && y<hitbox.y+hitbox.height) {
			if(x+width>hitbox.x && y+height>hitbox.y) {
				return true;
			}
		}
		return false;
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}


	public float getWidth() {
		return width;
	}


	public void setWidth(float width) {
		this.width = width;
	}


	public float getHeight() {
		return height;
	}


	public void setHeight(float height) {
		this.height = height;
	}
	
	
	
	

}
