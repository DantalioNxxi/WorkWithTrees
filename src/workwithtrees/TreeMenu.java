/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workwithtrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Пользователь
 */
class TreeMenu {
    //Массив пунктов меню
    private List <MenuEntry> entries = new ArrayList<MenuEntry>();
    private boolean isExit = false;

    public TreeMenu(BinaryTree tree) {
        entries.add(new MenuEntry("1. Добавить узел") {
            @Override
            public void run() {
//                System.out.println("1 tree run...\n");
                System.out.println("Введите ключ и значение узла через пробел:\n");
//                InputStreamReader isr2 = new InputStreamReader(System.in);
                try {
                    BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                    String x = readernode.readLine();
                    
//                    readernode.close(); // Ошибка stream.close!

                    String[] str = x.split(" ");
                    int[] kv = new int[str.length];

                    for (int i = 0; i < str.length; i++) {
                        kv[i] = Integer.parseInt(str[i]);
                    }
                    
                    System.out.println("Узел "+kv[0]+":"+kv[1]+"\n");
                    tree.add(kv[0], kv[1]);
                    tree.print();
//                    isr2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        entries.add(new MenuEntry("2. Изменить узел") {
            @Override
            public void run() {
                System.out.println("Введите ключ и новое значение узла через пробел:\n");
                try {
                    BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                    String x = readernode.readLine();

                    String[] str = x.split(" ");
                    int[] kv = new int[str.length];

                    for (int i = 0; i < str.length; i++) {
                        kv[i] = Integer.parseInt(str[i]);
                    }
                    
                    System.out.println("Узел "+kv[0]+":"+kv[1]+"\n");
                    tree.set(kv[0], kv[1]);
                    tree.print();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        entries.add(new MenuEntry("3. Удалить узел") {
            @Override
            public void run() {
                System.out.println("Введите ключ узла:\n");
                try {
                    BufferedReader readernode = new BufferedReader(new InputStreamReader(System.in));
                    String x = readernode.readLine();
                    
                    int k = Integer.parseInt(x);
                    
                    tree.remove(k);
                    tree.print();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        
        entries.add(new MenuEntry("4. Нарисовать дерево с ключами") {
            @Override
            public void run() {
                System.out.println("4 tree run...\n");
                tree.print();
            }
        });
        
        entries.add(new MenuEntry("5. Нарисовать дерево без ключей") {
            @Override
            public void run() {
                System.out.println("5 tree run...\n");
                tree.print();
            }
        });
        // Добавляем пункт меню Exit
        entries.add(new MenuEntry("6. Вернуться в главное меню") {
            @Override
            public void run() {
                isExit = true;
            }
        });
        
    }

    public void run() {
        // Бесконечный цикл, пока не нажали кнопку выход
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!isExit) {
            printMenu();
            try {
                String line = reader.readLine();
                int choice = Integer.parseInt(line);
                // Выбираем нажатый пункт меню и выполняем его код
                MenuEntry entry = entries.get(choice - 1);
                entry.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void printMenu()
        {System.out.println("1. Добавить узел\n" +
                            "2. Изменить узел\n" +
                            "3. Удалить узел\n" +
                            "4. Нарисовать дерево с ключами\n" +
                            "5. Нарисовать дерево без ключей\n" +
                            "6. Вернуться в главное меню\n");
        }
}
