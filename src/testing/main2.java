package testing;

import static org.lwjgl.glfw.GLFW.*;

import java.util.ArrayList;

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
		Window win=new Window(1920, 1080, true);
		Camera camera=new Camera(1920, 1080);
		
		Player player=new Player(0, 100, 100, 100);
		ArrayList<Entity>ob=new ArrayList<Entity>();
		ob.add(new Rect(new Vector3f(-300,100, 0), 800, 100, "res/wood_planks_old_0087_01.jpg"));
		ob.add(new Rect(new Vector3f(-800,300, 0), 200, 600, "res/wood_planks_old_0087_01.jpg"));
		ob.add(new Rect(new Vector3f(-80000,-200, 0), 160000, 100, "res/wood_planks_old_0087_01.jpg"));
		
		Lights light=new Lights(win,1.2f,Lights.ADD_LIGHT);
		
		light.addLight(-700, 600, 0.6f);
		
		win.setVSync(false);
		while(win.shouldUpdate()) {
			long start=win.getTime();
			win.drawInit(new Vector4f(0.4f,0.6f,1f,1));
			
			
			
			for(int i=0;i<ob.size();i++) {
				ob.get(i).render(camera);
				light.getShadows().shadowFromGeometry(ob.get(i));
			}
			
			
			
			
			if(win.getInput().isPressed(GLFW_KEY_R)) {
				player.translate(100, 100);
			}
			
			
			boolean inAir=true;
			for(int i=0;i<ob.size();i++) {
				Vector2f overlapp=player.getRect().getHitbox().intersectFixOverlap(ob.get(i).getHitbox());
				player.move(overlapp.x, overlapp.y);
				
				if(overlapp.x!=0) {
					player.setXvel(0f);
				}
				
				if(overlapp.y!=0) {
					player.setYvel(0);
					
					if(overlapp.y>0) {
						if(win.getInput().isPressed(GLFW_KEY_W)) {
							player.setYvel(0.20f);
						}
						//player.setXvel(player.getXvel()/1.4f);
					}
					inAir=false;
				}
				
			}
			
				player.setXvel(player.getXvel()/1.1f);
			
			
			//light.translate(0, player.getRect().getX()+player.getRect().getWidth()/2+camera.getWidth()/2, player.getRect().getY());
			
			
			
			if(win.getInput().isDown(GLFW_KEY_A)) {
				if(player.getXvel()>-0.1f) {
					player.setXvel(player.getXvel()-0.01f);
				}
			}
			if(win.getInput().isDown(GLFW_KEY_D)) {
				if(player.getXvel()<0.1f) {
					player.setXvel(player.getXvel()+0.01f);
				}
			}
			
			
			camera.setPosition(new Vector3f(-player.getX(), -player.getY(), 0));
			
			
			player.update(camera,-0.006f);
			
			
			light.render(camera);
			
			for(int i=0;i<ob.size();i++) {
				ob.get(i).render(camera);
			}
			
			
			light.getShadows().clearShadows();
			win.clean();
			
			
			long end=win.getTime();
			win.update(120);
			
			System.out.println(win.getFps());
		}
		
	}

}
