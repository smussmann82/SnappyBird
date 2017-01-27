import java.util.Comparator;

class CompZ implements Comparator<Sprite>{

	CompZ(){
	
	}
	
	@Override
	public int compare(Sprite a, Sprite b){
		if(a.z > b.z){
			return 1;
		}else{
			return -1;
		}
	}
}
