package testing;

import org.joml.Vector3f;
import org.joml.Vector4f;

import static org.lwjgl.glfw.GLFW.*;
import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.util.Model;
import niles.lwjgl.util.Shader;
import niles.lwjgl.util.ShaderRender;
import niles.lwjgl.world.Window;

public class MandelBrotset {

	public static void main(String[] args) {
		Window win=new Window(1920, 1080, true);
		
		Shader shader=new Shader("mandel");
		Model mandelbrot=Model.CreateModel();
		
		float maxI=100;
		float scale=500;
		float xPos=0;
		float yPos=0;
		while(win.shouldUpdate()) {
			win.drawInit(new Vector4f(1));
			
			shader.bind();
			
			shader.setUniform("scale", scale);
			shader.setUniform("xPos", xPos);
			shader.setUniform("yPos", yPos);
			shader.setUniform("maxI", (int)maxI);
			
			mandelbrot.render();
			
			
			if(win.getInput().isDown(GLFW_KEY_UP)) {
				scale*=1.04;
			}
			if(win.getInput().isDown(GLFW_KEY_DOWN)) {
				scale/=1.04;
			}
			
			if(win.getInput().isDown(GLFW_KEY_W)) {
				yPos+=20/scale;
			}
			if(win.getInput().isDown(GLFW_KEY_S)) {
				yPos-=20/scale;
			}
			if(win.getInput().isDown(GLFW_KEY_A)) {
				xPos-=20/scale;
			}
			if(win.getInput().isDown(GLFW_KEY_D)) {
				xPos+=20/scale;
			}
			
			if(win.getInput().isDown(GLFW_KEY_LEFT)) {
				maxI+=1.04;
			}
			if(win.getInput().isDown(GLFW_KEY_RIGHT)) {
				maxI-=1.04;
			}
			
			
			win.clean();
			
			win.update(120);
			System.out.println(win.getFps());
		}
		
	}

}
