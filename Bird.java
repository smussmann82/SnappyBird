import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.Graphics;
//import javax.swing.JPanel;

class Bird extends Sprite{
	static Image image1 = null;
	static Image image2 = null;
	static Image image_death = null;
	int counter;
	double velocity;
	LinkedList<Sprite> sprites;
	
	Bird(LinkedList<Sprite> s){
		super();
		this.sprites = s;
		this.z = 3;
		this.h = 45; //bird height (in pixels)
		this.w = 60; //bird width (in pixels)
		try{
			if(Bird.image1 == null){
				Bird.image1 = ImageIO.read(new File("bird1.png"));
			}
			if(Bird.image2 == null){
				Bird.image2 = ImageIO.read(new File("bird2.png"));
			}
			if(Bird.image_death == null){
				Bird.image_death = ImageIO.read(new File("feathers.png"));
			}
		}catch( IOException e){
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	public void update() {
		// Move the bird
		this.counter++;
		this.velocity += 2.0;
		this.y += this.velocity;
		if(this.collision == false){
			this.detectCol();
		}
		if(this.y > 500 || this.y + this.h < 0){
			this.collision = true;
		}
		if(this.collision == true){
			this.deathAnimation();
		}
	}
	
	boolean isPie(){
		return false;
	}
	
	boolean isTube(){
		return false;
	}
	
	boolean isBird(){
		return true;
	}
	
	public void draw(Graphics g) throws IOException{

		if(this.collision == true){
			g.drawImage(Bird.image_death, this.x, this.y, null);
		}else if(this.counter%4 == 0){
			g.drawImage(Bird.image1, this.x, this.y, null);
		}else{
			g.drawImage(Bird.image2, this.x, this.y, null);
		}
	}
	
	public void flap() {
		if(this.collision == false){
			this.velocity = -25;
		}
	}
	
	public void deathAnimation(){
		//System.out.println("Game Over");
		this.x += 10;
		if(this.x > 600){
			System.exit(0);
		}
		if(this.y >=460){
			this.velocity = -25;
		}
	}
	
	//detect collisions
	public void detectCol(){
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			Sprite s = it.next();
			if(s.isTube() == true){
				this.collision = doesIntersect(this, s);
			}
		}
	}
}
