package testing;

import org.joml.Vector4f;

import niles.lwjgl.light.Lights;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.MouseCursor;
import niles.lwjgl.world.Window;

public class light {

	public static void main(String[] args) {
		Window win=new Window(1920, 1080, true);
		Camera camera=new Camera(1920, 1080);
		
		MouseCursor m=new MouseCursor();
		
		Lights l=new Lights(win, 0.001f, Lights.ADD_LIGHT);
		l.particleSystemInit(220, 1, 0, 0, 10, 10, 219, 0.1f);
		l.ParticleSetStartVel(0, 4, 2, 2);
		
		
		win.setVSync(false);
		while(win.shouldUpdate()) {
			win.drawInit(new Vector4f(0));
			
			
			l.setParticleX(m.getMouseMovement(win, 1).x);
			l.setParticleY(m.getMouseMovement(win, 1).y*-1);
			
			
			for(int i=0;i<l.getLights().size();i++) {
				l.getLights().get(i).setIntensity(l.getLights().get(i).getLifeTime()/80);
			}
			l.particleUpdate();
			l.particleAdd();
			
			l.render(camera);
			
			
			win.clean();
			
			win.update(120*2);
			System.out.println(win.getFps());
		}

	}

}
