import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;

class Tube extends Sprite
{
	boolean direction;
	static Image tube_up = null;
	static Image tube_down = null;
	Random rng;
	LinkedList<Sprite> sprites;
      
	Tube(Random r, LinkedList<Sprite> s){
		super();
		sprites = s;
  	  	try{
  	  		Tube.tube_up = ImageIO.read(new File("tube_up.png"));
  	  		Tube.tube_down = ImageIO.read(new File("tube_down.png"));
  	  	}catch( IOException e){
			e.printStackTrace(System.err);
			System.exit(1);
  	  	}
		this.rng = r;
		this.w = 55; //tube width
		this.h = 400; //tube height
		this.direction = rng.nextBoolean();
		this.x = 600;
		this.z = 1;
		if(this.direction == true){
			this.y = rng.nextInt(350-200)+200;
		}else if(this.direction == false){
			this.y = rng.nextInt(350-200)-200;
		}
	}
	
	public void update() {
		// animate the tube moving left
		this.x -= 15;
		if(this.collision == false){
			detectCol();
		}  
    	  
    	  // animate the tube to retract when hit by pie
    	  if(this.collision == true){
    		  if(this.direction == true){
    			  this.y += 10;
    		  }else if(this.direction == false){
    			  this.y -= 10;
    		  }
    	  }
    	  
    	  // determine if the tube should be destroyed
    	  if(this.direction == true){
    		  if(this.x < -100 || this.y < 0){
    			  this.destroy = true;
    		  }
    	  }else if(this.direction == false){
    		  if(this.x < -100 || this.y > 500){
    			  this.destroy = true;
    		  }
    	  }
    	  
      }
	
	boolean isPie(){
		return false;
	}
	
	boolean isTube(){
		return true;
	}
	
	boolean isBird(){
		return false;
	}
      
	public void draw(Graphics g) throws IOException{
		if(direction == true){
			g.drawImage(Tube.tube_up, this.x, this.y, null);
		}else if(direction == false){
			g.drawImage(Tube.tube_down, this.x, this.y, null);
		}
	}
	
	public void detectCol(){
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			Sprite s = it.next();
			//check if sprite is Tube class
			if(s.isPie() == true){
				//check if pie intersects with tube
				this.collision = doesIntersect(s, this);
				if(this.collision == true){
					s.destroy = true;
				}
			}
		}
	}

}


