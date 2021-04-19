import java.util.ArrayList;
import java.util.List;

public class Printer {
    private static int levels = 0;
    private static List<String> levelsStrings = new ArrayList<>();

    public static void print2(Tree tree) {
        newObhod(tree.root);
        for (int i = levelsStrings.size() - 1; i > 0; i--) {
            String newParent = refactorParent(levelsStrings.get(i), levelsStrings.get(i - 1));
            levelsStrings.set(i - 1, newParent);
        }
        for (int i = 0; i < levelsStrings.size(); i++) {
            System.out.println(levelsStrings.get(i));
        }
    }

    private static List<Integer> getIndexesOfSpace(String str) {
        List<Integer> list = new ArrayList<>();
        String newStr = str.trim();
        int delta = str.length() - newStr.length();

        for (int i = 0; i < newStr.length(); i++) {
            int lastInd = i;
            while (lastInd < newStr.length() && String.valueOf(newStr.charAt(lastInd)).equals(" ")) {
                lastInd++;
            }
            if (lastInd != i) {
                list.add(delta + i + (lastInd - i) / 2);
                i = lastInd;
            }

        }
        return list;
    }

    private static String refactorParent(String down, String up) {

        String[] upArr = up.split(" ");
        StringBuilder newParent = new StringBuilder();
        List<Integer> indexes = getIndexesOfSpace(down);
        int indParent = 0;
        for (int i = 0; i < indexes.size(); i++) {

            if (i % upArr.length == 0) {
                for (int j = i; j < indexes.get(i); j++) {
                    newParent.append(" ");
                }
                newParent.append(upArr[indParent]);
                indParent++;
            }
        }
        return newParent.toString();

    }

    private static void newObhod(Node node) {
        addNewEl(levels, getKeysAsString(node.getKeys()));
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

    private static void addNewEl(int level, String str) {
        if (levelsStrings.size() > level) {
            String newState = levelsStrings.get(level) + " " + str;
            levelsStrings.set(level, newState);
        } else {
            levelsStrings.add(level, str);
        }
    }
}
