public class Point implements PointInterface,Comparable<Point>{
	public float[] coordinates;
	public list<Triangle> triangles=new list<Triangle>();//list of triangles
	public list<Edge> edges=new list<Edge>();//list of edges
	public long marker=0,marker1=0;
	public Point(float x, float y, float z){
		coordinates=new float[3];
		coordinates[0]=x;
		coordinates[1]=y;
		coordinates[2]=z;
	}
	public float getX() {
		return coordinates[0];
	}
	public float getY() {
		return coordinates[1];
	}
	public float getZ() {
		return coordinates[2];
	}
	public float [] getXYZcoordinate() {
		return coordinates;
	}
	public int hashCode() {
		float f1=this.getX();
		float f2=this.getY();
		float f3=this.getZ();
		int hash=(Float.floatToIntBits(f1)/3)+(Float.floatToIntBits(f2)/3)+(Float.floatToIntBits(f3)/3);
		return hash;
	}
	public String toString() {
		return String.valueOf(coordinates[0])+" "+String.valueOf(coordinates[1])+" "+String.valueOf(coordinates[2]);
	}
	public boolean equals(Point p) {
		if(this.getX()==p.getX() && this.getY()==p.getY() && this.getZ()==p.getZ()) {
			return true;
		}else {
			return false;
		}
	}
	public int compareTo(Point p) {
		if(this.getX() > p.getX()) {
			return 1;
		}else if(this.getX() < p.getX()) {
			return -1;
		}else {
			if(this.getY() > p.getY()) {
				return 1;
			}else if(this.getY() < p.getY()) {
				return -1;
			}else {
				if(this.getZ() > p.getZ()) {
					return 1;
				}else if(this.getZ() < p.getZ()) {
					return -1;
				}else {
					return 0;
				}
			}
		}
	}
}