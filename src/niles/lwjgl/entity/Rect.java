package niles.lwjgl.entity;

import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.util.Model;
import niles.lwjgl.util.Texture;

public class Rect extends Entity {
	
	private static float[] vertices=new float[] {
			0f,0f,0,
			1f,0f,0,
			1f,-1f,0,
			0f,-1f,0
			
	};
	private static float[] tex_coords=new float[] {
			0,0,
			1,0,
			1,1,
			0,1
	};
	private static int[] indices=new int[] {
			0,1,2,
			2,3,0
	};
	static Model model=new Model(vertices, tex_coords, indices);
	static Texture texture;
	
	private float height;
	private float width;
	
	public Rect(Vector3f position,float width,float height,Vector4f color) {
		super(color);
		this.width=width;
		this.height=height;
		translate(position,width,height);
		
		setModel(createModel());
		//setTexture(createTexture());
	}
	public Rect(Vector3f position,float width,float height,String textureName) {
		super(new Vector4f(0, 0, 0, 0));
		this.width=width;
		this.height=height;
		translate(position,width,height);
		
		setModel(createModel());
		setTexture(createTexture(textureName));
	}
	

	@Override
	public Model createModel() {
		return model;
	}
	


	@Override
	public Texture createTexture(String textureName) {
		// TODO Auto-generated method stub
		return new Texture(textureName);
	}



	

}
