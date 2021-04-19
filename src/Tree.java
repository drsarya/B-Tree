import java.util.*;

public class Tree {
    private Node root;
    private final int weight;
    private Node findNode = null;

    Tree(Integer weight) {
        this.weight = weight;
    }

    public Node getRoot() {
        return root;
    }

    public void add(int value) {
        if (root == null) {
            root = new Node();
            root.addKey(value);
        } else {
            walker(root, value);
        }
    }

    public Node find(Integer value) {
        findNode = null;
        searchNode(value, root);
        return findNode;

    }

    public boolean search(Integer value) {
        findNode = null;
        searchNode(value, root);
        return findNode != null;

    }

    private void walker(Node node, int value) {
        for (int i = 0; i < node.getKeys().size(); i++) {
            if (value <= node.getKeys().get(i) || value >= node.getKeys().get(i) && (node.getKeys().size() - i == 1 || value <= node.getKeys().get(i + 1))) {
                if (node.getKids().isEmpty()) {
                    node.addKey(value);
                    check(node.getKeys(), node);
                } else {
                    if (value <= node.getKeys().get(i)) {
                        walker(node.getKids().get(i), value);
                    } else {
                        walker(node.getKids().get(i + 1), value);
                    }
                }
                return;
            }
        }
    }

    private void check(List<Integer> keys, Node currentNode) {
        if (keys.size() > weight - 1) {
            Node childLeft = new Node();
            Node childRight = new Node();
            childLeft.addKey(keys.get(0));
            childRight.addKey(keys.subList(2, keys.size()));
            if (currentNode.getKids().size() > 0) {
                childLeft.addKids(Arrays.asList(currentNode.getKids().get(0), currentNode.getKids().get(1)));
                childRight.addKids(currentNode.getKids().subList(2, currentNode.getKids().size()));
            }
            if (currentNode.parent == null) {
                Node n = new Node();
                n.addKey(keys.get(1));
                n.addKids(Arrays.asList(childLeft, childRight));
                root = n;
            } else {
                currentNode.parent.addKey(keys.get(1));
                currentNode.parent.removeChild(keys);
                currentNode.parent.addKids(Arrays.asList(childLeft, childRight));
                check(currentNode.parent.getKeys(), currentNode.parent);
            }
        }
    }

    public void deleteKey(Integer value) {
        obhod(value);
    }

    private void obhod(Integer value) {
        find(value);
        if (findNode == null) {
            System.out.println("Элемент не найден");
        } else {
            if (findNode.getKeys().size() > 1 && findNode.getKids().isEmpty()) {
                findNode.getKeys().remove(value);
            } else {
                if (findNode.parent != null) {
                    if (findNode.parent.getKeys().size() > 1) {

                    } else {
                        System.out.println(value + " refactor node");
                    }
                } else {
                    System.out.println(value + " parent is null");
                }
            }
        }
    }

    private void searchNode(Integer value, Node node) {
        for (int i = 0; i < node.getKeys().size(); i++) {
            if (value < node.getKeys().get(i)
                    || value > node.getKeys().get(i)
                    && (node.getKeys().size() - i == 1 || value < node.getKeys().get(i + 1))) {
                if (value <= node.getKeys().get(i)) {
                    if (!node.getKids().isEmpty()) {
                        searchNode(value, node.getKids().get(i));
                        return;
                    }
                } else {
                    if (!node.getKids().isEmpty()) {
                        searchNode(value, node.getKids().get(i + 1));
                        return;
                    }
                }
            } else if (value.equals(node.getKeys().get(i))) {
                findNode = node;
                return;
            }
        }
    }
}
