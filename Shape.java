

public class Shape implements ShapeInterface
{
	scHashTable<Point,Point> points=new scHashTable<Point,Point>(9739);
	scHashTable<Triangle,Triangle> triangles=new scHashTable<Triangle,Triangle>(9739);
	list<Edge> allEdges=new list<Edge>();
	list<Triangle> allTriangles=new list<Triangle>();
	int triangle_count=0;
	long marker=0;
	int number_points=0;
	boolean check=false;
	long marker1=0;
	float closest=Float.MAX_VALUE;
	public boolean checkLine(float [] c) {
//		float s1=(c[6]-c[3])/(c[3]-c[0]);
//		float s2=(c[7]-c[4])/(c[4]-c[1]);
//		float s3=(c[8]-c[5])/(c[5]-c[2]);
//		float a=(c[6]-c[3])*(c[4]-c[1]);////////////////////////??????????????????????????CHANGE IT?????????????????????????????????????????????????????????
//		float b=(c[7]-c[4])*(c[3]-c[0]);////////////////////////??????????????????????????CHANGE IT?????????????????????????????????????????????????????????
//		float p=(c[7]-c[4])*(c[5]-c[2]);////////////////////////??????????????????????????CHANGE IT?????????????????????????????????????????????????????????
//		float q=(c[8]-c[5])*(c[4]-c[1]);////////////////////////??????????????????????????CHANGE IT?????????????????????????????????????????????????????????
		
//		float f1=(c[3]-c[0])*(c[3]-c[0])+(c[4]-c[1])*(c[4]-c[1])+(c[5]-c[2])*(c[5]-c[2]);
//		float f2=(c[6]-c[3])*(c[6]-c[3])+(c[7]-c[4])*(c[7]-c[4])+(c[8]-c[5])*(c[8]-c[5]);
//		float f3=(c[6]-c[0])*(c[6]-c[0])+(c[7]-c[1])*(c[7]-c[1])+(c[8]-c[2])*(c[8]-c[2]);
//		if(f1==0 || f2==0 || f3==0) {
//			return true;
//		}
//		float cos1=(float) ((f1+f3-f2)/(2*Math.sqrt(f1)*Math.sqrt(f3)));
//		float cos2=(float) ((f1+f2-f3)/(2*Math.sqrt(f1)*Math.sqrt(f2)));
//		float cos3=(float) ((f2+f3-f1)/(2*Math.sqrt(f2)*Math.sqrt(f3)));
//		System.out.println(cos1+" "+cos2+" "+cos3);
		
		
		float x1=c[0],y1=c[1],z1=c[2],x2=c[3],y2=c[4],z2=c[5],x3=c[6],y3=c[7],z3=c[8];
		float f1=((y1-y2)*(z1-z3)-(y1-y3)*(z1-z2));
		float f2=((x1-x2)*(z1-z3)-(x1-x3)*(z1-z2));
		float f3=((x1-x2)*(y1-y3)-(x1-x3)*(y1-y2));
		if(Math.abs(f1)<0.0001f && Math.abs(f2)<0.0001f && Math.abs(f3)<0.0001f) {
			return true;
		}else {
			return false;
		}
	}
	public void print() {
		for(int i=0;i<triangles.table.length;i++) {
			if(triangles.table[i]!=null) {
				Triangle t=(Triangle)triangles.table[i].value;
				System.out.println(t.vertices[0].toString()+t.vertices[2].toString()+t.vertices[2].toString());
			}
		}
	}
	public boolean ADD_TRIANGLE(float [] triangle_coord) {
		//long t6=System.nanoTime();
		if(checkLine(triangle_coord)) {
			//System.out.println("wrong");
			return false;
		}else {
			//creating Point objects
			Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
			Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
			Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
			
//			System.out.println(p1);
//			System.out.println(p2);
//			System.out.println(p3);
			Point temp=points.get(p1);
			if(temp!=null) {
				p1=temp;
				//System.out.println("old"+p1);
			}else {
				points.insert(p1, p1);
			}
			temp=points.get(p2);
			if(temp!=null) {
				p2=temp;
				//System.out.println("old"+p2);
			}else {
				points.insert(p2, p2);
			}
			temp=points.get(p3);
			if(temp!=null) {
				//System.out.println("old"+p3);
				p3=temp;
			}else {
				points.insert(p3, p3);
			}
			
			//creating Edge objects
			Edge e1=new Edge(p1,p2);
			Edge e2=new Edge(p2,p3);
			Edge e3=new Edge(p3,p1);
			list<Edge> l1= p1.edges;
			boolean b1=false;
			boolean b2=false;
			for(int i=0;i<l1.curr_size;i++) {
				Edge e=l1.get(i);
				if(e.equals(e1)) {
					e1=e;
					b1=true;
					//System.out.println("old1"+e1);
				}
				if(e.equals(e3)) {
					e3=e;
					b2=true;
					//System.out.println("old2"+e3);
				}
			}
			if(b1==false) {
				p1.edges.add(e1);
				p2.edges.add(e1);
				allEdges.add(e1);//all edges list
			}
			if(b2==false) {
				p1.edges.add(e3);
				p3.edges.add(e3);
				allEdges.add(e3);//all edges list
			}
			
			list<Edge> l2= p2.edges;
			boolean b3=false;
			for(int i=0;i<l2.curr_size;i++) {
				Edge e=l2.get(i);
				if(e.equals(e2)) {
					e2=e;
					b3=true;
					//System.out.println("old"+e2+"         "+e);
					break;
				}
			}
			if(b3==false) {
				p2.edges.add(e2);
				p3.edges.add(e2);
				allEdges.add(e2);//all edges list
			}
			//creating Triangle object
			Triangle t1=new Triangle(p1,p2,p3);
			Triangle tempT=triangles.get(t1);
			if(tempT!=null) {
				t1=tempT;
				//System.out.println(t1+"////////");
			}else {
				p1.triangles.add(t1);
				p2.triangles.add(t1);
				p3.triangles.add(t1);
				t1.edges[0]=e1;
				t1.edges[1]=e2;
				t1.edges[2]=e3;
				e1.triangles.add(t1);
				e2.triangles.add(t1);
				e3.triangles.add(t1);
				triangles.insert(t1, t1);
				t1.tcount=triangle_count;//for fifo
				triangle_count++;//for fifo
				
				////////adding neighbours//////////////////
				for(int i=0;i<e1.triangles.curr_size;i++) {
					Triangle temp_t=e1.triangles.get(i);
					if(!temp_t.equals(t1)) {
						t1.neighbours.add(temp_t);
						temp_t.neighbours.add(t1);
					}
				}
				for(int i=0;i<e2.triangles.curr_size;i++) {
					Triangle temp_t=e2.triangles.get(i);
					if(!temp_t.equals(t1)) {
						t1.neighbours.add(temp_t);
						temp_t.neighbours.add(t1);
					}
				}
				for(int i=0;i<e3.triangles.curr_size;i++) {
					Triangle temp_t=e3.triangles.get(i);
					if(!temp_t.equals(t1)) {
						t1.neighbours.add(temp_t);
						temp_t.neighbours.add(t1);
					}
				}
				///////////////////////////////////////////
				allTriangles.add(t1);
				
			}
//			System.out.println(p1+"||||||"+p2+"||||||"+p3+"||||||");
//			System.out.println(p1.edges.curr_size+"||||||"+p2.edges.curr_size+"||||||"+p3.edges.curr_size+"||||||");
//			System.out.println(p1.triangles.curr_size+"||||||"+p2.triangles.curr_size+"||||||"+p3.triangles.curr_size+"||||||");
//			System.out.println(e1+"||||||"+e2+"||||||"+e3+"||||||");
//			System.out.println(e1.triangles.curr_size+"||||||"+e2.triangles.curr_size+"||||||"+e3.triangles.curr_size+"||||||");
//			System.out.println("///////////////////////");
			//long t7=System.nanoTime();
			//System.out.println((t7-t6)+"//////////");
			return true;
		}
	}
	
