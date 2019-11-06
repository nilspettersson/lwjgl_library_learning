package testing;

import static org.lwjgl.glfw.GLFW.*;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.extra.Player;
import niles.lwjgl.light.Lights;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;

public class main2 {
	public static void main(String[] args) {
		Window win=new Window(1920/2, 1080/2, false);
		Camera camera=new Camera(1920, 1080);
		
		Player player=new Player(0, 100, 100, 100);
		Entity rect2=new Rect(new Vector3f(-800,-200, 0), 1600, 100, "res/wood_planks_old_0087_01.jpg");
		
		Lights light=new Lights(win,1f,Lights.REMOVE_LIGHT);
		
		light.addLight(0, 0, 0.6f);
		
		while(win.shouldClose()) {
			long start=win.getTime();
			
			win.drawInit(new Vector4f(1f,1f,1f,1));

			Vector2f overlap=player.getRect().getHitbox().intersectFixOverlap(rect2.getHitbox());
			player.getRect().move(overlap.x, overlap.y);
			
			
			rect2.render(camera);
			
			if(overlap.y!=0) {
				player.setYvel(0);
				
				if(win.getInput().isDown(GLFW_KEY_W)) {
					player.setYvel(0.2f);
				}
			}
			player.update(camera,-0.01f);
			
			if(win.getInput().isDown(GLFW_KEY_A)) {
				player.getRect().move(-0.1f, 0);
			}
			if(win.getInput().isDown(GLFW_KEY_D)) {
				player.getRect().move(0.1f, 0);
			}
			if(win.getInput().isDown(GLFW_KEY_S)) {
				//player.getRect().move(0, -0.1f);
			}
			
			light.render(camera);
			light.getShadows().clearShadows();
			win.clean();
			
			long end=win.getTime();
			win.update(start, end, 120);
			
			System.out.println(win.getFps());
		}
		
	}

}
