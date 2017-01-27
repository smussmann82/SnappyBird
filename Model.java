import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Iterator;
import java.util.LinkedList;

class Model
{
	Bird bird;
	Tube tube;
	LinkedList<Sprite> sprites;
	Random rng;
	int framecounter = 0;

	Model() {
		this.rng = new Random();
		this.sprites = new LinkedList<Sprite>();
		this.bird = new Bird(this.sprites);
		this.tube = new Tube(this.rng, this.sprites);
		this.sprites.add(bird);
		this.sprites.add(tube);
	}
	
	public void update(){
		
		Collections.sort(sprites, new CompZ());
		
		Iterator<Sprite> it = sprites.iterator();
		while(it.hasNext())
		{
			Sprite s = it.next();
			s.update();
			if(s.destroy == true){
				it.remove();
			}
		}
		
		this.framecounter++;
		if(this.framecounter % 25 == 0){
			this.sprites.add(new Tube(this.rng, this.sprites));
		}
	}
	
	public void onClick(){
		this.bird.flap();
	}
	
	public void rightClick() {
		if(bird.collision == false){
			this.sprites.add(new Pie(this.bird, this.sprites));
		}
	}

}