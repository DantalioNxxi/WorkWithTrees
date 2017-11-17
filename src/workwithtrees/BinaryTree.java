
package workwithtrees;

import java.util.ArrayList;
import static java.lang.Math.pow;

/**
 * Class of the Binary Tree.
 * This class inherit the class Tree.
 * He contains the class of the node for a binary tree.
 * @author DantalioNxxi
 * @version 1.3
 * @since 1.0
 * @see Tree
 * @see Node
 */
public class BinaryTree extends Tree {

    /**
     * The variable is contains are variants of the calculation indents.
     * Use by method indention().
     * @see BinaryTree#indention(boolean, int, workwithtrees.BinaryTree.Indent) 
     */
    private static enum Indent {COMPRESS, COUPLE, LEFT, RIGHT}
    
    /**
     * The class of the node for a binary tree.
     */
    private static class Node {
        
        /** Key of the npde.*/
        int key;
        /**Value ot the node.*/
        int value;
        /**References to left and right sub-node.*/
        Node left, right;
        
        /**Constructor - create a new node
         * @param key key of the node
         * @param value value of the node
         * Left and Right sub-node is null by default.
         */
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        /**Prints the node.
         * If the Node is not exists, then print "-",
         * else print him key and value throught ":"
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
         * @param keys If true, print with keys, else print only value.
         * @return If true, print the null-node as "-:-", else print "-".
         * @see Node#toString() 
         */
        public String toString(boolean keys) {
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
        
        /**
         * Checks that this node contains sub-node with key kc.
         * @param kc a potential key of the anything sub-node
         * @return if the node is contain the sub-node with key kc.
         */
        public boolean containChild(int kc) {
            if (this.left != null && this.left.key == kc) {
                return true;
            }
            if (this.right != null && this.left.key == kc) {
                return true;
            }
            return false;
        }
    }

    /**Reference to root-node of Tree*/
    private Node root;

