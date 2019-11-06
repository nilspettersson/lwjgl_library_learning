package testing;
import java.util.ArrayList;
import org.joml.Vector3f;
import org.joml.Vector4f;
import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.extra.Grid;
import niles.lwjgl.extra.Player;
import niles.lwjgl.light.Lights;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;
import static org.lwjgl.glfw.GLFW.*;

public class snake {
	
	public static void main(String args[]) {
		Window win=new Window(1920/2, 1080/2, false);
		Camera camera=new Camera(1920, 1080);
		
		win.setVSync(false);
		
		Grid grid=new Grid(-camera.getWidth()/2+40,-camera.getHeight()/2+40,30, 30, 40);
		Entity food=new Rect(new Vector3f(grid.getCells().get((int)(Math.random()*grid.getCells().size())), 0), 40, 40, new Vector4f(1, 0, 0,1));
		
		Lights light=new Lights(win, 1f, Lights.ADD_LIGHT);
		light.addLight(0, 0, 0.1f);
		
		ArrayList<Player>snake=new ArrayList<>();
		
		boolean left=false;
		boolean right=true;
		boolean up=false;
		boolean down=false;
		boolean setup=true;
		while(win.shouldUpdate()) {
			if(setup) {
				snake.clear();
				for(int i=0;i<3;i++) {
					if(i==0) {
						snake.add(new Player(grid.getCells().get(428).x, grid.getCells().get(428).y, 40, 40));
					}
					else {
						snake.add(new Player(-1000, -1000, 40, 40));
					}
				}
				
				setup=false;
			}
			
			win.drawInit(new Vector4f(0.2f, 0.2f, 0.2f, 1));
			for(int i=0;i<snake.size();i++) {
				snake.get(i).update(camera, 0);
				light.getShadows().shadowFromGeometry(snake.get(i).getRect());
			}
			light.getShadows().shadowFromGeometry(food);
			
			
			light.render(camera);
			
			for(int i=1;i<snake.size();i++) {
				if(snake.get(0).getRect().getHitbox().intersect(snake.get(i).getRect().getHitbox())) {
					setup=true;
				}
			}
			
			
			
			for(int i=snake.size()-1;i>0;i--) {
				snake.get(i).translate(snake.get(i-1).getX(), snake.get(i-1).getY());
			}
			
			if(snake.get(0).getRect().getHitbox().intersect(food.getHitbox())) {
				snake.add(new Player(-1000, -1000, 40, 40));
				food.translate(new Vector3f(grid.getCells().get((int)(Math.random()*grid.getCells().size())), 0), 40, 40);
			}
			
			food.render(camera);
			
			if(win.getInput().isPressed(GLFW_KEY_A)) {
				left=true;
				right=false;
				up=false;
				down=false;
			}
			if(win.getInput().isPressed(GLFW_KEY_D)) {
				left=false;
				right=true;
				up=false;
				down=false;
			}
			if(win.getInput().isPressed(GLFW_KEY_W)) {
				left=false;
				right=false;
				up=true;
				down=false;
			}
			if(win.getInput().isPressed(GLFW_KEY_S)) {
				left=false;
				right=false;
				up=false;
				down=true;
			}
			
			if(left) {
				snake.get(0).move(-1, 0);
			}
			if(right) {
				snake.get(0).move(1, 0);
			}
			if(up) {
				snake.get(0).move(0, 1);
			}
			if(down) {
				snake.get(0).move(0, -1);
			}
			
			
			light.getShadows().clearShadows();
			
			win.clean();
			win.update(15);
		}
		
	}

}
