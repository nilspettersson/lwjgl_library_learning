package library;

import org.joml.Vector4f;

public class Rect extends Geometry {
	
	
	private Vector4f color=new Vector4f(0,0,0,0);
	private float height;
	private float width;
	
	public Rect(float width,float height,Vector4f color) {
		super();
		this.color=color;
		this.width=width;
		this.height=height;
	}

	@Override
	public Model createModel() {
		float[] vertices=new float[] {
				0f,0f,0,
				1f*width,0f,0,
				1f*width,-1f*height,0,
				0f,-1f*height,0
				
		};
		float[] tex_coords=new float[] {
				0,0,
				1,0,
				1,1,
				0,1
		};
		int[] indices=new int[] {
				0,1,2,
				2,3,0
		};
		
		Model model=new Model(vertices, tex_coords, indices);
		
		return model;
	}
	
	public void setColor(Vector4f color) {
		getShader().setUniform("color", color);
	}
	

	@Override
	public Shader createShader() {
		Shader shader=new Shader("shaderSimple");
		shader.bind();
		
		shader.setUniform("color", color);
		return shader;
	}

	@Override
	public Texture createTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
