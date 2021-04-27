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

//    public boolean search(Integer value) {
//        findNode = null;
//        searchNode(value, root);
//        return findNode != null;
//
//    }

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
        find(value);
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
                // node = checkState(node, value);
                if (node.getParent() == null && node.getKeys().size() == 1 && node.getKids().isEmpty())
                    return;
                checkState(node, value);
                return;
            }
        }
    }

    private void checkState(Node node, Integer value) {

        //  find(value);
        if (node == null) {
            System.out.println("Элемент не найден");
        } else {
            //node.getKeys().remove(value);


            if (node.getKids().isEmpty()) {
                node.getKeys().remove(value);
                if (node.getKeys().size() == 0) {
                    checkNearNodes(node);
                }
                final String a = "Лист";
            } else if (node.getParent() == null) {
                final String b = "Корень";
            } else {
                final String c = "центральный";
            }

        }


    }

    private void checkNearNodes(Node node) {
        Node parent = node.getParent();
        if (parent == null) {
            node.getChild(0).setParent(null);
            root = node.getChild(0);
            return;
        }
        Integer index = node.getParent().getIndex(node);

        if (node.getKeys().size() != 0) return;
        if (index > 0 && parent.getKids().get(index - 1).getKeys().size() > 1) {
            //если у левого ребенка ключей больше минимального
            //set new value for current node

            node.addKey(parent.getKeys().get(index - 1));
            Node leftChild = parent.getChild(index - 1);
            Integer maxValueOfLeftChild = leftChild.getKeys().get(leftChild.getKeys().size() - 1);
            parent.getKeys().set(index - 1, maxValueOfLeftChild);
            //delete key from left brother
            leftChild.removeKey(leftChild.getKeys().size() - 1);
            if (leftChild.getKids().size() > 0) {
                //После соединения перекинуть лишнего ребенка на нод
                node.addChild(leftChild.getKids().get(leftChild.getKids().size() - 1));
                leftChild.removeChild(leftChild.getKids().size() - 1);
            }
        } else if (index + 1 < parent.getKids().size() && parent.getChild(index + 1).getKeys().size() > 1) {
            //если у правого ребенка ключей больше минимального
            //set new value for current node
            node.addKey(parent.getKeys().get(index));
            Node rightChild = parent.getChild(index + 1);

            Integer minValueOfRightChild = rightChild.getKeys().get(0);
            //set new value for parent
            parent.getKeys().set(index, minValueOfRightChild);
            //delete key from right brother
            rightChild.removeKey(0);
            if (rightChild.getKids().size() > 0) {
                //После соединения перекинуть лишнего ребенка на нод
                node.addChild(rightChild.getKids().get(0));
                rightChild.removeChild(0);
            }
        } else {
            //поделиться ключом некому
            //спускаем - мержим рукурсивно передавая новый нод и батю
            Node n = new Node();
            n.addKids(node.getKids());
            if (index == 0 || index == 1) {
                n.addKey(parent.getKeys().get(0));
                parent.removeKey(0);
                if (index == 0) {
                    n.addKids(parent.getChild(index + 1).getKids());
                    n.addKey(parent.getChild(index + 1).getKeys().get(0));
                    parent.removeChild(index + 1);
                    parent.removeChild(index);
                } else {
                    n.addKids(parent.getChild(0).getKids());
                    n.addKey(parent.getChild(0).getKeys().get(0));
                    parent.removeChild(index);
                    parent.removeChild(0);
                }
            } else {
                n.addKids(parent.getChild(index - 1).getKids());
                n.addKey(parent.getKeys().get(index - 1));
                parent.removeKey(index - 1);
                n.addKey(parent.getChild(index - 1).getKeys().get(0));
                parent.removeChild(index);
                parent.removeChild(index - 1);
            }

            parent.addChild(n);
            if (parent.getKeys().size() == 0) {
                checkNearNodes(parent);
            }
        }

    }


//    private void searchNode(Integer value, Node node) {
//        for (int i = 0; i < node.getKeys().size(); i++) {
//            if (value < node.getKeys().get(i)
//                    || value > node.getKeys().get(i)
//                    && (node.getKeys().size() - i == 1 || value < node.getKeys().get(i + 1))) {
//                if (value <= node.getKeys().get(i)) {
//                    if (!node.getKids().isEmpty()) {
//                        searchNode(value, node.getKids().get(i));
//                        return;
//                    }
//                } else {
//                    if (!node.getKids().isEmpty()) {
//                        searchNode(value, node.getKids().get(i + 1));
//                        return;
//                    }
//                }
//            } else if (value.equals(node.getKeys().get(i))) {
//                findNode = node;
//                return;
//            }
//        }
//    }
}