	public int TYPE_MESH() {
		int n1=0,n2=0;
		for(int i=0;i<allEdges.curr_size;i++) {
			Edge e=allEdges.get(i);
			if(e.triangles.curr_size>2) {
				return 3;
			}else if(e.triangles.curr_size==2) {
				n2++;
			}else {
				n1++;
			}
		}
		//System.out.println("n1"+n1+"n2"+n2);
		if(n1>0) {
			return 2;
		}else {
			return 1;
		}
	}
	public void merge_arrays(Edge[] a, int l, int mid, int h) {
		Edge[] e1=new Edge[mid-l+1];
		Edge[] e2=new Edge[h-mid];
		for (int i=0;i<mid-l+1;i++) {//check
			e1[i]=a[l+i];
		}
		for (int j=0;j<h-mid;j++) {
			e2[j]=a[mid+1+j];
		}
		
		int i=0,j=0; 
        int k = l;
        while(i<mid-l+1 && j<h-mid){
        	if(e1[i].distance_square<=e2[j].distance_square) {
        		a[k]=e1[i];
        		i++;
        	}else {
        		a[k]=e2[j];
        		j++;
        	}
        	k++;
        }
        while(i<mid-l+1) {
        	a[k]=e1[i];
        	i++;
        	k++;
        }
        while(j<h-mid) {
        	a[k]=e2[j];
        	j++;
        	k++;
        }
	}
	public void sorted_edges(Edge[] a, int l, int h) {
		if(l<h) {
			int mid=(l+h)/2;
			sorted_edges(a,l,mid);
			sorted_edges(a,mid+1,h);
			merge_arrays(a,l,mid,h);
		}
		
	}
	public EdgeInterface [] BOUNDARY_EDGES() {
		list<Edge> l=new list<Edge>();
		for(int i=0;i<allEdges.curr_size;i++) {
			Edge e=allEdges.get(i);
			if(e.triangles.curr_size==1) {
				l.add(e);
			}
		}
		if(l.curr_size==0) {
			return null;
		}else {
			int s=l.curr_size;
			Edge[] ans=new Edge[s];
			for(int i=0;i<s;i++) {
				ans[i]=l.get(i);
			}
			sorted_edges(ans,0,s-1);
//			for(int i=0;i<ans.length;i++) {
//				System.out.println(ans[i]);
//			}
			return ans;
		}
	}
	
