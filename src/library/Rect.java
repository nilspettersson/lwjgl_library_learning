package library;

import org.joml.Vector4f;

public class Rect extends Geometry {
	
	
	private Vector4f color;
	public Rect(float width,float height,Vector4f color) {
		super();
		this.color=color;
	}

	@Override
	public Model createModel() {
		float[] vertices=new float[] {
				-1f,1f,0,
				1f,1f,0,
				1f,-1f,0,
				-1f,-1f,0
				
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

	@Override
	public Shader createShader() {
		Shader shader=new Shader("shaderSimple.fs");
		shader.setUniform("color", color);
		return shader;
	}

	@Override
	public Texture createTexture() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
