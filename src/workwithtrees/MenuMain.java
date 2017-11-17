
package workwithtrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of the Main Menu for work with any Tree.
 * Object is launched by method run().
 * An one menu entry added into array of entries by anonymous class,
 * which implements an abstract class MenuEntry.
 * @author DantalioNxxi
 * @version 1.3
 * @since 1.0
 * @see MenuMain
 * @see BinaryTree
 * @see MenuEntry
 */
class MenuMain {

    /**Array of menu items*/
    private List<MenuEntry> entries = new ArrayList<MenuEntry>();
    private boolean isExit = false;

    public MenuMain() {
        /**The Binary Tree, with which will be worked the user.*/
        BinaryTree tree = new BinaryTree();

        entries.add(new MenuEntry("1. Сформировать бинарное дерево") {
            @Override
            public void run() {
                TreeMenu menutree = new TreeMenu(tree);
                menutree.run();
            }
        });

        entries.add(new MenuEntry("2. Нарисовать дерево с ключами") {
            @Override
            public void run() {
                if (tree.depth() == 0) {
                    System.out.println("Сначала сформируйте дерево!\n");
                } else {
                    tree.printInAllVariants(true);
                }
            }
        });

        entries.add(new MenuEntry("3. Нарисовать дерево без ключей") {
            @Override
            public void run() {
                if (tree.depth() == 0) {
                    System.out.println("Сначала сформируйте дерево!\n");
                } else {
                    tree.printInAllVariants(false);
                }
            }
        });
        //Adds the menu entry Exit
        entries.add(new MenuEntry("4. Выход") {
            @Override
            public void run() {
                isExit = true;
            }
        });

    }

    /**
     * Launches the Main Menu.
     * @see MenuMain
     */
    public void run() {
        // Endless cycle, while button does not push - isExit = true
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                // Is launches that menu entrie, which was chosen by the user.
                MenuEntry entry = entries.get(choice - 1);
                entry.run();
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                System.out.println("Ошибка! Вызвано исключение: " + ex.toString() + "\nНекорректно введён пункт меню!"
                        + "\nПопробуйте ввести снова.\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Prints the Main Menu.
     * @see MenuMain
     */
    public void printMenu() {
        System.out.println("1. Сформировать бинарное дерево\n"
                + "2. Нарисовать дерево с ключами\n"
                + "3. Нарисовать дерево без ключей\n"
                + "4. Выход\n");
    }
}
