public class Edge implements EdgeInterface{
	public Point[] endPoints;
	list<Triangle> triangles=new list<Triangle>();
	public float distance_square=0;
	public Edge(Point p1, Point p2) {
		endPoints=new Point[2];
		//two points can not be equal
		if(p2.compareTo(p1)>0) {
			endPoints[0]=p1;
			endPoints[1]=p2;
		}else {
			endPoints[0]=p2;
			endPoints[1]=p1;
		}
		float a=p2.getX()-p1.getX();
		float b=p2.getY()-p1.getY();
		float k=p2.getZ()-p1.getZ();
		distance_square=a*a+b*b+k*k;
	}
	public PointInterface [] edgeEndPoints() {
		return endPoints;
	}
	public int hashCode() {
		Point p1=endPoints[0];
		Point p2=endPoints[1];
		int hash=(p1.hashCode()/2)+(p2.hashCode()/2);
		return hash;
	}
	public boolean equals(Edge e) {
		if(this.endPoints[0].equals(e.endPoints[0]) && this.endPoints[1].equals(e.endPoints[1])) {
			return true;
		}else {
			return false;
		}
	}
	public String toString() {
		return (endPoints[0].toString()+"// "+endPoints[1].toString());
	}
}