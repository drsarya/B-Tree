
import java.util.ArrayList;
import java.util.List;

public class Printer {
    private static int levels = 0;
    private static List<List<Node>> nodes = new ArrayList<>();

    public static void printTree(Tree tree) {
        List<String> levels = new ArrayList<>();
        walker(tree.getRoot());
        levels.add(takeNodeVisual((tree.getRoot().getKeys())));
        for (int i = 0; i < nodes.size() - 1; i++) {
            String newLevelDescription = levelTreeToString(nodes.get(i));
            levels.add(newLevelDescription);
        }
        for (String level : levels) {
            System.out.println(level);
        }
    }

    private static String levelTreeToString(List<Node> nodes) {
        StringBuilder commonParent = new StringBuilder();
        for (Node node : nodes) {
            for (int j = 0; j < node.getKids().size(); j++) {
                commonParent.append(takeNodeVisual(node.getKids().get(j).getKeys()));
            }
            commonParent.append("  ");
        }
        return commonParent.toString();
    }

    private static void walker(Node node) {
        if (nodes.size() == levels) {
            nodes.add(levels, new ArrayList<>());
        }
        nodes.get(levels).add(node);
        for (int i = 0; i < node.getKids().size(); i++) {
            levels++;
            walker(node.getKids().get(i));
            levels--;
        }
    }

    private static String takeNodeVisual(List<Integer> keys) {
        return "|" + getKeysAsString(keys) + "| ";
    }

    private static String getKeysAsString(List<Integer> keys) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            s.append(keys.get(i));
            if (i < keys.size() - 1)
                s.append(",");
        }
        return s.toString();
    }

}
