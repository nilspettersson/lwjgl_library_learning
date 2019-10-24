package library;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

public abstract class Geometry {
	private Model model;
	private Shader shader;
	private Texture texture;
	
	
	private Matrix4f position=new Matrix4f(); 
	
	public Geometry() {
		
	}
	
	public abstract Model createModel();
	public abstract Shader createShader();
	public abstract Texture createTexture();
	
	public abstract void setColor(Vector4f color);
	
	public void translate(Vector3f vec,int scale) {
		position=new Matrix4f();
		position.translate(vec).scale(scale);
	}
	
	public void init() {
		model=createModel();
		shader=createShader();
		texture=createTexture();
	}
	
	public void render(Camera camera) {
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

	public Shader getShader() {
		return shader;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
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
