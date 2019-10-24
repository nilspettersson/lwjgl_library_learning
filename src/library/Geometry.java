package library;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public abstract class Geometry {
	private Model model;
	private Texture texture;
	
	
	private Matrix4f position=new Matrix4f(); 
	
	public Geometry() {
		
	}
	
	public abstract Model createModel();
	public abstract Texture createTexture();
	
	
	public void translate(Vector3f vec,float width,float height ) {
		position=new Matrix4f();
		position.translate(vec).scale(width, height, 0);
	}
	
	public void init() {
		model=createModel();
		texture=createTexture();
	}
	
	public void render(Camera camera, Shader shader) {
		shader.bind();
		
		
		
		shader.setUniform("projection", camera.getProjection().mul(position));
		model.render();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}


	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Matrix4f getPosition() {
		return position;
	}

	public void setPosition(Matrix4f position) {
		this.position = position;
	}
	
	

	
	
	
}
