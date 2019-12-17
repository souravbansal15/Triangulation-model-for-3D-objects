class queue<T> {
	public int size,last,first,curr_size;
	public int total_capacity;
	public Object data[];
	public queue(int c){
		total_capacity=c;
		this.size=0;
		this.curr_size=0;
		this.last=0;
		this.first=total_capacity-1;
		data=new Object[this.total_capacity];
	}
	boolean isEmpty() {
		if(this.size==0) {
			return true;
		}else {
			return false;
		}
	}
	public void enqueue(Object o) {
		size++;
		first=(first+1)%total_capacity;
		data[first]=o;
	}
	public T dequeue() {
		T req=(T)data[last];
		last=(last+1)%total_capacity;
		size--;
		return req;
	}
	public T front() {
		T req=(T)data[last];
		return req;
	}
}