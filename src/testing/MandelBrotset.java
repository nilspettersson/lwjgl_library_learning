package testing;

import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.util.Shader;
import niles.lwjgl.util.ShaderRender;
import niles.lwjgl.world.Window;

public class MandelBrotset {

	public static void main(String[] args) {
		Window win=new Window(1920, 1080, true);
		
		Shader shader=new Shader("mandel");
		
		ShaderRender mandelbrot=new ShaderRender(shader);
		
		while(win.shouldUpdate()) {
			win.drawInit(new Vector4f(1));
			
			mandelbrot.render();
			
			win.clean();
		}
		
	}

}
