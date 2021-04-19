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
        tree.add(8);

        tree.add(6);
        tree.add(7);
        tree.add(8);

        tree.add(1);
        tree.add(2);
        tree.add(3);

        tree.add(10);
        tree.add(11);
        tree.add(12);

        tree.add(3);
        tree.add(4);
        tree.add(5);


      //  Printer.print2(tree);
    }
}
