import java.awt.Graphics;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;

public abstract class Sprite
{
    int x; 
    int y;
    int z;
    int h;
    int w;
    boolean collision = false;
    boolean destroy = false;
    
    Sprite(){
    	
    }
	
	public abstract void update();
	
	public abstract void draw(Graphics g) throws IOException;
	
	abstract boolean isPie();
	
	abstract boolean isTube();
	
	abstract boolean isBird();
	
	public boolean doesIntersect(Sprite a, Sprite b){
		if(a.x + a.w > b.x && a.x < b.x + b.w && a.y + a.h > b.y && a.y < b.y + b.h){
			return true;
		}else{
			return false;
		}
	}
}
