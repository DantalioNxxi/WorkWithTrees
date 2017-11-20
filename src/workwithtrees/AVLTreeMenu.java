
package workwithtrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of the Menu for work with AVL Binary Tree.
 * Object is created and launched by method run() of the MenuMain.
 * An one menu entry added into array of entries by anonymous class,
 * which implements an abstract class MenuEntry.
 * @author DantalioNxxi
 * @version 1.0
 * @since 1.3
 * @see MenuMain
 * @see MenuEntry
 * @see AVLTree
 */
public class AVLTreeMenu {
    /**An array of the entries for the AVLTreeMenu*/
    private static final List <MenuEntry> ENTRIES = new ArrayList<MenuEntry>();
    /**If true, then running of the menu will be stoped*/
    private boolean isExit = false;
    
    /**
     * Splits a string of digits, which entered by the user
     * and return an array of digits.
     * @param x string of digits, which entered by the user
     * @return array of digits entered by the user
     */
    private static int[] splitter(String x) {
        String[] str = x.split(" ");
        int[] arr = new int[str.length];

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        
        return arr;
    }

    /**
     * Constructor of the AVLTreeMenu Menu.
     * Added menu entries into array of the entries and describes their
     * by the anonymous classes, which implements an abstract class MenuEntry.
     * @param tree is tree, with user will be worked and changed.
     * @see MenuEntry
     */
    public AVLTreeMenu(AVLTree tree) {
        ENTRIES.add(new MenuEntry("1. Добавить узел") {
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println("Введите ключ и значение узла через пробел: ");
                        BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                        String x = readernode.readLine();
                        
                        int[] kv = splitter(x);
                        if (kv.length>2){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        
                        tree.add(kv[0], kv[1]);
                        tree.printOnlyKeys();
                        break;
                    }
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        System.out.println("Ошибка! Вызвано исключение: "+ex.toString()+
                                "\nПопробуйте ввести данные снова.\n");
                    }
                    catch (IOException ex){
                        System.out.println("Фатальная ошибка во вводе данных!\n");
//                        ex.printStackTrace();
                        break;
                    }
                }
            }
        });
        
        ENTRIES.add(new MenuEntry("2. Изменить узел") {
            @Override
            public void run() {
                while(true){
                        System.out.println("Введите ключ и новое значение узла через пробел: ");
                        try {
                            BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                            String x = readernode.readLine();

                            int[] kv = splitter(x);
                            if (kv.length>2){
                                throw new ArrayIndexOutOfBoundsException();
                            }
                            
                            tree.set(kv[0], kv[1]);
//                            tree.printInAllVariants(true);
                            tree.printOnlyKeys();
                            break;
                        }
                        catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                            System.out.println("Ошибка! Вызвано исключение: "+ex.toString()+
                                    "\nПопробуйте ввести данные снова.\n");
                        }
                        catch (IllegalArgumentException ex){
                            break;
                        }
                        catch (IOException e) {
                            System.out.println("Фатальная ошибка во вводе данных!\n");
//                            e.printStackTrace();
                            break;
                        }
                }
            }
        });
        
        ENTRIES.add(new MenuEntry("3. Удалить узел") {
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println("Введите ключ узла: ");
                        BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                        String x = readernode.readLine();

                        int k = Integer.parseInt(x);

                        tree.remove(k);
                        tree.printOnlyKeys();
                        break;
                        }
                    catch (NumberFormatException ex){
                        System.out.println("Ошибка! Вызвано исключение: "+ex.toString()+
                                "\nПопробуйте ввести данные снова.\n");
                    }
                    catch (IllegalArgumentException ex){
                        break;
                    }
                    catch (IOException e) {
                        System.out.println("Фатальная ошибка во вводе данных!\n");
//                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        
        ENTRIES.add(new MenuEntry("4. Нарисовать дерево только с ключами") {
            @Override
            public void run() {
                if (tree.depth()==0){
                    System.out.println("Сначала добавьте в дерево узлы!\n");
                }
                else{
//                    tree.printInAllVariants(true);
                    tree.printOnlyKeys();
                }
                //
                System.out.println("change and here to true");
                isExit = true;
            }
        });
        
        // Add an entry menu the Exit
        //Was commented on. Else cycle could not to exit,
        //because anonimous method was worked only one time
        //WHY? -> IDN!
//        ENTRIES.add(new MenuEntry("5. Вернуться в главное меню") {
//            @Override
//            public void run() {
//                isExit = true;
//            }
//        });
        
    }

    /**
     * Launches the AVLTreeMenu.
     * @see AVLTreeMenu
     */
    public void run() {
        // Endless cycle, while button does not push - isExit = true
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                //Else cycle could not to exit,
                //because anonimous method was worked only one time
                if (choice==5){
                    break;
                }
                // Is launches that menu entrie, which was chosen by the user.
                MenuEntry entry = ENTRIES.get(choice - 1);
                entry.run();
            }
            catch (NumberFormatException | IndexOutOfBoundsException ex) {
                System.out.println("Ошибка! Вызвано исключение: "+ex.toString()
                        +"\nНекорректно введён пункт меню!"
                        + "\nПопробуйте ввести снова.\n");
            }
            catch (IOException e) {
                System.out.println("Фатальная ошибка во вводе данных!\n");
//                e.printStackTrace();
            }
        }
//        isExit = false;
    }
    
    /**
     * Prints the AVLTreeMenu.
     * @see AVLTreeMenu
     */
    public void printMenu()
        {System.out.println("1. Добавить узел\n" +
//                            "2. Добавить узел к определённому узлу\n" +
//                            "3. Добавить новый корень\n" +
                            "2. Изменить узел\n" +
                            "3. Удалить узел\n" +
                            "4. Нарисовать дерево с ключами\n" +
//                            "7. Нарисовать дерево без ключей\n" +
                            "5. Вернуться в главное меню\n");
        }
    
}
