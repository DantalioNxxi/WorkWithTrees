package workwithtrees;

import static java.lang.Math.pow;
import java.util.ArrayList;

/**
 * The abstract class of the any Binary Tree.
 *
 * @author DantalioNxxi
 * @version 1.1
 * @since 1.3
 * @see BinarySearchTree
 * @see AVLTree
 */
public abstract class BinaryTree {

    /**
     * The variable is contains are variants of the calculation indents. Use by
     * method indention().
     * @see BinaryTree#indention(boolean, int, workwithtrees.BinaryTree.Indent) 
     */
    protected static enum Indent {
        COMPRESS, COUPLE, LEFT, RIGHT
    }

    /**
     * Gets the value by key.
     *
     * @param k key will help to search the node
     * @return value of the node with key k
     */
    public abstract int getValue(int k);

    /**
     * Checks contain of the node with key k into the tree.
     *
     * @param k will help to search a node
     * @return true if the Tree contains a node with key k
     */
    public abstract boolean containsKey(int k);

    /**
     * Adds the node into the Tree
     *
     * @param k key of the added node
     * @param v value of the added node
     */
    public abstract void add(int k, int v);

    /**
     * Sets a new value for a node with key k.
     *
     * @param k key of the node, whose value will changed
     * @param newv new value if the node
     */
    public abstract void set(int k, int newv);

    /**
     * Removes a node with key k.
     *
     * @param k key of removed node
     */
    public abstract void remove(int k);

    /**
     * Is calculte the depth of the Tree.
     *
     * @return a depth of the Tree
     */
    public abstract int depth();

    /**
     * Is calculated are indents for the correct display of such elemens, as the
     * nodes in the printCompressPyr(), and the LEFT or RIGHT nodes, as well as
     * indents after them COUPLE, which used by such method, as printTree(),
     * printOnlyKeys().
     *
     * @param keys true, if the nodes prints with keys (an indents must be
     * larger)
     * @param i current level of the pyramid
     * @param element after which will calculate the indent
     * @return indent
     * @see BinarySearchTree#printCompressPyr(java.util.ArrayList, boolean) s
     * @see BinarySearchTree#printTree(java.util.ArrayList, boolean)
     * @see BinarySearchTree#printOnlyKeys()
     */
    protected StringBuilder indention(boolean keys, int i, Indent element) {
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
     * Is generates a pyramid of the Binary Tree. Fill his levels by empty line
     * Used by methods printInAllVariants, printOnlyKeys in BinarySearchTree.
     * Used by methods printOnlyKeys in AVLTree.
     * @return StringBuilder List pyramid from empty lines by size at depth of
     * the Tree.
     * @see BinarySearchTree#printInAllVariants(boolean)
     * @see BinarySearchTree#printOnlyKeys()
     * @see AVLTree#printOnlyKeys() 
     */
    protected ArrayList<StringBuilder> generatePyramid() {
        //Create the pyramid
        ArrayList<StringBuilder> pyramid = new ArrayList<StringBuilder>();
        //Initialize the levels of the pyramid
        for (int i = 0; i < this.depth(); i++) {
            pyramid.add(i, new StringBuilder(""));
        }
        return pyramid;
    }
    
    //End of the class
}
