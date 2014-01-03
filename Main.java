package needForSpeed;

import java.awt.Color;
import java.util.LinkedList;

import sedgewick.StdDraw;

public class Main {

	public static void main(String[] args) {
		// FIXME Auto-generated method stub
		int rows = 40;
		int cols = 40;
		Arena arena = new Arena(rows, cols);
		Self self = new Self(2, cols/2, arena);
		int score = 0;
		StdDraw.setCanvasSize(700, 700);
		LinkedList<Opponent> opponents = new LinkedList<Opponent>();
		boolean alive = true;
		
		while(true){
			arena.draw();
			self.drawMyself();
			StdDraw.setPenColor(Color.green);
			StdDraw.text(0.1, 0.01, ""+score);
			Move mv = new Move(0, 0);
			
			if(StdDraw.hasNextKeyTyped()){
				char m = StdDraw.nextKeyTyped();
				switch(m){
				case 'W': case 'w':
					mv = new Move(1, 0);
					break;
				case 'A': case 'a':
					mv = new Move(0, -1);
					break;
				case 'S': case 's':
					mv = new Move(-1, 0);
					break;
				case 'D': case 'd':
					mv = new Move(0, 1);
					break;
				default:
					break;
				}
			}
			
			self.Move(mv, arena);
			
			if(Math.random() < 0.2){
				int theCol = (int)(Math.random()*(cols-2))+1;
				Opponent newOpponent = new Opponent(rows-4, theCol, arena);
				opponents.add(newOpponent);
			}
			if(opponents.size()>1){
				for(int i = 0; i< opponents.size()-1; i++){
					if(opponents.get(i).Collide(opponents.get(opponents.size()-1))){
						opponents.remove(opponents.size()-1);
					}
				}
			}
			
			for(Opponent x: opponents){
				x.drawItself();
				if(x.Collide(self)){
					alive = false;
				}
//				x.moveDownAuto(arena);
				if(!x.moveDownAuto(arena)){
					x.setInArena(false);
//					System.out.println(score);
//					opponents.remove(x);
//					opponents.removeFirst();
				}
			}
			
			if(!alive){
				StdDraw.setPenColor(Color.DARK_GRAY);
				StdDraw.text(0.5, 0.5, "Game Over!");
				break;
			}
			
			for(int i = 0; i < opponents.size(); i++){
				if(!opponents.get(i).isInArena()){
					opponents.remove(i);
					score++;
				}
			}
			
			/*for(Opponent x: opponents){
				if(!x.isInArena()){
					System.out.println("in here");
					opponents.remove(x);
					score++;
				}
			}
*/			
			StdDraw.show(50);
			StdDraw.clear();
		}
		
		System.out.println("Score: " + score);
		
		StdDraw.show(200);
		StdDraw.setPenColor(Color.green);
		StdDraw.setPenRadius(0.2);
		StdDraw.line(0, 0, 1, 1);
		StdDraw.line(0, 1, 1, 0);
		StdDraw.text(0.5, 0.5, "Game Over!");

	}

}
