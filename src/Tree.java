import java.util.*;

public class Tree {
    Node root;
    int weight;

    Tree(Integer weight) {
        this.weight = weight;
    }

    public void add(int value) {
        if (root == null) {
            root = new Node();
            root.getKeys().add(value);
        } else {

            obhod(root, value);
        }


    }

    private void obhod(Node node, int value) {
        for (int i = 0; i < node.getKeys().size(); i++) {
            if (value <= node.getKeys().get(i) || value >= node.getKeys().get(i) && (node.getKeys().size() - i == 1 || value <= node.getKeys().get(i + 1))) {
                if (node.getKids().isEmpty()) {
                    node.getKeys().add(value);
                    Collections.sort(node.getKeys());
                    Collections.sort(node.getKids());
                    check(node.getKeys(), node);
                    return;
                } else {
                    if (value <= node.getKeys().get(i)) {
                        obhod(node.getKids().get(i), value);
                        return;
                    } else {
                        obhod(node.getKids().get(i + 1), value);
                        return;
                    }
                }
            }
        }
    }


    private void check(List<Integer> keys, Node currentNode) {

        if (keys.size() > weight - 1) {
            Node n = new Node();
            n.setParent(currentNode.parent);
            n.getKeys().add(keys.get(1));
            Node childLeft = new Node();
            childLeft.getKeys().add(keys.get(0));
            Node childRight = new Node();
            for (int i = 0; i < keys.size(); i++) {
                if (i >= 2) childRight.getKeys().add(keys.get(i));
            }
            if (currentNode.getKids().size() > 0) {
                childLeft.addChildren(currentNode.getKids().get(0));
                childLeft.addChildren(currentNode.getKids().get(1));
                for (int i = 0; i < currentNode.getKids().size(); i++) {
                    if (i >= 2) childRight.addChildren(currentNode.getKids().get(i));
                }
            }
            n.getKids().add(childLeft);
            n.getKids().add(childRight);

            if (currentNode.parent == null) {
                childLeft.setParent(n);
                childRight.setParent(n);
                Collections.sort(n.getKids());
                root = n;
            } else {
                currentNode.parent.getKeys().add(keys.get(1));
                currentNode.parent.getKids().remove(findChildren(currentNode.parent, keys));
                childLeft.setParent(currentNode.parent);
                childRight.setParent(currentNode.parent);
                currentNode.parent.getKids().add(childLeft);
                currentNode.parent.getKids().add(childRight);
                Collections.sort(currentNode.parent.getKids());
                Collections.sort(currentNode.parent.getKeys());
                check(currentNode.parent.getKeys(), currentNode.parent);
            }
        }
    }

    private int findChildren(Node node, List<Integer> keys) {
        for (int i = 0; i < node.getKids().size(); i++) {
            if (node.getKids().get(i).getKeys().size() == keys.size() && node.getKids().get(i).getKeys().containsAll(keys)) {
                return i;
            }
        }
        return -1;
    }

    public void delete() {
    }

    public void find() {
    }
}
