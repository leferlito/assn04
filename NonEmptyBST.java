package assn04;


import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	// left child = lower
	// right child = greater
	public BST<T> insert(T element) {
		BST<T> lChild = this._left;
		BST<T> rChild = this._right;

		if(element.compareTo(this._element) > 0) {
			this._right = rChild.insert(element);
		}
		if(element.compareTo(this._element) < 0) {
			this._left = lChild.insert(element);
		}
		if(element.compareTo(this._element) == 0) {
			this._right = rChild.insert(element);
		}
		return this;
	}

	// TODO: remove
	// if only one node, return empty BST
	// also need to implement the searching and see what sorts of children they have:
	// if leaf, just delete
	// if only one child, replace
	// if 2 children, replace by successor
	@Override
	public BST<T> remove(T element) {
		BST<T> lChild = this._left;
		BST<T> rChild = this._right;
		if (this.isEmpty()) {
			return this;
		}
		// search first, then figure out number of children
		if(element.compareTo(this._element) > 0){
			this._right = rChild.remove(element);
		}
		if(element.compareTo(this._element) < 0){
			this._left = lChild.remove(element);
		}
		if(element.compareTo(this._element) == 0) { // finally found node
			if (this._left.isEmpty() && this._right.isEmpty()) { // leaf condition
				return new EmptyBST<>();
			}
			if (this._left.isEmpty() ^ this._right.isEmpty()){ // one child condition
				if (this._left.isEmpty()) {
					return this.getRight();
				}
				else{
					return this.getLeft();
				}
			}
			if (!this._left.isEmpty() && !this._right.isEmpty()) { // 2 children condition. replace with smallest on right
				BST<T> rightNode = this.getRight();
				while(!this.getLeft().isEmpty()){
					rightNode = rightNode.getLeft();
				}
				this._element = rightNode.getElement();
				this._right = this._right.remove(this._element);
			}
		}
		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		System.out.print(this.getElement()+ " ");
		this.getLeft().printPreOrderTraversal();
		this.getRight().printPreOrderTraversal();
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		this.getLeft().printPostOrderTraversal();
		this.getRight().printPostOrderTraversal();
		System.out.print(this.getElement()+ " ");
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() { // queue linked list Queue<T>, enqueue/dequeue
		Queue<BST<T>> queue = new LinkedList<>();
		BST<T> current = null;
		queue.add(this);
		while (!queue.isEmpty()) {
			current = queue.poll();
			if (!current.getLeft().isEmpty()) {
				queue.add(current.getLeft());
			}
			if (!current.getRight().isEmpty()) {
				queue.add(current.getRight());
			}
			System.out.print(current.getElement() + " ");
		}
	}

	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;
	
	
	}


	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