	public void BFS_count(Triangle t, boolean[] vis) {
//		vis[t.tcount]=true;
//		for(int i=0;i<t.neighbours.curr_size;i++) {
//			Triangle curr=t.neighbours.get(i);
//			if(!vis[curr.tcount]) {
//				DFS(curr,vis);
//			}
//		}
		vis[t.tcount]=true;
		queue<Triangle> q=new queue<Triangle>(triangle_count);
		q.enqueue(t);
		while(!q.isEmpty()) {
			Triangle curr=q.dequeue();
			for(int i=0;i<curr.neighbours.curr_size;i++) {
				Triangle neighbour=curr.neighbours.get(i);
				if(!vis[neighbour.tcount]) {
					vis[neighbour.tcount]=true;
					q.enqueue(neighbour);
				}
			}
		}
	}
	
	public int COUNT_CONNECTED_COMPONENTS() {
//		int num=0;
//		boolean[] vis=new boolean[triangle_count];
//		for(int i=0;i<triangle_count;i++) {
//			if(!vis[i]) {
//				num++;
//				DFS(allTriangles.get(i),vis);
//			}
//		}
//		return num;
		///////////////////////////////////////////
		int num=0;
		boolean[] vis=new boolean[triangle_count];
		for(int i=0;i<triangle_count;i++) {
			if(!vis[i]) {
				num++;
				BFS_count(allTriangles.get(i),vis);
			}
		}
		return num;
	}
	
	
	
