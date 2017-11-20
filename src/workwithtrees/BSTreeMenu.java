
package workwithtrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of the Menu for work with Binary Search Tree.
 * Object is created and launched by method run() of the MenuMain.
 * An one menu entry added into array of entries by anonymous class,
 * which implements an abstract class MenuEntry.
 * @author DantalioNxxi
 * @version 1.3
 * @since 1.0
 * @see MenuMain
 * @see BinarySearchTree
 * @see MenuEntry
 */
class BSTreeMenu {
    /**An array of the entries for the BSTreeMenu*/
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
     * Constructor of the BSTree Menu.
     * Added menu entries into array of the entries and describes their
     * by the anonymous classes, which implements an abstract class MenuEntry.
     * @param tree is tree, with user will be worked and changed.
     * @see MenuEntry
     */
    public BSTreeMenu(BinarySearchTree tree) {
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
        
        ENTRIES.add(new MenuEntry("2. Добавить узел к определённому узлу") {
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println("Введите ключ родителя, ключ и значение узла потомка через пробел: ");
                        BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                        String x = readernode.readLine();
                        
                        int[] kkv = splitter(x);
                        if (kkv.length>3){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        
                        tree.addChild(kkv[0], kkv[1], kkv[2]);
                        tree.printOnlyKeys();
                        break;
                    }
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException ex){
                        System.out.println("Ошибка! Вызвано исключение: "+ex.toString()+
                                "\nПопробуйте ввести данные снова.\n");
                    }
                    catch (IllegalArgumentException ex) { // - NFE up, because he extends IAE
                        System.out.println(ex.toString());
                        break;
                    }
                    catch (IOException ex){
                        System.out.println("Фатальная ошибка во вводе данных!\n");
//                        ex.printStackTrace();
                        break;
                    }
                }
            }
        });
        
        ENTRIES.add(new MenuEntry("3. Добавить новый корень") {
            @Override
            public void run() {
                while(true){
                    try {
                        System.out.println("Введите ключ и значение нового корня через пробел: ");
                        BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                        String x = readernode.readLine();
                        
                        int[] kv = splitter(x);
                        if (kv.length>2){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        
                        tree.addNewRoot(kv[0], kv[1]);
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
        
        ENTRIES.add(new MenuEntry("3. Изменить узел") {
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
                            tree.printInAllVariants(true);
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
        
        ENTRIES.add(new MenuEntry("4. Удалить узел") {
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
        
        ENTRIES.add(new MenuEntry("5. Нарисовать дерево с ключами") {
            @Override
            public void run() {
                if (tree.depth()==0){
                    System.out.println("Сначала добавьте в дерево узлы!\n");
                }
                else{
                    tree.printInAllVariants(true);
                }
            }
        });
        
        ENTRIES.add(new MenuEntry("6. Нарисовать дерево без ключей") {
            @Override
            public void run() {
                if (tree.depth()==0){
                    System.out.println("Сначала добавьте в дерево узлы!\n");
                }
                else{
                    tree.printInAllVariants(false);
                }
            }
        });
        // Add an entry menu the Exit
        ENTRIES.add(new MenuEntry("7. Вернуться в главное меню") {
            @Override
            public void run() {
                isExit = true;
            }
        });
        
    }

    /**
     * Launches the BSTreeMenu.
     * @see BSTreeMenu
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
                MenuEntry entry = ENTRIES.get(choice - 1);
                entry.run();
            }
            catch (NumberFormatException | IndexOutOfBoundsException ex) {
                System.out.println("Ошибка! Вызвано исключение: "+ex.toString()+"\nНекорректно введён пункт меню!"
                        + "\nПопробуйте ввести снова.\n");
            }
            catch (IOException e) {
                System.out.println("Фатальная ошибка во вводе данных!\n");
//                e.printStackTrace();
            }
        }
    }
    
    /**
     * Prints the BSTreeMenu.
     * @see BSTreeMenu
     */
    public void printMenu()
        {System.out.println("1. Добавить узел\n" +
                            "2. Добавить узел к определённому узлу\n" +
                            "3. Добавить новый корень\n" +
                            "4. Изменить узел\n" +
                            "5. Удалить узел\n" +
                            "6. Нарисовать дерево с ключами\n" +
                            "7. Нарисовать дерево без ключей\n" +
                            "8. Вернуться в главное меню\n");
        }
}
