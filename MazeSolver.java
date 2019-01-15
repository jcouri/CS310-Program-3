package prog3;
import data_structures.*;

public class MazeSolver {
	private MazeGrid grid;
	private GridCell cell;
	private GridCell terminalCell;
	private Stack<GridCell> stack;
	private Queue<GridCell> queue;
	private int x;
	private int y;
	private int distance;
	
	public MazeSolver(int dimension) {
		grid = new MazeGrid(this, dimension);
		terminalCell = grid.getCell(dimension-1, dimension-1);
		stack = new Stack<GridCell>();
		queue = new Queue<GridCell>();
		distance = -1;
	}
	
	public void mark() {
		queue.enqueue(grid.getCell(0,0));
		while(!queue.isEmpty()) {
			cell = queue.dequeue();
			distance = cell.getDistance() + 1;
			cell.setDistance(distance);
			x = cell.getX();
			y = cell.getY();
			grid.markDistance(cell);
			if (grid.isValidMove(grid.getCell(x-1,y))) {
				cell = grid.getCell(x-1,y);
				if (!cell.wasVisited()){
					cell.setDistance(distance);
					queue.enqueue(cell);
				}
			}
			if (grid.isValidMove(grid.getCell(x,y-1))) {
				cell = grid.getCell(x,y-1);
				if (!cell.wasVisited()){
					cell.setDistance(distance);
					queue.enqueue(cell);
				}
			}
			if (grid.isValidMove(grid.getCell(x+1,y))) {
				cell = grid.getCell(x+1,y);
				if (!cell.wasVisited()){
					cell.setDistance(distance);
					queue.enqueue(cell);
				}
			}
			if (grid.isValidMove(grid.getCell(x,y+1))) {
				cell = grid.getCell(x,y+1);
				if (!cell.wasVisited()){
					cell.setDistance(distance);
					queue.enqueue(cell);
				}
			}
		}
	}
	
	public boolean move() {
		distance = terminalCell.getDistance();
		if(distance == -1) return false; 
		stack.push(terminalCell);
		while(distance != 0) {
			cell = stack.peek();
			x = cell.getX();
			y = cell.getY();
			if (grid.isValidMove(grid.getCell(x-1,y))) {
				if (grid.getCell(x-1,y).getDistance() < distance) {
					cell = grid.getCell(x-1,y);
					distance = cell.getDistance();
				}
			}
			if (grid.isValidMove(grid.getCell(x,y-1))) {
				if (grid.getCell(x,y-1).getDistance() < distance){
					cell = grid.getCell(x,y-1);
					distance = cell.getDistance();
				}
			}
			if (grid.isValidMove(grid.getCell(x+1,y))) {
				if (grid.getCell(x+1,y).getDistance() < distance){
					cell = grid.getCell(x+1,y);
					distance = cell.getDistance();
				}
			}
			if (grid.isValidMove(grid.getCell(x,y+1))) {
				if (grid.getCell(x,y+1).getDistance() < distance){
					cell = grid.getCell(x,y+1);
					distance = cell.getDistance();
				}
			}
			stack.push(cell);
		}
		while (!stack.isEmpty()) {
			cell = stack.pop();
			grid.markMove(cell);
		}
		return true;
	}
	
	public void reset() {
		queue.makeEmpty();
		stack.makeEmpty();
	}
	
	public static void main(String[] args) {
		new MazeSolver(30);
	}
}
