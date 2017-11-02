
/**
 * File Name: Maze.java
 * 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2017
 * 
 *       To compile you require: IntUtil.java RandomInt.java Maze.java
 */

public class Maze {

	// ADD ONLY PRIVATE VARIABLES THAY YOU REQUIRE for the class
	private static IntUtil u = new IntUtil();
	private char[][] maze = null;

	// WRITE CODE BELOW
	Maze(int row, int column) {
		maze = new char[row][column];
		for (int i = row-1; i >= 0; i--) {
			for (int j = 0; j < column; j++) {
				maze[i][j] = '.';
			}
		}
		
	}

	public Maze() {
		super();
	}

	public void setWall(int x, int y) {
		maze[x][y] = '*';
	}
	public boolean traversablePath(String s, int[] from, int[] to){
		int rows = maze.length;
		int column = maze[0].length;
		
		int[] start = from;
		int[] end=to;
		int x,y;
		int startX = x = start[0];
		int startY = y = start[1];
		if(maze[startX][startY] == '*'){
			return false;
		}
	
		for(int i=0;i<s.length();i= i+2){
			char direction=s.charAt(i);
			int move = Character.getNumericValue(s.charAt(i+1));
			
			if((Character.isLetter(direction) && !Character.isLetter(move)) && (direction=='n'| direction=='N')){
				if((x + move) >= rows)
					return false;
				for(int k=1;k<=move;k++){
					if (maze[x+k][y] == '*')
						return false;
				}
					
				x = x + move;
			}
			else if((Character.isLetter(direction) && !Character.isLetter(move)) && (direction=='e'| direction=='E')){
				if((y + move) >= column)
					return false;
				for(int k=1;k<=move;k++){
					if (maze[x][y+k] == '*')
						return false;
				}
				y = y + move;
			}
			else if((Character.isLetter(direction) && !Character.isLetter(move)) && (direction=='s'| direction=='S')){
				if((x - move) < 0)
					return false;
				for(int k=1;k<=move;k++){
					if (maze[x-k][y] == '*')
						return false;
				}
				x = x - move;
			}
			else if((Character.isLetter(direction) && !Character.isLetter(move)) && (direction=='w'| direction=='W')){
				if((y - move) < 0)
					return false;
				for(int k=1;k<=move;k++){
					if (maze[x][y-k] == '*')
						return false;
				}
				y = y - move;
			}
			
//			System.out.println("New Position After Moving "+ x +" "+y);
		}
		
		return true;
	}
	// CANNOT CHANGE ANYTHING BELOW
	// MUST WORK AS IS
	// ALL ASSERTIONS MUST PASS
	private boolean test1(int[] from, int[] to, String s) {
		boolean p = traversablePath(s, from, to);
		if (p == true) {
			System.out
					.println("{" + from[0] + "," + from[1] + "} " + s + " move to " + "{" + to[0] + "," + to[1] + "} ");
		} else {
			System.out.println("move " + s + " not possible");
		}
		return p;
	}

	private static void test1() {
		Maze m = new Maze(3, 4);
		m.setWall(2, 3);
		m.setWall(1, 1);
		m.setWall(0, 1);
		m.draw();
		{
			int[] from = { 0, 0 };
			int[] to = { -1, -1 };
			String s = "N2e1E1n0s2e1";
			boolean p = m.test1(from, to, s);
			u.myassert(p == true);
		}
		{
			int[] from = { 0, 0 };
			int[] to = { -1, -1 };
			String s = "N2e3";
			boolean p = m.test1(from, to, s);
			u.myassert(p == false);
		}

		{
			int[] from = { 0, 0 };
			int[] to = { -1, -1 };
			String s = "W1";
			boolean p = m.test1(from, to, s);
			u.myassert(p == false);
		}

		{
			int[] from = { 0, 0 };
			int[] to = { -1, -1 };
			String s = "N3";
			boolean p = m.test1(from, to, s);
			u.myassert(p == false);
		}
	}

	private static void test2() {
		Maze m = new Maze(5, 9);
		m.setWall(1, 2);
		m.setWall(3, 6);
		m.setWall(0, 6);
		m.draw();
		{
			int[] from = { 2, 0 };
			int[] to = { -1, -1 };
			String s = "N2e1E1n0s2e1";
			boolean p = m.test1(from, to, s);
			u.myassert(p == true);
		}

	}

	private static void testBench() {
		test1();
		test2();

	}
	
	public void draw(){
		int row=maze.length;
		int column=maze[0].length;
		
		System.out.print(" ");
		for(int i=0;i<column;i++){
			System.out.print(" "+i);
		}
		System.out.println("\n");
		for(int i=row-1;i>=0;i--){
			System.out.print(i+" ");
			for(int j=0;j<column;j++){
				System.out.print(maze[i][j] +" ");
			}
			System.out.println("\n");
		}
		
	}
	

	public static void main(String[] args) {
		
		System.out.println("Maze.java");
		testBench();
//		boolean isTraversable = m.traversablePath(s,from,to);
//		System.out.println("isTraversable"+isTraversable);
		System.out.println("DONE with Maze");
	}
}

/*
 * 
 * m.draw() MUST PRODUCE THIS OUTPUT 0123 2 ...* 1 .*.. 0 .*.. 0123
 * 
 * A POSSIBLE MOVE MUST PRODUCE OUTPUT LIKE THIS
 * 
 * move 2 steps N {0,0} -> 2,0} move 1 steps e {2,0} -> 2,1} move 1 steps E
 * {2,1} -> 2,2} move 0 steps n {2,2} -> 2,2} move 2 steps s {2,2} -> 0,2} move
 * 1 steps e {0,2} -> 0,3} {0,0} N2e1E1n0s2e1 move to {0,3}
 * 
 * AN POSSIBLE MOVE MUST PRODUCE OUTPUT LIKE THIS move 2 steps N {0,0} -> 2,0}
 * move 3 steps e {2,0} -> 2,3} move N2e3 not possible
 * 
 * 
 */
