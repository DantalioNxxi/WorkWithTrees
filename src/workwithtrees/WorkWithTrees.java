
package workwithtrees;

import java.io.IOException;

/**
 * The main class for work with any Trees.
 * @author DantalioNxxi
 * @version 1.3
 * @since 1.0
 * @see MenuMain
 * @see BSTreeMenu
 * @see BinaryTree
 * @see AVLTree
 */
public class WorkWithTrees {

    /**
     * The main method for work with trees.
     * @param args the command line arguments
     * @throws IOException if will be a critical error at work of the user
     */
    public static void main(String[] args) throws IOException{
        // Create and launch the Main Menu
        MenuMain menu = new MenuMain();
        menu.run();
        
    }
}

