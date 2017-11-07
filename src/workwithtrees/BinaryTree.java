/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workwithtrees;

/**
 *
 * @author Пользователь
 */
public class BinaryTree {
    
    static class Node {
        int key;
        int value;
        Node left, right;
       
        Node(int key, int value) {
                this.key = key;
                this.value = value;
        }
        
        public static String nodeToString(Node node){
        if(node == null) return ("-:-");
        return ( Integer.toString(node.key) + ":" + Integer.toString(node.value) );
        }
    }
    
    Node root = null;
//    Node root;
//    public BinaryTree(){}
//    public BinaryTree(int key, int value){root = new Node(key,value,null);}
    
    //Получить значение по ключу
    public int get(int k) {
        Node x = root;
        while (x != null) {
                if (k == x.key) {
                    return x.value;
                }
                if (k < x.key) {
                    x = x.left;
                }
                else {
                    x = x.right;
                }
        }
        return 1_000_000; // Проблема. Почему ноль.
    }
    
    //Получить узел по ключу
    Node getNode(int k) {
        Node x = root;
        while (x != null) {
                if (k == x.key) {
                    return x;
                }
                if (k < x.key) {
                    x = x.left;
                }
                else {
                    x = x.right;
                }
        }
        return null;
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
                }
                else {
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
        }
        else {
                if (k < y.key) {
                    y.left = newNode;
                }
                else {
                    y.right = newNode;
                }
        }
    }
    
    //Задать узлу с ключём k новое значение
    public void set(int k, int newv){
        if (this.containsKey(k)){
            this.add(k, newv);
        }
        else System.out.println("Узла с ключём "+k+" не существует.");
    }
    
    //Удаление элемента по ключу
    //При удалении узла на его место становится узел с ключем наименее большим, чем удалённый
    //При отсутствии узлов с данным ключем дерево остаётся без изменений
    public void remove(int k) {
        if (!this.containsKey(k)){System.out.println("Узла с ключём "+k+" не существует."); return;}
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
    
    //Глубина всего дерева
    public int depth(){
        return depth(root)-1;
    }
    
    //Глубина поддерева для определённого узла
    int depth(Node p)
    {
//        Node temp=p;
        int h1=0,h2=0;
        if(p==null)return 0;
        if(p.left!=null){h1=depth(p.left);}
        if(p.right!=null){h2=depth(p.right);}
        return(Math.max(h1,h2)+1);
    }
    
    //Для проверки:
    void print(Node node,int tcount) {
        if (node != null) {
            StringBuilder str = new StringBuilder("\t");
            for (int i=0; i< tcount;i++) {str.append("\t");}
            System.out.print(str.toString() + node.key + ":" + node.value + " \n"); //Надо будет сократить
            print(node.left, tcount-1);
            print(node.right, tcount+1);
        }
    }

    //Печать дерева
    public void print() {
        System.out.println("Глубина дерева: "+this.depth());
        print(root, (this.depth()));
    }

}
    
//Ниже прочие попытки правильной отрисовки дерева

    //    public void printLeftSubTree (Node node, int level, int tcount){
//        if (level!=0 && node!=null)
//        {
//        StringBuilder str = new StringBuilder("");
//        StringBuilder splitter = new StringBuilder("");
//        for (int i=0;i<level;i++){str.append("\t");} // Отступ
//        for (int i=0;i<tcount;i++){splitter.append("   ");} // Разделитель
//        
//        str.append(Node.nodeToString(node.left));
//        str.append(splitter);
//        str.append(Node.nodeToString(node.right));
//        System.out.print(str);
//        }
//    }
    
    //    public void printSubTree (Node node, int level, int tcount) {
