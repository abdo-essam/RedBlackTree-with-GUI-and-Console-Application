public class RedBlackTree {
    private Node root;
    private Node NILL;

    // fix the red-black tree
    private void fixInsert(Node newNode) {
        // 0 is black 1 is red
        Node parentNode = newNode.parent;
        Node grandParentNode = newNode.parent.parent;
        while (parentNode.color == 1) {
            if (parentNode == grandParentNode.left) {
                Node uncleNode = grandParentNode.right; // uncle
                if (uncleNode.color == 1) {
                    // case 1
                    uncleNode.color = 0; // black
                    parentNode.color = 0; // black
                    grandParentNode.color = 1; // red
                    newNode = grandParentNode;
                } else {
                    if (newNode == parentNode.right) {
                        // case 2
                        newNode = parentNode;
                        leftRotate(parentNode);
                    }
                    // case 3
                    parentNode.color = 0; // black
                    grandParentNode.color = 1; // red
                    rightRotate(grandParentNode);
                }
            } else {
                Node uncleNode = grandParentNode.left; // uncle

                if (uncleNode.color == 1) {
                    // mirror case 1
                    uncleNode.color = 0; // black
                    parentNode.color = 0; // black
                    grandParentNode.color = 1; // red
                    newNode = grandParentNode;
                } else {
                    // mirror case 2
                    if (newNode == parentNode.left) {
                        // mirror case 2
                        newNode = parentNode;
                        rightRotate(parentNode);
                    }
                    // mirror case 3
                    parentNode.color = 0; // black
                    grandParentNode.color = 1; // red
                    leftRotate(grandParentNode);
                }
            }
            if (newNode == root) {
                break;
            }
        }
        root.color = 0; // black
    }


    public RedBlackTree() {
        NILL = new Node();
        NILL.color = 0;
        NILL.left = null;
        NILL.right = null;
        root = NILL;
    }

    public void leftRotate(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        if (rightNode.left != NILL) {
            rightNode.left.parent = node;
        }
        rightNode.parent = node.parent;
        if (node.parent == null) {
            this.root = rightNode;
        } else if (node == node.parent.left) {
            node.parent.left = rightNode;
        } else {
            node.parent.right = rightNode;
        }
        rightNode.left = node;
        node.parent = rightNode;
    }


    public void rightRotate(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        if (leftNode.right != NILL) {
            leftNode.right.parent = node;
        }
        leftNode.parent = node.parent;
        if (node.parent == null) {
            this.root = leftNode;
        } else if (node == node.parent.right) {
            node.parent.right = leftNode;
        } else {
            node.parent.left = leftNode;
        }
        leftNode.right = node;
        node.parent = leftNode;
    }

    Node Presuccessor(Node k) {
        Node s = k;
        while (s.right != NILL)
            s = s.right;
        return s;
    }

    private void RBdeletedFIXUP(Node deletedNode) {
        Node w;
        while ((deletedNode != root) && deletedNode.color == 0) {
            if (deletedNode == deletedNode.parent.left) {
                w = deletedNode.parent.right;
                // case 1
                if (w.color == 1) {
                    w.color = 0;
                    deletedNode.parent.color = 1;
                    leftRotate(deletedNode.parent);
                    w = deletedNode.parent.right;
                }
                // case 2
                if (w.left.color == 0 && w.right.color == 0) {
                    w.color = 1;
                    deletedNode = deletedNode.parent;
                    // case 3 or 4
                } else {

                    if(w.right.color == 0){
                        w.left.color = 0;
                        w.color = 1;
                        rightRotate(w);
                        w = deletedNode.parent.right;
                    }
                    // case 4
                    w.color = deletedNode.parent.color;
                    deletedNode.parent.color = 0;
                    w.right.color = 0;
                    leftRotate(deletedNode.parent);
                    deletedNode = root;
                }

            } else {
                w = deletedNode.parent.left;
                // case 1
                if (w.color == 1) {
                    w.color = 0;
                    deletedNode.parent.color = 1;
                    rightRotate(deletedNode.parent);
                    w = deletedNode.parent.left;
                }
                // case 2
                if (w.right.color == 0 && w.left.color == 0) {
                    w.color = 1;
                    deletedNode = deletedNode.parent;
                    // case 3 or 4
                } else {
                    if(w.left.color == 0){
                        w.right.color = 0;
                        w.color = 1;
                        leftRotate(w);
                        w = deletedNode.parent.left;
                    }
                    // case 4
                    w.color = deletedNode.parent.color;
                    deletedNode.parent.color = 0;
                    w.left.color = 0;
                    rightRotate(deletedNode.parent);
                    deletedNode = root;
                }

            }

        }
        deletedNode.color = 0;
    }

    public void clear()
    {
        // In Java automatic garbage collection happens, so we can simply make root null to delete the tree
        root = null;
    }
    public void delete(int key){

        // find in the tree if containing key
        Node z = NILL;
        Node x;
        x = root;
        while (x != NILL) {
            if (x.data == key) {
                z = x;
            }

            if (x.data <= key) {
                x = x.right;
            } else {
                x = x.left;
            }
        }

        if (z == NILL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }
        else
            RBdeleted(z);
    }

    private void RBdeleted(Node z) {
        Node y;
        Node x = NILL;
        if (z.left == NILL || z.right == NILL)
            y = z;
        else
            y = Presuccessor(z.left);
        if (y.left != NILL)
            x = y.left;
        else if (y.right != NILL)
            x = y.right;
        x.parent = y.parent;
        if (x == NILL) {
            x.color = 0;
        }
        if (y.parent == NILL) {
            root = x;
        }
        if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;

        if(y != z)
            z.data = y.data;

        if (y.color == 0)
            RBdeletedFIXUP(x);
        if (x == NILL) {
            if (x == x.parent.left)
                x.parent.left = NILL;
            else
                x.parent.right= NILL;
        }

    }
    // insert the key in the correct position and fix the tree
    public void insert(int key) {

        Node newNode = new Node();
        newNode.parent = null;
        newNode.data = key;
        newNode.left = NILL;
        newNode.right = NILL;
        newNode.color = 1; //the new node must be red

        Node y = null;
        Node x = this.root;

        while (x != NILL) {
            y = x;
            if (newNode.data <= x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }


        if (y == null) {
            root = newNode;
        } else if (newNode.data <= y.data) {
            y.left = newNode;
        } else {
            y.right = newNode;
        }

        // y is parent of x
        newNode.parent = y;

        // new node is a root node, must be black
        if (newNode.parent == null) {
            newNode.color = 0;
            return;
        }

        // if the grandparent is null then out
        if (newNode.parent.parent == null) {
            return;
        }

        // Fix the tree
        fixInsert(newNode);
    }

    public Node getRoot() {
        return this.root;
    }

    public void print(Node root) {
        if (root != NILL) {
            print(root.left);
            System.out.print(root.data + " ");

            if (root.getColor() == 0)
                System.out.println("black");
            else
                System.out.println("red");


            print(root.right);
        }
    }
}