package niles.lwjgl.util;

public class ShaderRender {
	
	private Shader shader;
	private static Model model;
	
	public ShaderRender(Shader shader) {
		this.shader=shader;
		model=Model.CreateModel();
	}
	
	public void render() {
		shader.bind();
		model.render();
	}

	public Shader getShader() {
		return shader;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}

	public static Model getModel() {
		return model;
	}

	public static void setModel(Model model) {
		ShaderRender.model = model;
	}
	
	

}
