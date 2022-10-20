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
		if(element.compareTo(this._element) == 0) {
			if (this._left == null && this._right == null) { // leaf condition
				this.remove(element);
				return this;
			}
			if (this._left == null || this._right == null){ // one child condition
				if (this._left == null) {
					this._element.equals(this._right);
				}
				else{
					this._element.equals(this._left);
				}
			}
			if (this._left != null && this._right != null) { // 2 children condition
				while(this.getLeft() != null){
					this._left = lChild.remove(element);
				}
				this._element.equals(this._left); // is this doing what I want?
			}
		}
		return this;
	}

	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
	
	
	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
	
	
	}

	// TODO: printBreadthFirstTraversal
	@Override
	public void printBreadthFirstTraversal() {
	
	
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
