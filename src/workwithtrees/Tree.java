
package workwithtrees;

/**
 * The abstract class of the any Tree.
 * @author DantalioNxxi
 * @version 1.0
 * @since 1.3
 * @see BinaryTree
 */
public abstract class Tree {
        /**
         * Gets the value by key.
         * @param k key will help to search the node
         * @return value of the node with key k
         */
        public abstract int getValue(int k);
        
        /**
         * Checks contain of the node with key k into the tree.
         * @param k will help to search a node
         * @return true if the Tree contains a node with key k
         */
        public abstract boolean containsKey(int k);

        /**
         * Adds the node into the Tree
         * @param k key of the added node
         * @param v value of the added node
         */
        public abstract void add(int k, int v);
        
        /**
         * Adds a new sub-node kc with value vc toward a node kp.
         * @param kp node, which get a new sub-node
         * @param kc node, which will add to kp
         * @param vc value of the new sub-node
         */
        public abstract void addChild(int kp, int kc, int vc);
        
        /**
         * Adds a new root for tree.
         * @param key key of new root
         * @param value value of new root
         */
        public abstract void addNewRoot(int key, int value);

        /**
         * Sets a new value for a node with key k.
         * @param k key of the node, whose value will changed
         * @param newv new value if the node
         */
        public abstract void set(int k, int newv);
        
        /**
         * Removes a node with key k.
         * @param k key of removed node
         */
        public abstract void remove(int k);

        /**
         * Is calculte the depth of the Tree.
         * @return a depth of the Tree
         */
        public abstract int depth();
}
