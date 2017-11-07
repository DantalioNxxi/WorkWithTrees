/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workwithtrees;

import java.io.IOException;
/**
 *
 * @author Пользователь
 */
public class WorkWithTrees {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        MenuMain menu = new MenuMain();
        menu.run();

    }
}

   
//    BinaryTree tree = new BinaryTree();
//        tree.add(50, 11);
////        System.out.println(tree.get(50));
//        tree.add(50, 500);
////        System.out.println(tree.get(50));
//        tree.add(30, 1);
//        tree.add(20, 2);
//        tree.add(60, 5);
//        tree.add(34, 5);
//        tree.add(35, 5);
//        tree.add(55, 5);
//        tree.add(66, 5);
//        tree.add(70, 5);
//        tree.add(78, 5);
//        tree.add(79, 5);
//        System.out.println("Глубина дерева: "+tree.depth());
//        tree.print();
////        tree.remove(2);
////        tree.remove(3);
////        tree.print();
////        tree.remove(4);
////        tree.print();
////        tree.remove(7); // Ошибка не вызывается
////        System.out.println(tree.depth());
////        System.out.println(tree.depth(tree.getNode(4)));
////        tree.print(tree.getNode(3));
//        //System.out.println(tree.root.key + " " + tree.root.value);
//        tree.set(55, 600);
//        tree.print();
//        tree.set(71, 700);