public class Triangle implements TriangleInterface, Comparable<Triangle>{
	public Point[] vertices;
	public Edge[] edges=new Edge[3];
	list<Triangle> neighbours=new list<Triangle>();
	public int tcount=0;
	public Triangle(Point a, Point b, Point c) {
		vertices=new Point[3];
		vertices[0]=a;
		vertices[1]=b;
		vertices[2]=c;
		//ordering
		for(int i=0;i<3;i++) {
			for(int j=i+1;j<3;j++) {
				if(vertices[i].compareTo(vertices[j])>0) {
					Point temp=vertices[i];
					vertices[i]=vertices[j];
					vertices[j]=temp;
				}
			}
		}
	}
	public String toString() {
		return vertices[0].toString()+"// "+vertices[1].toString()+"// "+vertices[2].toString();
	}
	public PointInterface [] triangle_coord() {
		return vertices;
	}
	public int hashCode() {
		Point p1=vertices[0];
		Point p2=vertices[1];
		Point p3=vertices[2];
		int hash=(p1.hashCode()/3)+(p2.hashCode()/3)+(p3.hashCode()/3);
		return hash;
	}
	public boolean equals(Triangle t) {
		if(this.vertices[0].equals(t.vertices[0]) && this.vertices[1].equals(t.vertices[1]) && this.vertices[2].equals(t.vertices[2])) {
			return true;
		}else {
			return false;
		}
	}
	public int compareTo(Triangle t) {
		if(this.vertices[0].compareTo(t.vertices[0])>0) {
			return 1;
		}else if(this.vertices[0].compareTo(t.vertices[0])<0) {
			return -1;
		}else {
			if(this.vertices[1].compareTo(t.vertices[1])>0) {
				return 1;
			}else if(this.vertices[1].compareTo(t.vertices[1])<0) {
				return -1;
			}else {
				if(this.vertices[2].compareTo(t.vertices[2])>0) {
					return 1;
				}else if(this.vertices[2].compareTo(t.vertices[2])<0) {
					return -1;
				}else {
					return 0;
				}
			}
		}
	}
	
}