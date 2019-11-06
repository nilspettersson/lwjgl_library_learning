package niles.lwjgl.extra;

import java.util.ArrayList;

import org.joml.Vector2f;

public class Grid {
	
	private ArrayList<Vector2f> cells=new ArrayList<>();
	private float size;
	
	int c=0;
	int r=0;
	public Grid(float startX,float startY,int columnCount,int rowCount,float size) {
		this.size=size;
		while(r<rowCount) {
			
			cells.add(new Vector2f(startX+c*size, startY+r*size));
			
			c++;
			if(c>=columnCount) {
				c=0;
				r++;
			}
			
			
		}
		
	}
	
	
	
	public Vector2f getCell(int column,int row) {
		
		for(int i=0;i<cells.size();i++) {
			if(cells.get(i).x==column*size && cells.get(i).y==row*size) {
				return new Vector2f(cells.get(i).x, cells.get(i).y);
			}
		}
		return new Vector2f();
		
	}
	
	
	
	public ArrayList<Vector2f> getCells() {
		return cells;
	}
	public void setCells(ArrayList<Vector2f> cells) {
		this.cells = cells;
	}
	
	

}
