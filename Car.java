package needForSpeed;

import java.util.LinkedList;
import java.util.List;

public class Car {
	
	final int cRow, cCol;
	final private List<Cell> segments;
	final private CellManaging cm;
	
	public Car(int row, int col, CellManaging cm){
		this.cm = cm;
		this.cRow = row;
		this.cCol = col;
		this.segments = new LinkedList<Cell>();
		for(int r = row-1; r <= row+2; r++){
			for(int c = col-1; c <= col+1; c++){
				if(c == col || (r == row+1 && c == col-1) || (r == row+1 && c == col+1) || (r == row-1 && c == col-1) || (r == row-1 && c == col+1)){
					segments.add(cm.allocateCell(r, c));
				}
			}
		}
	}
	
	public void Move(Move move){
		
	}
	

}
