package workwithtrees;

import java.util.ArrayList;

/**
 * Class of the AVL Binary Tree. This class inherit the class BinaryTree. He
 * contains the class of the node for a AVL binary tree, which include an
 * additional field as "Balance factor". Balancing can change when adding or removing, 
 * and is restored using the small left (right) or large left (right) turns.
 * @author DantalioNxxi
 * @version 1.0
 * @since 1.3
 * @see BinaryTree
 * @see Node
 */
public class AVLTree extends BinaryTree {

    /**
     * The class of the node for a AVL binary tree.
     */
    private static class Node {

        /**
         * Key of the npde.
         */
        int key;
        /**
         * Value ot the node.
         */
        int value;
        /**
         * Balance factor
         */
        int b;
        /**
         * Height of the Node
         */
        int h;
        /**
         * Balance of the Node
         */
        int balance;

        /**
         * References to left and right sub-node as well as to his parent node.
         */
        Node left, right, parent;

        /**
         * Constructor - create a new
         *
         * @param key key of the node
         * @param value value of the node
         * @param parent reference to parent of the new Node
         */
        private Node(int key, int value, Node parent) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.parent = parent;
            this.h = 1;
            this.balance = 0;
        }

        /**
         * Constructor - create a new node.
         *
         * @param key key of the node
         * @param value value of the node Left and Right sub-node is null by
         * default.
         */
        private Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Prints the node. If the Node is not exists, then print "-", else
         * print him key and value throught ":"
         *
         * @return Node as string "key:value", else "-" for the null-node.
         * @see Node#toString(boolean)
         */
        @Override
        public String toString() {
            if (this == null) {
                return ("-");
            }
            return (Integer.toString(this.key) + ":" + Integer.toString(this.value));
        }

