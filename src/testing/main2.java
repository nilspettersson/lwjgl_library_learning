package testing;

import static org.lwjgl.glfw.GLFW.*;
import org.joml.Vector3f;
import org.joml.Vector4f;
import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.light.Lights;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;

public class main2 {

	public static void main(String[] args) {
		Window win=new Window(1920/2, 1080/2, false);
		Camera camera=new Camera(1920, 1080);
		
		Entity rect=new Rect(new Vector3f(0, 0, 0), 100, 100, new Vector4f(1, 0, 0, 1));
		
		
		Lights light=new Lights(win);
		light.addLight(0, 0, 1f);
		light.setZ(1f);
		
		while(win.shouldClose()) {
			win.drawInit(new Vector4f(0.5f,0.5f,0.5f,1));
			light.getShadows().shadowFromGeometry(rect);
			
			light.render(camera);
			
			rect.render(camera);
			
			if(win.getInput().isDown(GLFW_KEY_A)) {
				rect.move(-0.05f, 0);
			}
			if(win.getInput().isDown(GLFW_KEY_D)) {
				rect.move(0.05f, 0);
			}
			if(win.getInput().isDown(GLFW_KEY_W)) {
				rect.move(0, 0.05f);
			}
			if(win.getInput().isDown(GLFW_KEY_S)) {
				rect.move(0, -0.05f);
			}
			
			light.getShadows().clearShadows();
			win.clean();
		}
		
	}

}
