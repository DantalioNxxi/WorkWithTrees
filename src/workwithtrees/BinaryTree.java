/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workwithtrees;

import java.util.ArrayList;

/**
 *
 * @author Пользователь
 */
public class BinaryTree {

    private static class Node {

        int key;
        int value;
        Node left, right;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            if (this == null) {
                return ("-");
            }
            return (Integer.toString(this.key) + ":" + Integer.toString(this.value));
        }
        
        private String toString(boolean keys) {
            if (this == null) {
                if (keys) return ("-:-");
                else return "-";
            }
            if (keys) return (Integer.toString(this.key) + ":" + Integer.toString(this.value));
            else return (Integer.toString(this.value));
        }
    }

    private Node root = null;

    //Получить значение по ключу
    public int get(int k) {
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
        return 1_000_000; // Проблема. Почему ноль.
    }

    //Получить узел по ключу
    private Node getNode(int k) {
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

    //Проверить наличие узла в дереве по ключу
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
    
    //Добавление новой пары ключ-значение
    //Если узел с таким ключём уже существует, то ему присваивается новое значение
    //Тем самым нельзя создать более одного узла с одинаковыми ключями
    public void add(int k, int v) {
        Node x = root, y = null;
        while (x != null) {
            if (k == x.key) {
                x.value = v;
                return; //Прерывание цикла.
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

    //Задать узлу с ключём k новое значение
    public void set(int k, int newv) {
        if (this.containsKey(k)) {
            this.add(k, newv);
        } else {
            System.out.println("Узла с ключём " + k + " не существует.");
        }
    }

    //Удаление элемента по ключу
    //При удалении узла на его место становится узел с ключем наименее большим, чем удалённый
    //При отсутствии узлов с данным ключем дерево остаётся без изменений
    public void remove(int k) {
        if (!this.containsKey(k)) {
            System.out.println("Узла с ключём " + k + " не существует.");
            return;
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

    //Глубина поддерева для определённого узла
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
    
    //Глубина всего дерева
    public int depth() {
        return depth(root);
    }

    //Заполняет пирамиду pyramid от узла node
    private void fillPir(Node node, int level, ArrayList<StringBuilder> pyramid, boolean keys) {
        //Если рисовать с ключами
        if (keys){
        if (node != null) {
            //Оступ для узла
            pyramid.get(level).append(node.toString(keys) + " ");
            fillPir(node.left, level + 1, pyramid,keys);
            fillPir(node.right, level + 1, pyramid,keys);
        } else {
            if (level < this.depth()) {
                //Как-будто если бы узел был
                pyramid.get(level).append("-:-" + " ");
                //Добавляем гипотетических потомков
                for (int j = level + 1, amountchild = 2; j < this.depth(); j++) {
                    //Рисуем потомков
                    for (int r = 0; r < amountchild; r++) {
                        pyramid.get(j).append("-:-" + " ");
                    }
                    //Количество потомков увеличевается в 2 раза с каждым новым уровнем
                    amountchild *= 2;
                }
            }
        }
        //Если рисовать без ключей
        } else{
            if (node != null) {
            //Оступ для узла
            pyramid.get(level).append(node.toString(keys) + " ");
            fillPir(node.left, level + 1, pyramid, keys);
            fillPir(node.right, level + 1, pyramid, keys);
        } else {
            if (level < this.depth()) {
                //Как-будто если бы узел был
                pyramid.get(level).append("-" + " ");
                //Добавляем гипотетических потомков
                for (int j = level + 1, amountchild = 2; j < this.depth(); j++) {
                    //Рисуем потомков
                    for (int r = 0; r < amountchild; r++) {
                        pyramid.get(j).append("-" + " ");
                    }
                    //Количество потомков увеличевается в 2 раза с каждым новым уровнем
                    amountchild *= 2;
                }
            }
        }
        }
    }

    //Печать дерева
    public void print(boolean keys) {
        System.out.println("=======================");
        System.out.println("Глубина дерева: " + (this.depth()-1) + "\n");
        //Создаём пирамиду
        ArrayList<StringBuilder> pyr = new ArrayList<StringBuilder>();
        //Инциализируем уровни пирамиды
        for (int i = 0; i < this.depth(); i++) {
            pyr.add(i, new StringBuilder("")); //после изменить на ""
        }
        //Заполняем пирамиду
        fillPir(root, 0, pyr, keys);
        
        //Печатаем узлы без отступов:
        System.out.println("Узлы по уровням:");
        for (StringBuilder i : pyr) {
            System.out.println(i);
        }
        
        //Ещё один вариант:
        System.out.println("В виде сжатой пирамиды:");
        for (int l = 0, s = 1; l < this.depth(); l++,s*=2) {
            StringBuilder str = new StringBuilder("");
                for (int j = 0; j < (int) (Math.pow(this.depth() + 1, 2) / 2) - s; j++) {
                    str.append(" ");
                }
            System.out.print(str.toString()+pyr.get(l).toString());
            System.out.println("\n");
        }
        
        //Печатаем пирамиду в виде дерева
        System.out.print("В виде дерева:\n");
        for (int i = 0; i < this.depth(); i++) {
            //Получили массив элементов пирамиды на уровне i 
            String[] mstr = pyr.get(i).toString().split(" ");
            //Печать элементов с учётом отступов
            for (int k = 0, left = 1, couple = 0; k < mstr.length; k++, left *= (-1)) {
                //Если пара потомков пройдена, то рассчитывается промежуточный отступ,
                //который равен обычному отступу предыдущего уровня
                if (couple == 2){
                    couple = 1;
                    StringBuilder str = new StringBuilder("");
                    for (int j = 0; j < ((int) (Math.pow(this.depth() + 1, 2) / (Math.pow(2, i-1)))); j++) {
                        str.append(" ");
                    }
                    System.out.print(str.toString() + mstr[k]);
                }
                //Иначе проверяется сторона потомка и рассчиывается обычный отступ
                else{
                    if (left > 0) {
                        StringBuilder str = new StringBuilder("");
                        for (int j = 0; j <= ((int) (Math.pow(this.depth() + 1, 2) / (Math.pow(2, i)))); j++) {
                            str.append(" ");
                        }
                        System.out.print(str.toString() + mstr[k]);
                    } else {
                        StringBuilder str = new StringBuilder("");
                        for (int j = 0; j <= 2 * ((int) (Math.pow(this.depth() + 1, 2) / (Math.pow(2, i)))); j++) {
                            str.append(" ");
                        }
                        System.out.print(str.toString() + mstr[k]);
                    }
                    couple++;
                }
            }
            System.out.println();
        }
        System.out.println("=======================");

    }
    
}
