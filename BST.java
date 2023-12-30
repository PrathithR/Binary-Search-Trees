public class BST {
	private Node root;
	private int counter;
	/*Methods:
	 * int size(): return the number of nodes in tree
	 * void insert(int element): creates and inserts a new node with the element
	 * void delete(int element): deletes the node with element and readjusts the tree.
	 * void preorder: Prints the preorder traversal of the tree.
	 * void postorder: Prints the postorder traversal of the tree.
	 * void inorder: Prints the inorder traversal of the tree.
	 * 
	 * Helper Methods(private):
	 * void preorder(Node root), postorder(Node root), inorder(Node root).
	 * boolean isLeaf(Node n):returns True if n is leaf, false otherwise
	 * boolean hasSingleChild(Node n):returns True if n has only one child, false otherwise
	 * */

	class Node{
		private int key;
		private Node parent;
		private Node leftChild;
		private Node rightChild;
		/*Methods:
		 * getters and setters for all fields
		 * */
		public Node() {
			key=-1;
			parent=null;
			leftChild=null;
			rightChild=null;
		}
		public Node(int key) {
			this.key=key;
			parent=null;
			leftChild=null;
			rightChild=null;
		}
		public Node(int key, Node parent,Node leftChild, Node rightChild) {
			this.key=key;
			this.parent=parent;
			this.leftChild=leftChild;
			this.rightChild=rightChild;
		}

		public int getKey() {
			return key;
		}
		public void setKey(int k) {
			key=k;
		}
		public Node getParent() {
			return parent;
		}
		public void setParent(Node p) {
			parent=p;
		}
		public Node getLeft() {
			return leftChild;
		}
		public void setLeft(Node l) {
			leftChild=l;
		}
		public Node getRight() {
			return rightChild;
		}
		public void setRight(Node r) {
			rightChild=r;
		}

	}


	public BST() {
		root=null;
		counter=0;
	}

	public int size() {
		return counter;
	}

	public void insert(int element) {
		if(root==null) {//If BST empty
			root=new Node(element);
			counter++;
			return;
		}
		Node curr=root;
		for(int i=0;i<counter;i++) {//Max loops = no.of nodes

			if(element<curr.key) {//if less than current, go left
				if(curr.leftChild==null) {//if left empty, insert
					curr.leftChild=new Node(element,curr,null,null);
					counter++;//increment counter
					return;
				}
				curr=curr.leftChild;//if left not empty, change curr to left and continue.
				continue;
			}
			if(element>curr.key) {//if gerater than current, go right
				if(curr.rightChild==null) {//if right empty, insert
					curr.rightChild=new Node(element,curr,null,null);
					counter++;//increment counter
					return;
				}
				curr=curr.rightChild;//if right not empty, change curr to right and continue.
				continue;
			}
			System.out.println("Element is already in the tree!");//when curr==element
			return;
		}
	}
	public void delete(int element) {
		if(root==null) {//If BST empty
			System.out.println("Tree is empty.");
			return;
		}
		if(root.key==element) {//If root is to be deleted.
			if(isLeaf(root)) {//If root is leaf
				root=null;
			}else if(hasSingleChild(root)) {//if root has single child, make that child root
				if(root.leftChild!=null) {//if child is on left
					root=root.leftChild;
				}

				if(root.rightChild!=null) {//if child is on right
					root=root.rightChild;
				}
			}else {//root has two nodes: Replace deleted node with smallest node on its right side, and call delete on that node.
				Node smallestRight=root.rightChild;
				while(smallestRight.leftChild!=null) {
					smallestRight=smallestRight.leftChild;
				}
				int temp=smallestRight.key;
				delete(smallestRight.key);
				root.key=temp;
				return;//since counter is decremented by the delete called above, return from here. 
			}
			counter--;
			return;
		}
		Node curr=root;
		for(int i=0;i<counter;i++) {//Max loops = no.of nodes

			if(element<curr.key) {//if less than current, go left
				if(curr.leftChild==null) {//if left empty, element doesn't exist
					System.out.println("Element not found!");
					return;
				}
				if(curr.leftChild.key==element) {//true if node found at left of curr
					if(isLeaf(curr.leftChild)) {//if leaf
						curr.leftChild=null;
					}else if(hasSingleChild(curr.leftChild)) {// if it has single child
						Node left=curr.leftChild;//left is the node to be deleted
						if(left.leftChild!=null) {//if that child is on left
							curr.leftChild=left.leftChild;//Switch left with left.leftChild.
							left.leftChild.parent=curr;//new parent of the child of deleted node is the parent of the deleted node
						}

						if(left.rightChild!=null) {//if that child is on right
							curr.leftChild=left.rightChild;//Switch left with left.rightChild.
							left.rightChild.parent=curr;//new parent of the child of deleted node is the parent of the deleted node
						}
					}else {//Node has two children: Replace deleted node with smallest node on its right side, and call delete on that node.
						curr=curr.leftChild;//change curr from parent to the actual node to be deleted
						Node smallestRight=curr.rightChild;
						while(smallestRight.leftChild!=null) {
							smallestRight=smallestRight.leftChild;
						}
						int temp=smallestRight.key;
						delete(smallestRight.key);
						curr.key=temp;
						return;//since counter is decremented by the delete called above, return from here.
					}
					counter--;
					return;
				}//if element if not found and next left is not null
				curr=curr.leftChild;//change curr to left and continue.
				continue;
			}
			if(element>curr.key) {//if greater than current, go right
				if(curr.rightChild==null) {//if right empty, element doesn't exist
					System.out.println("Element not found!");
					return;
				}
				if(curr.rightChild.key==element) {//true if node found on right of curr
					if(isLeaf(curr.rightChild)) {//if leaf
						curr.rightChild=null;
					}else if(hasSingleChild(curr.rightChild)) {//if it has single child
						Node right=curr.rightChild;
						if(right.leftChild!=null) {//if child on left
							curr.rightChild=right.leftChild;//switch right with right.leftChild
							right.leftChild.parent=curr;//new parent of the child of deleted node is the parent of the deleted node
						}
						if(right.rightChild!=null) {//if child on right
							curr.rightChild=right.rightChild;//switch right with right.rightChild
							right.rightChild.parent=curr;//new parent of the child of deleted node is the parent of the deleted node
						}
					}else {//Node has two children: Replace deleted node with smallest node on its right side, and call delete on that node.
						curr=curr.rightChild;//change curr from parent to the actual node to be deleted
						Node smallestRight=curr.rightChild;
						while(smallestRight.leftChild!=null) {
							smallestRight=smallestRight.leftChild;
						}
						int temp=smallestRight.key;
						delete(smallestRight.key);
						curr.key=temp;
						return;//since counter is decremented by the delete called above, return from here.
					}
					counter--;
					return;
				}//if element if not found and next right is not null
				curr=curr.rightChild;//change curr to right and continue.
				continue;
			}
		}
	}

	public void preorder() {
		preorder(root);
		System.out.println();
	}

	public void postorder() {
		postorder(root);
		System.out.println();
	}

	public void inorder() {
		inorder(root);
		System.out.println();
	}



	public boolean isLeaf(Node n) {
		if(n.leftChild==null&&n.rightChild==null) {//True if no children node exist.
			return true;
		}
		return false;
	}
	public boolean hasSingleChild(Node n) {
		if(n.leftChild==null^n.rightChild==null) {//True if only one child node exists.
			return true;
		}
		return false;
	}

	private void preorder(Node root) {
		System.out.print(root.key+" ");
		if(root.leftChild!=null) {
			preorder(root.leftChild);
		}
		if(root.rightChild!=null) {
			preorder(root.rightChild);
		}
	}
	private void postorder(Node root) {
		if(root.leftChild!=null) {
			postorder(root.leftChild);
		}
		if(root.rightChild!=null) {
			postorder(root.rightChild);
		}
		System.out.print(root.key+" ");
	}
	private void inorder(Node root) {
		if(root.leftChild!=null) {
			inorder(root.leftChild);
		}
		System.out.print(root.key+" ");
		if(root.rightChild!=null) {
			inorder(root.rightChild);
		}
	}
}