	public list<Triangle> merge_sorted(list<Triangle> a,list<Triangle> b){
		int s1=a.curr_size,s2=b.curr_size;
		list<Triangle> ans=new list<Triangle>();
		int i=0,j=0;
		while(i<s1 && j<s2) {
			if(a.get(i).tcount<b.get(j).tcount) {
				ans.add(a.get(i));
				i++;
			}else {
				ans.add(b.get(j));
				j++;
			}
		}
		while (i<s1) {
			ans.add(a.get(i));
			i++;
		}
	    while (j<s2) {
	    	ans.add(b.get(j));
			j++;
	    }
	    return ans;
	}
	public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord) {
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		Triangle tempT=triangles.get(t1);
		if(tempT!=null) {
			list<Triangle> l1=tempT.edges[0].triangles;
			list<Triangle> l2=tempT.edges[1].triangles;
			list<Triangle> l3=tempT.edges[2].triangles;
			int s1=l1.curr_size,s2=l2.curr_size,s3=l3.curr_size;
			list<Triangle> l=merge_sorted(l1,l2);
			l=merge_sorted(l,l3);
			Triangle[] t=new Triangle[s1+s2+s3-3];
			int c=0;
			for(int i=0;i<s1+s2+s3;i++) {
				if(l.get(i).tcount!=tempT.tcount) {
					t[c]=l.get(i);
					c++;
				}
			}
			for(int i=0;i<t.length;i++) {
				System.out.println(t[i]);
			}
			//System.out.println("//////"+s1+s2+s3);
			return t;
		}else {
			return null;
		}
	}
	
	public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