    /**Gives a reference to node with key k
     * @param k key will help to search a node
     * @return node owner of the key k.
     */
    public Node getNode(int k) {
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
     * Gets the parent of a node with key "keyChild".
     * If the parent does not found, then return null.
     * @param keyChild for which will be search the parent
     * @return link to the parent Node
     */
    private Node getParent(int keyChild){
        Node x = root, parent = root;
        while (x != null) {
            if (keyChild == x.key) {
                return parent;
            }
            if (keyChild < x.key) {
                parent = x;
                x = x.left;
            } else {
                parent = x;
                x = x.right;
            }
        }
        return null;
    }
    
    /**
     * Checks is to be the left sub-node for him parent.
     * @param key key of assumed the left sub-node for him parent
     * @return true, if the node is a the left sub-node for him parent.
     * @see BinaryTree#rotationToLeft(int) 
     * @see BinaryTree#rotationToRight(int) 
     */
    public boolean isLeftSubNode(int key) {
        if (this.getNode(key) == this.getParent(key).left) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks is to be the right sub-node for him parent.
     * @param key key of assumed the right sub-node for him parent
     * @return true, if the node is a the right sub-node for him parent.
     * @see BinaryTree#rotationToLeft(int) 
     * @see BinaryTree#rotationToRight(int)
     */
    public boolean isRightSubNode(int key) {
        if (this.getNode(key) == this.getParent(key).right) {
            return true;
        }
        return false;
    }
    
    /**
     * Gives value of the node with key k
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

    /**
     * Checks that this Tree contains a node with key k
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
     * Adds the node into this BinaryTree.
     * If the node with such key is exist already, then him assign new value v
     * @param k key of the added node
     * @param v value of the added node
     */
    @Override
    public void add(int k, int v) {
        Node x = root, y = null;
        while (x != null) {
            if (k == x.key) {
                x.value = v;
                return;
            } else {
                y = x;
                if (k < x.key) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        Node newNode = new Node(k, v);
        if (y == null) {
            root = newNode;
        } else {
            if (k < y.key) {
                y.left = newNode;
            } else {
                y.right = newNode;
            }
        }
    }
    
    /**
     * Checks that is possible to add a node keyChild toward a node keyParent.
     * Uses by the method addChild()
     * @param keyParent node, which get a new sub-node
     * @param keyChild node, which will add to keyParent
     * @return true, if the addition is possible
     * @see BinaryTree#addChild(int, int, int) 
     */
    private boolean isAllowedInsert (int keyParent, int keyChild) {
        if (!this.containsKey(keyParent)) {
            System.out.println("Узла с ключём " + keyParent + " не существует.\n");
            return false;
        }
        
        if (this.containsKey(keyChild)){
            if (!getNode(keyParent).containChild(keyChild)){
                System.out.println("Узел с ключём " + keyChild + " уже есть в дереве.\n");
                return false;
            }
        }
        //The first check for reach to node
        Node x = root;
        boolean isAllow = false;
        while (x != null) {
            if (keyParent == x.key) {
                isAllow = true;
                break;
            } else if (keyChild < x.key) {
                x = x.left;
            } else if (keyChild > x.key) {
                x = x.right;
            }
        }
        if (isAllow==false) {
            System.out.println("Узел не достижим! Нелья добавить такой узел!");
            return isAllow;
        }
        //The second check for satisfying the rule of a binary tree
        if (keyChild==keyParent){
            return true;
        } else {
            //If the new node will be left
            if (keyChild<keyParent){
                if (getNode(keyParent).left!=null){
                    if (getNode(keyParent).left.key<keyChild){ //Key leaves left
                        
                        x = getNode(keyParent).left.right;
                        //keyChild must be less than all the elements on the right!
                        while (x != null) {
                            if (keyChild < x.key) {
                                System.out.println("Нелья добавить такой узел!");
                                return false;
                            } else{
                                    x = x.right;
                            }
                        }
                        return true;
                        
                    } else if (getNode(keyParent).left.key>keyChild) { //Key leaves right
                        
                        x = getNode(keyParent).left.left;
                        //keyChild must be less than all the elements on the left!
                        while (x != null) {
                            if (keyChild > x.key) {
                                System.out.println("Нелья добавить такой узел!");
                                return false;
                            } else{
                                    x = x.left;
                            }
                        }
                        return true;
                        
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
                //If the new node will be right
            } else {
                if (getNode(keyParent).right!=null){
                    if (getNode(keyParent).right.key<keyChild){ //Key leaves left
                        
                        x = getNode(keyParent).right.right;
                        //keyChild must be less than all the elements on the right!
                        while (x != null) {
                            if (keyChild < x.key) {
                                System.out.println("Нелья добавить такой узел!");
                                return false;
                            } else{
                                    x = x.right;
                            }
                        }
                        return true;
                        
                    } else if (getNode(keyParent).right.key>keyChild) { //Key leaves right
                        
                        x = getNode(keyParent).right.left;
                        //keyChild must be less than all the elements on the left!
                        while (x != null) {
                            if (keyChild > x.key) {
                                System.out.println("Нелья добавить такой узел!");
                                return false;
                            } else{
                                    x = x.left;
                            }
                        }
                        return true;
                        
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        
    }
    
    /**
     * Adds a new sub-node kc with value vc toward a node kp.
     * Uses the method isAllowInsert(), genNode()
     * @param kp node, which get a new sub-node
     * @param kc node, which will add to kp
     * @param vc value of the new sub-node
     * @throws IllegalArgumentException if the addition is not permited
     * @see BinaryTree#isAllowedInsert(int, int) 
     * @see BinaryTree#getNode(int) 
     */
    @Override
    public void addChild (int kp, int kc, int vc) throws IllegalArgumentException{
        //If the insertion of the such node is not permitted, then throw Exception
        if (!this.isAllowedInsert(kp, kc)) {
            throw new IllegalArgumentException();
        }
        
        Node y = null;
        Node newNode = new Node(kc, vc);
        
        if (kc==kp){
            getNode(kp).value = vc;
        } else {
            if (kc<kp){
                if (getNode(kp).left!=null){
                    y = getNode(kp).left;
                    if (getNode(kp).left.key<kc){ //Key leaves left
                        
                        getNode(kp).left = newNode;
                        getNode(kp).left.left = y;
                        
                    } else if (getNode(kp).left.key>kc) {
                        
                        getNode(kp).left = newNode;
                        getNode(kp).left.right = y;
                        
                    } else {
                        getNode(kp).left.value = vc;
                    }
                } else {
                    getNode(kp).left = newNode;
                }
            } else {
                if (getNode(kp).right!=null){
                    y = getNode(kp).right;
                    if (getNode(kp).right.key<kc){
                        
                        getNode(kp).right = newNode;
                        getNode(kp).right.left = y;
                        
                    } else if (getNode(kp).right.key>kc) {
                        
                        getNode(kp).right = newNode;
                        getNode(kp).right.right = y;
                        
                    } else {
                        getNode(kp).right.value = vc;
                    }
                } else {
                    getNode(kp).right = newNode;
                }
            }
        }
    }
    
    /**
    * Adds a new root for this Binary Tree.
    * Uses the methods add, containsKey and insertRoot.
    * @param newKey key of new root
    * @param newValue value of new root
    * @see BinaryTree#add(int, int) 
    * @see BinaryTree#containsKey(int) 
    * @see BinaryTree#insertRoot(workwithtrees.BinaryTree.Node, int, int) 
    */
    @Override
    public void addNewRoot(int newKey, int newValue){
        if (root == null){//If the Binary Tree is empty
            add(newKey, newValue);
            return;
        }
        
        if (this.containsKey(newKey)&&newKey==root.key){
            root.value = newValue;//If the node with key "newKey" already is root
        } else {
            insertRoot(root, newKey, newValue);
        }
    }

     /**
     * Recursive method for insertion a new root.
     * Firstly the recirsion go down to the node (newKey, newValue),
     * after get up by means of rotations, which to bring up this node.
     * Uses the methods rotationToRight() and rotationToLeft().
     * Used by the method addNew().
     * @param h old root of the tree(sub-tree)
     * @param newKey a new key of a new root
     * @param newValue a new value of a new root
     * @see BinaryTree#addNewRoot(int, int) 
     * @see BinaryTree#rotationToLeft(int) 
     * @see BinaryTree#rotationToRight(int) 
     */
    private void insertRoot(Node h, int newKey, int newValue) {
        if (h == null) {
            return; //Final of the recursion
        }

        if (newKey < h.key) {
            if (h.left == null) {
                insertRoot(h.left, newKey, newValue);//Finish the recursion
                h.left = new Node(newKey, newValue);//Create a new Root
                rotationToRight(h.key);
            } else {
                insertRoot(h.left, newKey, newValue);
                rotationToRight(h.key);
            }
        } else if (newKey > h.key) {
            if (h.right == null) {
                insertRoot(h.right, newKey, newValue);//Finish the recursion
                h.right = new Node(newKey, newValue);//Create a new Root
                rotationToLeft(h.key);
            } else {
                insertRoot(h.right, newKey, newValue);
                rotationToLeft(h.key);
            }
        } else {
            h.value = newValue; // Final of the recursion. The key already in this Binary Tree.
        }
    }
    
    /**
     * Rotation make the old node is right sub-tree of new root (old left sub-tree of old root)..
     * Usually applies for the left sub-nodes. The old root leaves to down to right.
     * @param key whose node must be turn up to right / leaves to down to right.
     * @see BinaryTree#insertRoot(workwithtrees.BinaryTree.Node, int, int) 
     */
    private void rotationToRight(int key) {
        Node x = this.getNode(key).left;
        this.getNode(key).left = x.right;
        x.right = this.getNode(key);

        if (this.getNode(key) == root) {
            root = x;
        } else if (this.isLeftSubNode(key)) {
            this.getParent(key).left = x;
        } else { //if isRightSubNode
            this.getParent(key).right = x;
        }
    }
    
    /**
     * Rotation make the old node is left sub-tree of new root (old right sub-tree of old root).
     * Usually applies for the right sub-nodes. The old root leaves to down to left.
     * @param key whose node must be turn up to left / leaves to down to left.
     * @see BinaryTree#insertRoot(workwithtrees.BinaryTree.Node, int, int) 
     */
    public void rotationToLeft(int key) {
        Node x = this.getNode(key).right;
        this.getNode(key).right = x.left;
        x.left = this.getNode(key);

        if (this.getNode(key) == root) {
            root = x;
        } else if (this.isLeftSubNode(key)) {
            this.getParent(key).left = x;
        } else { //if isRightSubNode
            this.getParent(key).right = x;
        }
    }
    
    /**
     * Sets a new value for a node with key k.
     * Uses a methods containsKey(), add().
     * @param k key of the node, whose value will changed
     * @param newv new value if the node
     * @throws IllegalArgumentException if such node is not exist
     * @see BinaryTree#containsKey(int) 
     * @see BinaryTree#add(int, int) 
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
     * Removes a node with key k.
     * On the removed node's position will stay a node, whose key is least larger, than removed node's key.
     * If a node with such key is not exist, then this tree will not chainged, but throw the exception
     * @param k key of potential removed node
     * @throws IllegalArgumentException if a node with such key is not exist
     */
    @Override
    public void remove(int k) throws IllegalArgumentException {
        if (!this.containsKey(k)) {
            System.out.println("Узла с ключём " + k + " не существует.\n");
            throw new IllegalArgumentException();
        }
        Node x = root, y = null;
        while (x != null) {
            if (k == x.key) {
                break;
            } else {
                y = x;
                if (k < x.key) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
        }
        if (x == null) {
            return;
        }
        if (x.right == null) {
            if (y == null) {
                root = x.left;
            } else {
                if (x == y.left) {
                    y.left = x.left;
                } else {
                    y.right = x.left;
                }
            }
        } else {
            Node leftMost = x.right;
            y = null;
            while (leftMost.left != null) {
                y = leftMost;
                leftMost = leftMost.left;
            }
            if (y != null) {
                y.left = leftMost.right;
            } else {
                x.right = leftMost.right;
            }
            x.key = leftMost.key;
            x.value = leftMost.value;
        }
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
     * A method-wrapper for the calculting of the depth for a root node of this Binary Tree.
     * Uses a methid depth(Node p)
     * @return a depth for this Binary Tree
     * @see BinaryTree#depth(workwithtrees.BinaryTree.Node) 
     */
    @Override
    public int depth() {
        return depth(root);
    }
    
    /**
     * Fills a pyramid from node for the tree.
     * A pyramid contains key and value of the nodes in String type for each level of the Binary Tree.
     * Uses the methods node.toString, BinaryTree.depth
     * @param node from which the pyramid will be filled
     * @param level of the pyramid, from which the method will fill the pyramid
     * @param pyramid which will filled by key and value of the nodes in String type of the Binary Tree
     * @param keys if true, then String type of the nod will contained a key
     * @see Node#toString() 
     * @see BinaryTree#depth() 
     */
    private void fillPir(Node node, int level, ArrayList<StringBuilder> pyramid, boolean keys) {
        //If the node is exist:
            if (node != null) {
                //Added a indent for the node
                pyramid.get(level).append(node.toString(keys) + " ");
                //Fills the left and right sub-nodes:
                fillPir(node.left, level + 1, pyramid, keys);
                fillPir(node.right, level + 1, pyramid, keys);
            } else {
                if (level < this.depth()) {
                    //Create chidrens in String type for the node as if they exists in depending on keys
                    String childStr;
                    if (keys){
                        childStr = "-:-";
                    } else{
                        childStr = "-";
                    }
                    //As if the node is exist
                    pyramid.get(level).append(childStr + " ");
                    //Adds the hypothetical childrens
                    for (int j = level + 1, amountchild = 2; j < this.depth(); j++) {
                        //Adds the childrens to pyramid
                        for (int r = 0; r < amountchild; r++) {
                            pyramid.get(j).append(childStr + " ");
                        }
                        //With each new level of the tree's depth the quantity of childrens doubled 
                        amountchild *= 2;
                    }
                }
            }
    }
    
    /**
     * Prints the nodes by levels.
     * @param  pyramid which was filled by key and value of the nodes in String type of the Binary Tree
     * @see BinaryTree#fillPir(workwithtrees.BinaryTree.Node, int, java.util.ArrayList, boolean) 
     */
    private void printLevels(ArrayList<StringBuilder> pyramid){
        System.out.println("Узлы по уровням:");
        for (StringBuilder i : pyramid) {
            System.out.println(i+" ");
        }
    }
    
    /**
     * Prints the pyramid of the Binary Tree in the compress form.
     * Uses the method indention to calculate an indents for the correct display.
     * @param pyramid which was filled by key and value of the nodes in String type of the Binary Tree.
     * @param keys if true, then String type of the nodes will contained a key, else only a value.
     * @see BinaryTree#fillPir(workwithtrees.BinaryTree.Node, int, java.util.ArrayList, boolean)
     * @see BinaryTree#indention(boolean, int, workwithtrees.BinaryTree.Indent) 
     */
    private void printCompressPyr(ArrayList<StringBuilder> pyramid, boolean keys){
        int s = (keys) ? 2 : 1;
        System.out.println("\nВ виде сжатой пирамиды:");
        for (int l = 0; l < this.depth(); l++) {
            StringBuilder str = indention(keys, s, Indent.COMPRESS);
            System.out.print(str.toString() + pyramid.get(l).toString()+"\n");
            s*=2;
        }
    }
    
    /**
     * Is calculated are indents for the correct display of such elemens, as the nodes in the printCompressPyr(),
     * and the LEFT or RIGHT nodes, as well as indents after them COUPLE, which used by such method, as printTree(),
     * printOnlyKeys().
     * @param keys true, if the nodes prints with keys (an indents must be larger)
     * @param i current level of the pyramid
     * @param element after which will calculate the indent
     * @return indent
     * @see BinaryTree#printCompressPyr(java.util.ArrayList, boolean) s
     * @see BinaryTree#printTree(java.util.ArrayList, boolean) 
     * @see BinaryTree#printOnlyKeys() 
     */
    private StringBuilder indention(boolean keys, int i, Indent element) {
        StringBuilder str = new StringBuilder("");
        switch (element) {
            case COMPRESS:
                for (int j = 0; j < (int) (pow(this.depth() + 1, 2) * 2) - i; j++) {
                    str.append(" ");
                }
                break;
            case COUPLE:
                for (int j = 0; j < (int) (pow(this.depth() + 1, 2) / (pow(2, i - 1))); j++) {
                    str.append(" ");
                }
                break;
            case LEFT:
                for (int j = 0; j <= ((int) (pow(this.depth() + 1, 2) / (pow(2, i))) - ((keys) ? (i - 3) : i)); j++) {
                    str.append(" ");
                }
                break;
            case RIGHT:
                for (int j = 0; j <= 2 * (int) (pow(this.depth() + 1, 2) / (pow(2, i)) - ((keys) ? (i - 2) : (i - 4))); j++) {
                    str.append(" ");
                }
                break;
        }
        return str;
    }
    
    /**
     * Prints the pyramid of the Binary Tree in the expanded form.
     * Firstly to get the massive elements of the pyramid.
     * After prints the elements and indentions (used the method indention()).
     * @param pyramid which was filled by key and value of the nodes in String type of the Binary Tree.
     * @param keys if true, then String type of the nodes will contained a key and value, else only a value.
     * @see BinaryTree#indention(boolean, int, workwithtrees.BinaryTree.Indent) 
     */
    private void printTree(ArrayList<StringBuilder> pyramid, boolean keys){
        System.out.print("В виде дерева:\n");
        for (int i = 0; i < this.depth(); i++) {
            //Get the array of elements of the pyramid at level i
            String[] mstr = pyramid.get(i).toString().split(" ");
            //Prints the elements and indentions
            for (int k = 0, left = 1, couple = 0; k < mstr.length; k++) {
                //If pair of children was passed, then calcilate indent-COUPLE
                if (couple == 2) {
                    couple = 1;
                    StringBuilder str = indention(keys, i, Indent.COUPLE);
                    System.out.print(str.toString() + mstr[k]);
                }
                //Else checks side of the child and calculate indent-LEFT/RIGHT
                else {
                    if (left > 0) {
                        System.out.print(indention(keys, i, Indent.LEFT) + mstr[k]);
                    } else {
                        System.out.print(indention(keys, i, Indent.RIGHT) + mstr[k]);
                    }
                    couple++;
                }
                left *= (-1);//Change a side of the node
            }
            System.out.println();
        }
    }
    
    /**
     * Prints the Binary Tree in the three forms
     * @param keys if true, then String type of the nodes will contained a key and value,
     * else only a value. Used by such methods, as fillPir(), printCompressPyr(),
     * and printTree().
     * @see BinaryTree#fillPir(workwithtrees.BinaryTree.Node, int, java.util.ArrayList, boolean) 
     * @see BinaryTree#printLevels(java.util.ArrayList) 
     * @see BinaryTree#printCompressPyr(java.util.ArrayList, boolean) 
     * @see BinaryTree#printTree(java.util.ArrayList, boolean) 
     */
    public void printInAllVariants(boolean keys) {
        System.out.println("=======================");
        System.out.println("Глубина дерева: " + (this.depth() - 1) + "\n");
        //Create the pyramid
        ArrayList<StringBuilder> pyr = new ArrayList<StringBuilder>();
        //Initialize the levels of the pyramid
        for (int i = 0; i < this.depth(); i++) {
            pyr.add(i, new StringBuilder(""));
        }
        //Fill the pyramid
        fillPir(root, 0, pyr, keys);
        //Print nodes without the indents:
        printLevels(pyr);
        //Print the tree in the form of a compressed pyramid:
        printCompressPyr(pyr, keys);
        //Print the tree in an expanded form:
        printTree(pyr, keys);
        System.out.println("=======================");
    }
    
    /**
     * Fills the pyramid by only keys of Binary Tree.
     * Used by the method printOnlyKeys()
     * @param node node of the Binary Tree, from which the pyramid will be filled
     * @param level of the pyramid, from which the method will fill this pyramid
     * @param pyramid which will be filled by key of the nodes in String type of the Binary Tree
     * @see BinaryTree#printOnlyKeys() 
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
     * Prints the Binary Tree with only keys in the compressed form of the pyramid.
     * Uses the method fillPirOnlyKeys()
     * @see BinaryTree#fillPirOnlyKeys(workwithtrees.BinaryTree.Node, int, java.util.ArrayList) 
     */
    public void printOnlyKeys() {
        System.out.println("=======================");
        System.out.println("Глубина дерева: " + (this.depth() - 1) + "\n");
        //Create the pyramid
        ArrayList<StringBuilder> pyr = new ArrayList<StringBuilder>();
        //Initialize the levels of the pyramid
        for (int i = 0; i < this.depth(); i++) {
            pyr.add(i, new StringBuilder(""));
        }
        //Fill pyramid
        fillPirOnlyKeys(root, 0, pyr);
        //Print keys of the tree only:
        int s = 1;
        for (int l = 0; l < this.depth(); l++) {
            StringBuilder str = indention(false, s, Indent.COMPRESS);
            System.out.print(str.toString() + pyr.get(l).toString()+"\n");
            s*=2;
        }
        System.out.println("=======================");
    }

}