//        if (level!=0 && node!=null)
//        {
////            System.out.println();
//            StringBuilder str = new StringBuilder("");
//            StringBuilder splitter = new StringBuilder("");
//            for (int i=0;i<level;i++){str.append("\t");} // Отступ
//            for (int i=0;i<tcount;i++){splitter.append("   ");} // Разделитель
//            
//            str.append(Node.nodeToString(node.left));
//            str.append(splitter);
//            str.append(Node.nodeToString(node.right));
//            System.out.print(str);
//            
////            System.out.println();
//            printLeftSubTree(node.left, level-1, tcount-1);
//            printRightSubTree(node.right, level-1, tcount-1);
//            
//        }
//        
//    }
    
    
//    public void printRightSubTree (Node node, int level, int tcount){
//        if (level!=0 && node!=null)
//        {
//        StringBuilder str = new StringBuilder("");
//        StringBuilder splitter = new StringBuilder("");
//        for (int i=0;i<tcount;i++){splitter.append("   ");} // Разделитель
//        
//        str.append(Node.nodeToString(node.left));
//        str.append(splitter);
//        str.append(Node.nodeToString(node.right));
//        System.out.print(str);
//        System.out.println();
//        }
//    }
//    
//    void printTree() {
//        int level = this.depth();
//        StringBuilder str = new StringBuilder("\t");
//            for (int i=0; i< level;i++) {str.append("\t");}
//        System.out.print(str.toString() + root.key + ":" + root.value + " \n"); //Нужно будет сократить
//        printSubTree(root, level-1, this.depth()*2);
//        //System.out.println();
//    }
//            if(nodeleft == null) {
//                StringBuilder str = new StringBuilder("  ");
//                    for (int i=0; i< tcount;i++) {str.append("  ");}
//                return (str.toString() + "-" + " \n");
//            }

//            else{
    //        StringBuilder str = new StringBuilder("  ");
    //            for (int i=0; i< tcount;i++) {str.append("  ");}
    //        System.out.print(str.toString() + node.key + ":" + node.value + " \n"); //Нужно будет сократить

//            StringBuilder strl = new StringBuilder("\t");
//                for (int i=0; i< tcount-1;i++) {strl.append("\t");}
//    //        System.out.print(strl.toString() + node.key + ":" + node.value + " \n"); //Нужно будет сократить
//
//            StringBuilder strr = new StringBuilder("\t");
//                for (int i=0; i< tcount+1;i++) {strr.append("\t");}
//    //        System.out.print(strr.toString() + node.key + ":" + node.value + " \n"); //Нужно будет сократить
//
//            String leftSubTree = (strl.toString() + node.key + ":" + node.value + " \n");
//            String rightSubTree = (strr.toString() + node.key + ":" + node.value + " \n");


    //        String leftSubTree = printSubTree(node.left, tcount-1);
    //        String rightSubTree = printSubTree(node.right, tcount+1);
//            System.out.print(leftSubTree + rightSubTree);


//            return (leftSubTree + rightSubTree);
            
    
        
//        if (node != null) {
//            StringBuilder str = new StringBuilder("  ");
//            for (int i=0; i< tcount;i++) {str.append("  ");}
////            System.out.println("\n");
//            System.out.print(str.toString() + node.key + ":" + node.value + " \n"); //Нужно будет сократить
////            if (node.left!=null) System.out.println("\n");
//            System.out.println("");
//            print(node.left, tcount-1);
//            
////            System.out.println("Правый пошёл");
//            print(node.right, tcount+1);
////            System.out.println("\n");
////            System.out.println("Есть узел!");
//        }
////        else System.out.println("Тут нет узла!");
//        else{
//            StringBuilder str = new StringBuilder("  ");
//            for (int i=0; i< tcount;i++) {str.append("  ");}
////            System.out.println("\n");
//            System.out.print(str.toString() + "-:-"); //Нужно будет сократить
//        }
//        
//    }
    


//Для проверки:
//void print(Node node,int tcount) {
//        
//        if (node != null) {
//            System.out.println("Левый пошёл");
//            print(node.left, tcount--);
//            StringBuilder str = new StringBuilder("\t");
//            for (int i=0; i< tcount;i++) {str.append("\t");}
//            System.out.print(tcount + str.toString() + node.key + ":" + node.value + " "); //Нужно будет сократить
//            System.out.println("Правый пошёл");
//            print(node.right, tcount--);
//            System.out.println("Есть узел!");
//        }
//        else System.out.println("Тут нет узла!");
//        
//    }

//public void print() {
//        print(root, (this.depth()));
//        //System.out.println();
//    }