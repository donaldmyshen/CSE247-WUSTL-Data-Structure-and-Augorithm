package studio8;

public class Point {

	public int x;
	public int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

    // @Override
	// public int hashCode() {
	// compute a hashcode here!
	//}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}
