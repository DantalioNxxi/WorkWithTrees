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
class MenuMain {
    //Массив пунктов меню
    private List <MenuEntry> entries = new ArrayList<MenuEntry>();
    private boolean isExit = false;

    public MenuMain() {
        //Инициализацию пока оставил, так как ещё не ловил NullPointerException
        BinaryTree tree = new BinaryTree();
        
        entries.add(new MenuEntry("1. Сформировать бинарное дерево") {
            @Override
            public void run() {
                //System.out.println("Дерево создано.\n");
                TreeMenu menutree = new TreeMenu(tree); //Возможно исключение NPE
                menutree.run();
            }
        });
        
        entries.add(new MenuEntry("2. Нарисовать дерево с ключами") {
            @Override
            public void run() {
                tree.print(true);
            }
        });
        
        entries.add(new MenuEntry("3. Нарисовать дерево без ключей") {
            @Override
            public void run() {
                tree.print(false);
            }
        });
        // Добавляем пункт меню Exit
        entries.add(new MenuEntry("4. Выход") {
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
        {System.out.println("1. Сформировать бинарное дерево\n" +
                            "2. Нарисовать дерево с ключами\n" +
                            "3. Нарисовать дерево без ключей\n" +
                            "4. Выход\n");
        }
}
