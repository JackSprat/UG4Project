package project.ug4.math;

public class IntVec2D {

	private int x, y;
	
	public IntVec2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public IntVec2D add(IntVec2D b) {
		return new IntVec2D(x + b.x, y + b.y);
	}
	
	public IntVec2D sub(IntVec2D b) {
		return new IntVec2D(x - b.x, y - b.y);
	}
	
	public IntVec2D scale(float value) {
		return new IntVec2D((int) (x * value), (int) (y * value));
	}
	
	public IntVec2D invert() {
		return new IntVec2D(-x, -y);
	}
	
	public double length() {
		return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}