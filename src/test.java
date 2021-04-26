public class test {
    public static void main(String[] args) {
        Tree tree = new Tree(3);
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);

        tree.add(5);
        tree.add(6);
        tree.add(7);
      //  tree.add(8);

//        tree.add(6);
//        tree.add(7);
//        tree.add(8);

//        tree.add(1);
//        tree.add(2);
//        tree.add(3);

//        tree.add(10);
//        tree.add(11);
//        tree.add(12);

//        tree.add(3);
//        tree.add(4);
//        tree.add(5);


        Printer p = new Printer();
        p.printTree(tree);

//        System.out.println(tree.search(1));
//        System.out.println(tree.search(101));
//        System.out.println(tree.search(4));
        System.out.println();
        tree.deleteKey(5);
        p.printTree(tree);
        System.out.println();
        tree.deleteKey(1);
        p.printTree(tree);
        System.out.println();
        tree.deleteKey(2);
        p.printTree(tree);
        System.out.println();
        tree.deleteKey(7);
        p.printTree(tree);
        System.out.println();
        tree.deleteKey(3);
        p.printTree(tree);
        System.out.println();
        tree.deleteKey(4);
        p.printTree(tree);
        System.out.println();
        tree.deleteKey(6);
        p.printTree(tree);
//        System.out.println();
//        tree.deleteKey(3);
//        p.printTree(tree);
    }
}
