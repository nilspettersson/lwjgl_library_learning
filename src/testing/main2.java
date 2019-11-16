package testing;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glReadBuffer;
import static org.lwjgl.opengl.GL11.glReadPixels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Rect;
import niles.lwjgl.extra.Player;
import niles.lwjgl.light.Lights;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Camera;
import niles.lwjgl.world.Window;

public class main2 {
	public static void main(String[] args) {
		Window win=new Window(1920, 1080, true);
		Camera camera=new Camera(1920, 1080);
		
		Texture playerTexture=new Texture("res/floor.png");
		Player player=new Player(0, 400, 200, 200, playerTexture);
		
		
		Texture texture=new Texture("res/floor.png");
		//Texture texture2=new Texture("res/wood_planks_old_0087_01.jpg");
		
		ArrayList<Entity>ob=new ArrayList<Entity>();
		ob.add(new Rect(new Vector3f(-300,100, 0), 800, 100, texture));
		ob.add(new Rect(new Vector3f(-800,300, 0), 200, 600, texture));
		ob.add(new Rect(new Vector3f(-80000,-200, 0), 160000, 100, texture));
		
		
		for(int i=0;i<0;i++) {
			ob.add(new Rect(new Vector3f((float)(Math.random()*400), (float)(Math.random()*400), 0), 40, 40, texture));
		}
		
		
		Lights light=new Lights(win,1.2f,Lights.ADD_LIGHT);
		
		
		light.addLight(-700, 600, 0.6f);
		
		win.setVSync(false);
		while(win.shouldUpdate()) {
			long start=win.getTime();
			win.drawInit(new Vector4f(0.4f,0.6f,1f,1));
			
			
			
			
			if(win.getInput().isPressed(GLFW_KEY_R)) {
				player.translate(100, 400);
				player.setXvel(0);
				player.setYvel(0);
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
							player.setYvel(0.15f);
						}
						//player.setXvel(player.getXvel()/1.4f);
					}
					inAir=false;
				}
				
			}
			
			player.setXvel(player.getXvel()/1.1f);
			
			
			//light.translate(0, player.getRect().getX()+player.getRect().getWidth()/2+camera.getWidth()/2, player.getRect().getY());
			
				
				
				
				if(win.getInput().isDown(GLFW_KEY_O)) {
					screenShot();
				}
				
				
				
			
			
			if(win.getInput().isDown(GLFW_KEY_A)) {
				if(player.getXvel()>-0.1f) {
					player.setXvel(player.getXvel()-0.005f);
				}
			}
			if(win.getInput().isDown(GLFW_KEY_D)) {
				if(player.getXvel()<0.1f) {
					player.setXvel(player.getXvel()+0.005f);
				}
			}
			
			
			if(win.getInput().isDown(GLFW_KEY_E)) {
				player.getRect().rotate();
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
	
	
	
	
	
	
	
	public static void screenShot() {
		glReadBuffer(GL_FRONT);
		int width = 1920;
		int height= 1080;
		int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
		ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
		glReadPixels(0, 0, width, height, GL_RGBA, GL_UNSIGNED_BYTE, buffer );
		

		
		/*File file = new File("test.png"); // The file to save to.
		String format = "PNG"; // Example: "PNG" or "JPG"
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		   
		for(int x = 0; x < width; x++) 
		{
		    for(int y = 0; y < height; y++)
		    {
		        int i = (x + (width * y)) * bpp;
		        int r = buffer.get(i) & 0xFF;
		        int g = buffer.get(i + 1) & 0xFF;
		        int b = buffer.get(i + 2) & 0xFF;
		        image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
		    }
		}
		   
		try {
		    ImageIO.write(image, format, file);
		} catch (IOException e) { e.printStackTrace(); }*/
		
	}
	
	
	
	
	

}