//		long t6=System.nanoTime();
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		
		Triangle tempT=triangles.get(t1);
		
		if(tempT!=null) {
//			for(int i=0;i<tempT.edges.length;i++) {
//				System.out.println(tempT.edges[i]);
//			}
//			long t7=System.nanoTime();
//			System.out.println((t7-t6)+"//////////");
			return tempT.edges;
		}else {
			return null;
		}
		
	}
	public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		Triangle tempT=triangles.get(t1);
		if(tempT!=null) {
//			for(int i=0;i<tempT.vertices.length;i++) {
//				System.out.println(tempT.vertices[i]);
//			}
			return tempT.vertices;
		}else {
			return null;
		}
	}
	public list<Triangle> merge_sorted_unique(list<Triangle> a,list<Triangle> b){
		int s1=a.curr_size,s2=b.curr_size;
		list<Triangle> ans=new list<Triangle>();
		int i=0,j=0;
		while(i<s1 && j<s2) {
			if(a.get(i).tcount<b.get(j).tcount) {
				ans.add(a.get(i));
				i++;
			}else if(a.get(i).tcount>b.get(j).tcount){
				ans.add(b.get(j));
				j++;
			}else {
				ans.add(a.get(i));
				i++;
				j++;
			}
		}
		while (i<s1) {
			if(!(a.get(i).tcount==b.get(j-1).tcount)) {
				ans.add(a.get(i));
			}
			i++;
		}
	    while (j<s2) {
	    	if(!(b.get(j).tcount==a.get(i-1).tcount)) {
				ans.add(b.get(j));
			}
			j++;
	    }
	    return ans;
	}
	public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord) {
		Point p1=new Point(triangle_coord[0],triangle_coord[1],triangle_coord[2]);
		Point p2=new Point(triangle_coord[3],triangle_coord[4],triangle_coord[5]);
		Point p3=new Point(triangle_coord[6],triangle_coord[7],triangle_coord[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		Triangle tempT=triangles.get(t1);
		if(tempT!=null) {
			list<Triangle> l1=tempT.vertices[0].triangles;
			list<Triangle> l2=tempT.vertices[1].triangles;
			list<Triangle> l3=tempT.vertices[2].triangles;
			list<Triangle> l=merge_sorted_unique(l1,l2);
			l=merge_sorted_unique(l,l3);
			Triangle[] t=new Triangle[l.curr_size-1];
			int c=0;
			for(int i=0;i<l.curr_size;i++) {
				if(l.get(i).tcount!=tempT.tcount) {
					t[c]=l.get(i);
					c++;
				}
			}
			for(int i=0;i<t.length;i++) {
				System.out.println(t[i]);
			}
			if(t.length==0) {
				return null;
			}
			return t;
		}else {
			return null;
		}
	}
	public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates) {
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		Point p1=points.get(p);
		if(p1!=null) {
			int s=p1.triangles.curr_size;
			Triangle[] t=new Triangle[s];
			for(int i=0;i<s;i++) {
				t[i]=p1.triangles.get(i);
			}
//			for(int i=0;i<t.length;i++) {
//				System.out.println(t[i]);
//			}
			return t;//always in fifo order
		}else {
			return null;
		}
	}
	public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates) {
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		Point p1=points.get(p);
		if(p1!=null) {
			int s=p1.edges.curr_size;
			Point[] ans=new Point[s];
			for(int i=0;i<s;i++) {
				if(p1.edges.get(i).endPoints[0].equals(p1)) {
					ans[i]=p1.edges.get(i).endPoints[1];
				}else {
					ans[i]=p1.edges.get(i).endPoints[0];
				}
			}
//			for(int i=0;i<ans.length;i++) {
//				System.out.println(ans[i]);
//			}
			return ans;
		}else {
			return null;
		}
	}
	public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates) {
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		Point p1=points.get(p);
		if(p1!=null) {
			int s=p1.edges.curr_size;
			Edge[] e=new Edge[s];
			for(int i=0;i<s;i++) {
				e[i]=p1.edges.get(i);
			}
//			for(int i=0;i<e.length;i++) {
//				System.out.println(e[i]);
//			}
			return e;
		}else {
			return null;
		}
	}
	public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates) {
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		Point p1=points.get(p);
		if(p1!=null) {
			int s=p1.triangles.curr_size;
			Triangle[] t=new Triangle[s];
			for(int i=0;i<s;i++) {
				t[i]=p1.triangles.get(i);
			}
			return t;
		}else {
			return null;
		}
	}
	
	public void BFS_check(Triangle t1,Triangle t2, boolean[] vis) {
		if(t1.equals(t2)) {
			check=true;
			return;
		}
		vis[t1.tcount]=true;
		queue<Triangle> q=new queue<Triangle>(triangle_count);
		q.enqueue(t1);
		while(!q.isEmpty()) {
			Triangle curr=q.dequeue();
			for(int i=0;i<curr.neighbours.curr_size;i++) {
				Triangle neighbour=curr.neighbours.get(i);
				if(neighbour.equals(t2)) {
					check=true;
					return;
				}
				if(!vis[neighbour.tcount]) {
					vis[neighbour.tcount]=true;
					q.enqueue(neighbour);
				}
			}
		}
	}
	
	public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2) {
		Point p1=new Point(triangle_coord_1[0],triangle_coord_1[1],triangle_coord_1[2]);
		Point p2=new Point(triangle_coord_1[3],triangle_coord_1[4],triangle_coord_1[5]);
		Point p3=new Point(triangle_coord_1[6],triangle_coord_1[7],triangle_coord_1[8]);
		Triangle t1=new Triangle(p1,p2,p3);
		Triangle req1=triangles.get(t1);
		
		Point p4=new Point(triangle_coord_2[0],triangle_coord_2[1],triangle_coord_2[2]);
		Point p5=new Point(triangle_coord_2[3],triangle_coord_2[4],triangle_coord_2[5]);
		Point p6=new Point(triangle_coord_2[6],triangle_coord_2[7],triangle_coord_2[8]);
		Triangle t2=new Triangle(p4,p5,p6);
		Triangle req2=triangles.get(t2);
		if(req1==null || req2==null) {
			return false;
		}
		boolean[] vis=new boolean[triangle_count];
		check=false;
		BFS_check(req1,req2,vis);
		return check;
	}
	
	public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates) {
		Point p1=new Point(edge_coordinates[0],edge_coordinates[1],edge_coordinates[2]);
		Point p2=new Point(edge_coordinates[3],edge_coordinates[4],edge_coordinates[5]);
		Point temp=points.get(p1);
		if(temp!=null) {
			p1=temp;
		}else {
			return null;
		}
		boolean b=false;
		Edge e=new Edge(p1,p2);
		for(int i=0;i<p1.edges.curr_size;i++) {
			Edge e1=p1.edges.get(i);
			if(e.equals(e1)) {
				b=true;
				e=e1;
				break;
			}
		}
		if(b) {
			int s=e.triangles.curr_size;
			Triangle[] t=new Triangle[s];
			for(int i=0;i<s;i++) {
				t[i]=e.triangles.get(i);
			}
//			for(int i=0;i<t.length;i++) {
//				System.out.println(t[i]);
//			}
			return t;
		}else {
			return null;
		}
	}
	
	public int[] BFS(Triangle t) {
		int num=1;
		int[] distance=new int[triangle_count];
		int nodes=triangle_count;
		queue<Triangle> q=new queue<Triangle>(nodes);
		boolean[] visited=new boolean[nodes];
		for(int i=0;i<nodes;i++) {
			distance[i]=-1;
		}
		visited[t.tcount]=true;
		distance[t.tcount]=0;
		q.enqueue(t);
		
		while(!q.isEmpty()) {
			Triangle curr=q.dequeue();
			for(int i=0;i<curr.neighbours.curr_size;i++) {
				Triangle neighbour=curr.neighbours.get(i);
				if(!visited[neighbour.tcount]) {
					visited[neighbour.tcount]=true;
					distance[neighbour.tcount]=distance[curr.tcount]+1;
					q.enqueue(neighbour);
					num++;
				}
			}
		}
		int max_src=0;
		for(int i=0;i<nodes;i++) {
			if(distance[i]>max_src) {
				max_src=distance[i];
			}
		}
		int[] arr=new int[2];
		arr[0]=max_src;
		arr[1]=num;
		return arr;
		
	}
	
	public int MAXIMUM_DIAMETER() {
		//long t12=System.nanoTime();
		int[] max_dist=new int[triangle_count];
		int[] tri=new int[triangle_count];
		for(int i=0;i<triangle_count;i++) {
			int[] max_src=BFS(allTriangles.get(i));
			max_dist[i]=max_src[0];
			tri[i]=max_src[1];
		}
		int max_num=0;
		int diameter=0;
		for(int i=0;i<triangle_count;i++) {
			if(tri[i]>max_num) {
				max_num=tri[i];
				diameter=max_dist[i];
			}else if(tri[i]==max_num) {
				if(max_dist[i]>diameter) {
					diameter=max_dist[i];
				}
			}else {
				
			}
		}
		System.out.println(diameter+"hhhhh");
		//long t13=System.nanoTime();
		//System.out.println((t13-t12)+"??");
//		for(int i=0;i<tri.length;i++) {
//			System.out.print(tri[i]+" ");
//		}
		return diameter;
	}
	public void findCentroid(Triangle t,float[] centroid, boolean[] vis) {
		Point p1=t.vertices[0];
		Point p2=t.vertices[1];
		Point p3=t.vertices[2];
		if(p1.marker!=marker) {
			centroid[0]+=p1.getX();
			centroid[1]+=p1.getY();
			centroid[2]+=p1.getZ();
			p1.marker=marker;
			number_points++;
		}
		if(p2.marker!=marker) {
			centroid[0]+=p2.getX();
			centroid[1]+=p2.getY();
			centroid[2]+=p2.getZ();
			p2.marker=marker;
			number_points++;
		}
		if(p3.marker!=marker) {
			centroid[0]+=p3.getX();
			centroid[1]+=p3.getY();
			centroid[2]+=p3.getZ();
			p3.marker=marker;
			number_points++;
		}
		vis[t.tcount]=true;
		for(int i=0;i<t.neighbours.curr_size;i++) {
			Triangle curr=t.neighbours.get(i);
			if(!vis[curr.tcount]) {
				findCentroid(curr,centroid,vis);
			}
		}
	}
	public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates) {
		Point p=new Point(point_coordinates[0],point_coordinates[1],point_coordinates[2]);
		Point p1=points.get(p);
		if(p1!=null) {
			marker++;
			number_points=0;
			Triangle t=p1.triangles.get(0);
			boolean[] vis=new boolean[triangle_count];
			float[] centroid=new float[3];
			findCentroid(t,centroid,vis);
			float f1=centroid[0]/number_points,f2=centroid[1]/number_points,f3=centroid[2]/number_points;
			Point c=new Point(f1,f2,f3);
			//System.out.println(c);
			return c;
		}else {
			return null;
		}
	}
	
	public void merge_arr_points(Point[] a, int l, int mid, int h) {
		Point[] e1=new Point[mid-l+1];
		Point[] e2=new Point[h-mid];
		for (int i=0;i<mid-l+1;i++) {//check
			e1[i]=a[l+i];
		}
		for (int j=0;j<h-mid;j++) {
			e2[j]=a[mid+1+j];
		}
		
		int i=0,j=0; 
        int k = l;
        while(i<mid-l+1 && j<h-mid){
        	if(e2[j].compareTo(e1[i])>=0) {
        		a[k]=e1[i];
        		i++;
        	}else {
        		a[k]=e2[j];
        		j++;
        	}
        	k++;
        }
        while(i<mid-l+1) {
        	a[k]=e1[i];
        	i++;
        	k++;
        }
        while(j<h-mid) {
        	a[k]=e2[j];
        	j++;
        	k++;
        }
	}
	
	public void sorted_points(Point[] a, int l, int h) {
		if(l<h) {
			int mid=(l+h)/2;
			sorted_points(a,l,mid);
			sorted_points(a,mid+1,h);
			merge_arr_points(a,l,mid,h);
		}
		
	}
	
	public PointInterface [] CENTROID () {
		boolean[] vis=new boolean[triangle_count];
		list<Point> centroids=new list<Point>();
		for(int i=0;i<triangle_count;i++) {
			Triangle t=allTriangles.get(i);
			if(!vis[t.tcount]) {
				marker++;
				number_points=0;
				float[] centroid=new float[3];
				findCentroid(t,centroid,vis);
				float f1=centroid[0]/number_points,f2=centroid[1]/number_points,f3=centroid[2]/number_points;
				Point c=new Point(f1,f2,f3);
				centroids.add(c);
			}
		}
		Point[] cs=new Point[centroids.curr_size];
		for(int i=0;i<centroids.curr_size;i++) {
			cs[i]=centroids.get(i);
		}
		sorted_points(cs,0,centroids.curr_size-1);
//		for(int i=0;i<centroids.curr_size;i++) {
//			System.out.println(cs[i]);
//		}
		return cs;
	}
	public void make_components(Triangle t, boolean[] vis, Component new_component) {
		Point p1=t.vertices[0];
		Point p2=t.vertices[1];
		Point p3=t.vertices[2];
		if(p1.marker1!=marker1) {
			new_component.points.add(p1);
			p1.marker1=marker1;
		}
		if(p2.marker1!=marker1) {
			new_component.points.add(p2);
			p2.marker1=marker1;
		}
		if(p3.marker1!=marker1) {
			new_component.points.add(p3);
			p3.marker1=marker1;
		}
		vis[t.tcount]=true;
		for(int i=0;i<t.neighbours.curr_size;i++) {
			Triangle curr=t.neighbours.get(i);
			if(!vis[curr.tcount]) {
				make_components(curr,vis,new_component);
			}
		}
	}
	public Point[] getMinimum(Component c1, Component c2) {
		float minimum=Float.MAX_VALUE;
		Point[] req=new Point[2];
		for(int i=0;i<c1.points.curr_size;i++) {
			Point p1=c1.points.get(i);
			for(int j=0;j<c2.points.curr_size;j++) {
				Point p2=c2.points.get(j);
				float a=p2.getX()-p1.getX();
				float b=p2.getY()-p1.getY();
				float k=p2.getZ()-p1.getZ();
				float dis=a*a+b*b+k*k;
				if(minimum>dis) {
					minimum=dis;
					req[0]=p1;
					req[1]=p2;
				}
			}
		}
		closest=minimum;
		return req;
	}
	public 	PointInterface [] CLOSEST_COMPONENTS() {
		closest=Float.MAX_VALUE;
		boolean[] vis=new boolean[triangle_count];
		list<Component> components=new list<Component>();
		for(int i=0;i<triangle_count;i++) {
			Triangle t=allTriangles.get(i);
			if(!vis[t.tcount]) {
				Component new_component=new Component();
				marker1++;
				make_components(t,vis,new_component);
				components.add(new_component);
			}
		}
		float min=Float.MAX_VALUE;
		Point[] ans=new Point[2];
		for(int i=0;i<components.curr_size;i++) {
			for(int j=i+1;j<components.curr_size;j++) {
				Point[] points=getMinimum(components.get(i),components.get(j));
				if(min>closest) {
					//System.out.println(min+","+closest);
					min=closest;
					ans=points;
				}
				//System.out.println(ans[0]);
				//System.out.println(ans[1]+"////");
			}
		}
//		for(int i=0;i<components.curr_size;i++) {
//			for(int j=0;j<components.get(i).points.curr_size;j++) {
//				System.out.print("//"+components.get(i).points.get(j)+"//");
//			}
//			System.out.println();
//		}
		//System.out.println(ans[0]+" "+ans[1]);
		return ans;
	}
}

