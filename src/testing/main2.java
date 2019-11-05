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
		
		Entity rect=new Rect(new Vector3f(0,0, 0), 200, 200, "res/wood_planks_old_0087_01.jpg");
		Entity rect2=new Rect(new Vector3f(100,0, 0), 200, 200, "res/wood_planks_old_0087_01.jpg");
		
		
		Lights light=new Lights(win,1f,Lights.REMOVE_LIGHT);
		
		light.addLight(0, 0, 0.6f);
		
		while(win.shouldClose()) {
			win.drawInit(new Vector4f(1f,1f,1f,1));
			//light.getShadows().shadowFromGeometry(rect);
			
			
			rect2.render(camera);
			rect.render(camera);
			if(rect.getHitbox().intersect(rect2.getHitbox())) {
				System.out.println("hit");
			}
			
			if(win.getInput().isDown(GLFW_KEY_A)) {
				rect.move(-0.1f, 0);
			}
			if(win.getInput().isDown(GLFW_KEY_D)) {
				rect.move(0.1f, 0);
			}
			if(win.getInput().isDown(GLFW_KEY_W)) {
				rect.move(0, 0.1f);
			}
			if(win.getInput().isDown(GLFW_KEY_S)) {
				rect.move(0, -0.1f);
			}
			
			light.render(camera);
			light.getShadows().clearShadows();
			win.clean();
		}
		
	}

}
