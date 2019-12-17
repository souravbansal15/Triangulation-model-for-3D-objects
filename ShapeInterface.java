// THE DEFAULT IS JUST ADDED SO THAT YOU CAN BUILD YOUR IMPLEMENTATION INCREMENTALLY.

public interface ShapeInterface
{

//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
default public boolean ADD_TRIANGLE(float [] triangle_coord){return false;}

//
default public int TYPE_MESH(){return -1;}

//
default public EdgeInterface [] BOUNDARY_EDGES(){return null;}

//
default public int COUNT_CONNECTED_COMPONENTS(){return 0;}


//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
default public TriangleInterface [] NEIGHBORS_OF_TRIANGLE(float [] triangle_coord){return null;}


//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
default public EdgeInterface [] EDGE_NEIGHBOR_TRIANGLE(float [] triangle_coord){return null;}

//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
default public PointInterface [] VERTEX_NEIGHBOR_TRIANGLE(float [] triangle_coord){return null;}


//INPUT [x1,y1,z1,x2,y2,z2,x3,y3,z3]
default public TriangleInterface [] EXTENDED_NEIGHBOR_TRIANGLE(float [] triangle_coord){return null;}


//INPUT [x,y,z]
default public TriangleInterface [] INCIDENT_TRIANGLES(float [] point_coordinates){return null;}


// INPUT [x,y,z]
default public PointInterface [] NEIGHBORS_OF_POINT(float [] point_coordinates){return null;}


// INPUT[x,y,z]
default public EdgeInterface [] EDGE_NEIGHBORS_OF_POINT(float [] point_coordinates){return null;}


// INPUT[x,y,z]
default public TriangleInterface [] FACE_NEIGHBORS_OF_POINT(float [] point_coordinates){ return null;}



// INPUT // [xa1,ya1,za1,xa2,ya2,za2,xa3,ya3,za3 , xb1,yb1,zb1,xb2,yb2,zb2,xb3,yb3,zb3]   where xa1,ya1,za1,xa2,ya2,za2,xa3,ya3,za3 are 3 coordinates of first triangle and xb1,yb1,zb1,xb2,yb2,zb2,xb3,yb3,zb3 are coordinates of second triangle as given in specificaition.

default public boolean IS_CONNECTED(float [] triangle_coord_1, float [] triangle_coord_2){return false;}


// INPUT [x1,y1,z1,x2,y2,z2] // where (x1,y1,z1) refers to first end point of edge and  (x2,y2,z2) refers to the second.
default public TriangleInterface [] TRIANGLE_NEIGHBOR_OF_EDGE(float [] edge_coordinates){ return null;}


default public int MAXIMUM_DIAMETER(){return 0;}



default public PointInterface [] CENTROID (){
return null;
}

// INPUT [x,y,z]
default public PointInterface CENTROID_OF_COMPONENT (float [] point_coordinates){

return null;
}


default public 	PointInterface [] CLOSEST_COMPONENTS(){
return null;
}







}

