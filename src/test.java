public class test {
    public static void main(String[] args) {
        Tree tree = new Tree(4);
        Printer p = new Printer();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(7);
        tree.add(8);
        tree.add(9);
        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(13);
        tree.add(14);
        tree.add(15);
        tree.add(16);
        tree.add(7);
        tree.add(9);
        tree.add(19);
        tree.add(20);
        tree.add(21);
        tree.add(22);
        tree.add(23);
        tree.add(24);


        p.printTree(tree);

//        System.out.println(tree.search(1));
//        System.out.println(tree.search(101));
//        System.out.println(tree.search(4));
        System.out.println();


//        tree.deleteKey(6);tree.deleteKey(7);
//        tree.deleteKey(8);tree.deleteKey(9);
//        tree.deleteKey(1);


         tree.deleteKey(9);


//        tree.deleteKey(3);
//        tree.deleteKey(4);
//        tree.deleteKey(10);


//        tree.add(1);
//        tree.add(2);
//        tree.add(3);
//        tree.add(4);
//        tree.add(5);
//        tree.add(6);
        //    tree.deleteKey(6);
//        tree.deleteKey(2);
//        tree.deleteKey(3);
//        tree.deleteKey(4);
//        tree.deleteKey(5);
        p.printTree(tree);
//        System.out.println();
//        tree.deleteKey(3);
//        p.printTree(tree);
    }
}
