
public class test {
	public static void main(String[] args) throws Exception {
		list<Integer> l=new list<Integer>();
		System.out.println(l.size);
		for(int i=0;i<1000;i++) {
			l.add(i);
		}
		int i1=l.get(6);
		System.out.println(i1);
		for(int i=0;i<1000;i++) {
			//System.out.println(l.get(i));
		}
	}
}