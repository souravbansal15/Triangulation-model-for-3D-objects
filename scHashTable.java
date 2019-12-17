public class scHashTable<K extends Comparable<K>, T>{
	int capacity;
	public Node<K,T>[] table;
	
	//constructor
	scHashTable(int cap){
		this.capacity=cap;
		table=(Node<K,T>[])new Node[capacity];
	}
	public Node<K,T> insertTree(Node<K,T> curr,Node<K,T> n){
		if(curr==null) {
			curr=n;
		}else {
			if(curr.key.compareTo(n.key)>0) {
				curr.left=insertTree(curr.left,n);
			}else if(curr.key.compareTo(n.key)==0) {
				return curr;
			}
			else {
				curr.right=insertTree(curr.right,n);
			}
		}
		return curr;
	}
	
	public T getValue(Node <K,T> curr,K k) {
		if(curr==null) {
			return null;
		}
		K p=curr.key;
		if(p.compareTo(k)==0) {
			return curr.value;
		}else {
			if(p.compareTo(k)>0) {
				return getValue(curr.left,k);
			}else {
				return getValue(curr.right,k);
			}
		}
	}
	
	public void insert(K key, T obj) {
		int index=key.hashCode()%capacity;
		if (index<0) {
			index=index*(-1);
		}
		Node<K,T> n= new Node<K,T>(key,obj);
		table[index]= insertTree(table[index],n);
	}

	public T get(K key){
		int index=key.hashCode()%capacity;
		if (index<0) {
			index=index*(-1);
		}
		return getValue(table[index], key);
	}
}