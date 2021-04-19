
import java.util.ArrayList;
import java.util.List;

public class Printer {
    private static int levels = 0;
    private static List<String> levelsStrings = new ArrayList<>();
    private static List<List<Node>> nodes = new ArrayList<>();

    public static void print2(Tree tree) {
        List<String> levels = new ArrayList<>();

        newObhod(tree.root);
        levels.add(takeNodeVisual( (tree.root.getKeys())));
        for (int i = 0; i < nodes.size() - 1; i++) {
            String newLevelDescription = createString(nodes.get(i));
            levels.add(newLevelDescription);
        }
        for (int i = 0; i <levels.size() ; i++) {
            System.out.println(levels.get(i));
        }
    }

    private static String createString(List<Node> nodes) {
        StringBuilder commonParent = new StringBuilder();
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.get(i).getKids().size(); j++) {
                commonParent.append(takeNodeVisual(nodes.get(i).getKids().get(j).getKeys()));
            }
            commonParent.append("  ");
        }
        return commonParent.toString();
    }

    private static String takeNodeVisual(List<Integer> keys) {
        StringBuilder commonParent = new StringBuilder();
        commonParent.append("|");
        commonParent.append(getKeysAsString(keys));
        commonParent.append("| ");
        return commonParent.toString();
    }

    private static void newObhod(Node node) {
        if (nodes.size() == levels) {
            nodes.add(levels, new ArrayList<>());
        }
        nodes.get(levels).add(node);
        for (int i = 0; i < node.getKids().size(); i++) {
            levels++;
            newObhod(node.getKids().get(i));
            levels--;
        }
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
