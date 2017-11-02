
public class Swap {
	public void swap(int x,int y){
		int t;
		t =x;
		x =y;
		y=x;
	}
	
	public void passByValue(){
		Integer x = new Integer(10);
		Integer y = new Integer(20);
		swap(x,y);
	}
public static void main(String[] args){
	System.out.println("In Swap Program");
	Swap swap = new Swap();
	swap.passByValue();
}

}
