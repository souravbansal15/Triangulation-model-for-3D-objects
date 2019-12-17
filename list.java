public class list<T>{
	public int size_factor=100;
	public Object data[];
	int index;
	int size;
	public int curr_size=0;
	public list() {
		this.data=new Object[size_factor];
		this.size=size_factor;
	}
	public void add(Object o) {
		curr_size++;
		if(this.index==this.size-1) {
			increase();
		}
		data[this.index]=o;
		this.index++;
	}
	public void increase() {
		this.size=this.size*2;
		Object new_data[]=new Object[this.size];
		for(int i=0;i<data.length;i++) {
			new_data[i]=data[i];
		}
		this.data=new_data;
	}
	public T get(int i){
		if(i>this.index-1 || i<0) {
			return null;
		}else {
			return (T)data[i];
		}
	}
}