        /**
         * Prints the node.
         *
         * @param keys If true, print with keys, else print only value.
         * @return If true, print the null-node as "-:-", else print "-".
         * @see Node#toString()
         */
        private String toString(boolean keys) {
            if (this == null) {
                if (keys) {
                    return ("-:-");
                } else {
                    return "-";
                }
            }
            if (keys) {
                return (Integer.toString(this.key) + ":" + Integer.toString(this.value));
            } else {
                return (Integer.toString(this.value));
            }
        }

    }

    /**
     * Reference to root-node of The Tree
     */
    private Node root;

    /**
     * Checks that this Tree contains a node with key k.
     * @param k key will help to search a node
     * @return true if this Tree contains a node with key k
     */
    @Override
    public boolean containsKey(int k) {
        Node x = root;
        while (x != null) {
            if (k == x.key) {
                return true;
            }
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return false;
    }

    /**
     * Gives value of the node with key k
     *
     * @param k key will help to search the node
     * @return value of the node with key k
     * @throws IllegalArgumentException if the node with key k is not exists
     */
    @Override
    public int getValue(int k) throws IllegalArgumentException {
        if (!this.containsKey(k)) {
            System.out.println("Узла с ключём " + k + " не существует.\n");
            throw new IllegalArgumentException();
        }
        Node x = root;
        while (x != null) {
            if (k == x.key) {
                return x.value;
            }
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return 0;
    }

    /**Gives a reference to node with key k
     * @param k key will help to search a node
     * @return node owner of the key k.
     */
    private Node getNode(int k) {
        Node x = root;
        while (x != null) {
            if (k == x.key) {
                return x;
            }
            if (k < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return null;
    }

    /**
     * Return a node with less key for this Sub-Tree.
     * @param node root of the sub-tree
     * @return the node with min key in this sub-tree.
     */
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    /**
     * Return a node with larger key for this Sub-Tree.
     * @param node root of the sub-tree
     * @return the node with max key in this sub-tree.
     */
    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }
    
    /**
     * Compare the heights of the x and y Nodes. Usually they left and right
     * nodes of the general parent.
     * @param x node 1 (left)
     * @param y node 2 (right)
     * @return height that larger.
     */
    private int height(Node x, Node y) {
        if (x == null && y == null) {
            return 0;
        } else if (x == null) {
            return y.h;
        } else if (y == null) {
            return x.h;
        } else {
            return Math.max(x.h, y.h);
        }
    }
    
    /**
     * Sets a new value for a node with key k.
     * Uses a methods containsKey(), add().
     * @param k key of the node, whose value will changed
     * @param newv new value if the node
     * @throws IllegalArgumentException if such node is not exist
     * @see AVLTree#containsKey(int) 
     * @see AVLTree#add(int, int) 
     */
    @Override
    public void set(int k, int newv) throws IllegalArgumentException {
        if (this.containsKey(k)) {
            this.add(k, newv);
        } else {
            System.out.println("Узла с ключём " + k + " не существует.\n");
            throw new IllegalArgumentException();
        }
    }

    /**
     * Is calculate a balance factor for the node with x and y,
     * where x-root-of-the-left-subTree, y-root-of-the-right-subTree.
     * The balance factor lies in the range from -2 to +2,
     * the AVL tree of other factors can not be
     * @param x the left sub-tree for the node
     * @param y the right sub-tree for the node
     * @return + if leftSubTree is higher, - if rightSubTree is higher.
     */
    private int balance(Node x, Node y) {
        if (x == null && y == null) {
            return 0;
        } else if (x == null) {
            return -y.h;
        } else if (y == null) {
            return x.h;
        } else {
            return x.h - y.h;
        }
    }
    
    /**
     * Adds the node into this BinaryTree.
     * If the node with such key is exist already, then him assign new value v.
     * Uses the method addForRoot.
     * @param key key of the added node
     * @param value value of the added node
     * @see AVLTree#addForRoot(workwithtrees.AVLTree.Node, int, int, workwithtrees.AVLTree.Node) 
     */
    @Override
    public void add(int key, int value) {
        root = addForRoot(root, key, value, null);
    }
    
    /**
     * Add a new node with "key" and "value" for a tree with root "node", which has
     * a parent "father".
     * Uses the methods balance, leftRotation and rightRotation for set of the
     * balancing.
     * Used by the method add (for full three by default).
     * @param node root of sub-tree where will be insert a new node
     * @param key of the new node
     * @param value of the new node
     * @param father for the root of sub-tree
     * @return root of the sub-tree which is contain a new node and is balancing.
     * @see AVLTree#add(int, int) 
     * @see AVLTree#balance(workwithtrees.AVLTree.Node, workwithtrees.AVLTree.Node) 
     * @see AVLTree#leftRotation(workwithtrees.AVLTree.Node) 
     * @see AVLTree#rightRotation(workwithtrees.AVLTree.Node) 
     */
    private Node addForRoot(Node node, int key, int value, Node father) {
        if (node == null) {
            Node newnode = new Node(key, value, father);
            return newnode;
        }
        if (key > node.key) {
            node.right
                    = addForRoot(node.right, key, value, node);
            //Calculate the height for the node in consider a new node.
            node.h = height(node.left, node.right) + 1;
        } else if (key < node.key) {
            node.left
                    = addForRoot(node.left, key, value, node);
            node.h = height(node.left, node.right) + 1;
        } else {
            node.value = value;
        }
        //Check a balance factor for exit of recursion.
        node.balance = balance(node.left, node.right);
        if (node.balance == -2) {
            node = leftRotation(node);
        } else if (node.balance == 2) {
            node = rightRotation(node);
        }
        return node;
    }

    /**
     * From right to left rotation.
     * Used by the methods addForRoot and removeForRoot.
     * @param node around which will be to do right to left rotation.
     * @return the node which will stay on his place.
     * @see AVLTree#addForRoot(workwithtrees.AVLTree.Node, int, int, workwithtrees.AVLTree.Node) 
     * @see AVLTree#removeForRoot(workwithtrees.AVLTree.Node, int) 
     */
    private Node leftRotation(Node node) {
        if (node.right.right == null && node.right.left != null) {
            //If necessary the small right-to-left rotation
            node.right = rightRotation(node.right);
            node = leftRotation(node);
        } else if (node.right.left == null || node.right.left.h <= node.right.right.h) {
            Node newnode = node.right;
            newnode.parent = node.parent;
            node.right = newnode.left;
            if (node.right != null) {
                node.right.parent = node;
            }
            node.h = height(node.left, node.right) + 1;
            node.parent = newnode;
            node.balance = balance(node.left, node.right);
            newnode.left = node;
            node = newnode;
            node.balance = balance(node.left, node.right);
            node.h = height(node.left, node.right) + 1;
        } else {
            //If necessary the small right-to-left rotation
            node.right = rightRotation(node.right);
            node = leftRotation(node);
        }
        return node;
    }

    /**
     * From left to right rotation.
     * Used by the methods addForRoot and removeForRoot.
     * @param node around which will be to do left to right rotation.
     * @return the node which will stay on his place.
     * @see AVLTree#addForRoot(workwithtrees.AVLTree.Node, int, int, workwithtrees.AVLTree.Node) 
     * @see AVLTree#removeForRoot(workwithtrees.AVLTree.Node, int) 
     */
    private Node rightRotation(Node node) {
        if (node.left.right != null && node.left.left == null) {
            //If necessary the small left-to-right rotation
            node.left = leftRotation(node.left);
            node = rightRotation(node);
        } else if (node.left.right == null || node.left.right.h <= node.left.left.h) {
            Node newnode = node.left;
            newnode.parent = node.parent;
            node.left = newnode.right;
            if (node.left != null) {
                node.left.parent = node;
            }
            node.h = height(node.left, node.right) + 1;
            node.parent = newnode;
            node.balance = balance(node.left, node.right);
            newnode.right = node;
            node = newnode;
            node.balance = balance(node.left, node.right);
            node.h = height(node.left, node.right) + 1;
        } else {
            //If necessary the small left-to-right rotation
            node.left = leftRotation(node.left);
            node = rightRotation(node);
        }
        return node;
    }

    /**
     * Removes a node with key k.
     * Uses the method removeForRoot.
     * @param k key of potential removed node
     * @throws IllegalArgumentException if a node with such key is not exist
     * @see AVLTree#removeForRoot(workwithtrees.AVLTree.Node, int) 
     */
    @Override
    public void remove(int k) {
        if (root == null) {
            System.out.println("Дерево ещё не создано!");
            return;
        }
        root = removeForRoot(this.root, k);
    }
    
    /**
     * For removing node for the subTree with root node"".
     * After the removal, it is necessary to check the balancing index of
     * each of the nodes, and if necessary, balance.
     * Used by the overriding method remove (for full AVLThree by default).
     * @param node root of sub-tree where will be removed a node
     * @param key of the node which will be removed
     * @return the node which will stay on his place (or new root for full tree).
     * @see AVLTree#min(workwithtrees.AVLTree.Node) 
     * @see AVLTree#remove(int) 
     * @see AVLTree#leftRotation(workwithtrees.AVLTree.Node) 
     * @see AVLTree#rightRotation(workwithtrees.AVLTree.Node)
     * 
     */
    private Node removeForRoot(Node node, int key) {
        if (node == null) {
            System.out.println("Узла с ключём " + key + " не существует.");
            return null;
        }
        if (key > node.key) {
            node.right = removeForRoot(node.right, key);
        } else if (key < node.key) {
            node.left = removeForRoot(node.left, key);
        } else {
            //Put the replacement in place of the remote node
            if (node.right == null && node.left == null) {
                node = null;
            } else if (node.right == null) {
                node.left.parent = node.parent;
                node = node.left;
            } else if (node.left == null) {
                node.right.parent = node.parent;
                node = node.right;
            } else {
                //Finds the node, which we insert in place of the removed node
                if (node.right.left == null) {
                    node.right.left = node.left;
                    node.right.parent = node.parent;
                    node.right.parent = node.parent;
                    node.left.parent = node.right;
                    node = node.right;
                } else {
                    Node res = min(node.right);
                    node.key = res.key;
                    node.value = res.value;
                    removeForRoot(node.right, node.key);
                }
            }
        }
        //Doing balancing
        if (node != null) {
            node.h = height(node.left, node.right) + 1;
            node.balance = balance(node.left, node.right);
            if (node.balance == -2) {
                node = leftRotation(node);
            } else if (node.balance == 2) {
                node = rightRotation(node);
            }
        }
        return node;
    }

    /**
     * Calculates the depth of a sub-tree for a node p.
     * @param p reference to node, which will be a root for the sub-tree
     * @return the depth of sub-tree for the node p
     */
    private int depth(Node p) {
//        Node temp=p;
        int h1 = 0, h2 = 0;
        if (p == null) {
            return 0;
        }
        if (p.left != null) {
            h1 = depth(p.left);
        }
        if (p.right != null) {
            h2 = depth(p.right);
        }
        return (Math.max(h1, h2) + 1);
    }

    /**
     * Is calculte the full depth of the Tree.
     * @return a depth of the Tree
     */
    @Override
    public int depth() {
        return depth(root);
    }

    /**
     * Fills the pyramid by only keys of The Binary Tree.
     * Used by the method printOnlyKeys()
     * @param node node of the Binary Tree, from which the pyramid will be filled
     * @param level of the pyramid, from which the method will fill this pyramid
     * @param pyramid which will be filled by key of the nodes in String type of the Binary Tree
     * @see AVLTree#printOnlyKeys() 
     */
    private void fillPirOnlyKeys(Node node, int level, ArrayList<StringBuilder> pyramid) {
        if (node != null) {
            pyramid.get(level).append(node.key + " ");
            fillPirOnlyKeys(node.left, level + 1, pyramid);
            fillPirOnlyKeys(node.right, level + 1, pyramid);
        } else {
            if (level < this.depth()) {
                String childStr = "-";
                pyramid.get(level).append(childStr + " ");
                for (int j = level + 1, amountchild = 2; j < this.depth(); j++) {
                    for (int r = 0; r < amountchild; r++) {
                        pyramid.get(j).append(childStr + " ");
                    }
                    amountchild *= 2;
                }
            }
        }
    }

    /**
     * Prints the AVL Tree with only keys in the compressed form of the pyramid.
     * Uses the method fillPirOnlyKeys()
     * java.util.ArrayList)
     */
    public void printOnlyKeys() {
        System.out.println("=======================");
        System.out.println("Глубина дерева: " + (this.depth() - 1) + "\n");
        //Create the pyramid
        ArrayList<StringBuilder> pyr = this.generatePyramid();
        //Fill pyramid
        fillPirOnlyKeys(root, 0, pyr);
        //Print keys of the tree only:
        int s = 1;
        for (int l = 0; l < this.depth(); l++) {
            StringBuilder str = indention(false, s, Indent.COMPRESS);
            System.out.print(str.toString() + pyr.get(l).toString() + "\n");
            s *= 2;
        }
        System.out.println("=======================");
    }

    //End of the class.
}
