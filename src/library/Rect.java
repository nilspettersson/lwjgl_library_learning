package library;

import org.joml.Vector3f;
import org.joml.Vector4f;

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
	
	
	private float height;
	private float width;
	
	public Rect(Vector3f position,float width,float height,Vector4f color) {
		super(color);
		this.width=width;
		this.height=height;
		translate(position,width,height);
		
		setModel(createModel());
		setTexture(createTexture());
	}
	

	@Override
	public Model createModel() {
		return model;
	}
	


	@Override
	public Texture createTexture() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
