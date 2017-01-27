import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Graphics;

class Pie extends Sprite{
	
	static Image pie_image = null;
	double y_velocity;
	Bird bird;
	LinkedList<Sprite> sprites;
	
	Pie(Bird b, LinkedList<Sprite> s){
		super();
		this.bird = b;
		try{
			if(Pie.pie_image == null){
				Pie.pie_image = ImageIO.read(new File("pie.png"));
			}
		}catch(IOException e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		this.x = bird.x;
		this.y = bird.y;
		this.z = 2;
		this.h = 30;
		this.w = 42;
		this.sprites = s;
	}
	
	public void update(){
		this.y -= 10;
		this.x += 15;
		if(this.destroy == false){
			this.detectCol();
		}
		this.y_velocity+=2.4;
		this.y += this.y_velocity;
		if(this.x > 500 || this.y > 500 ){
			this.destroy = true;
		}
	}
	
	boolean isPie(){
		return true;
	}
	
	boolean isTube(){
		return false;
	}
	
	boolean isBird(){
		return false;
	}
	
	public void draw(Graphics g) throws IOException {
		g.drawImage(Pie.pie_image, this.x, this.y, null);
	}
	
	public void detectCol(){
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			Sprite s = it.next();
			//check if sprite is Tube class
			if(s.isTube() == true){
				//check if pie intersects with tube
				this.destroy = doesIntersect(this, s);
				if(this.destroy == true){
					s.collision = true;
				}
			}
		}
	}
}