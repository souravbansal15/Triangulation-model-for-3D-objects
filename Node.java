public class Node<K,T>{
	Node<K,T> left,right;
	K key;
	T value;
	Node(K key, T value){
		this.key=key;
		this.value=value;
		left=null;
		right=null;
	}
}