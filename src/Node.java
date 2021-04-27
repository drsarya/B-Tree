import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node> {

    private List<Integer> keys = new ArrayList<>();
    private List<Node> kids = new ArrayList<>();
    public Node parent = null;

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public List<Node> getKids() {
        return kids;
    }

    public Node getChild(Integer index) {
        return kids.get(index);
    }

    public void addKids(List<Node> listNodes) {
        for (Node n : listNodes) {
            n.parent = this;
            kids.add(n);
        }
        Collections.sort(getKids());
    }

    public int getIndexFromParent(Node node) {

        return getKids().indexOf(node);
    }

    public int getIndexFromKeys(Integer value) {

        return getKeys().indexOf(value);
    }

    public void addChild(Node listNodes) {

        listNodes.parent = this;
        kids.add(listNodes);
        Collections.sort(getKids());
    }

    public void addKey(List<Integer> value) {
        keys.addAll(value);
        Collections.sort(getKeys());
    }

    public void addKey(Integer value) {
        keys.add(value);
        Collections.sort(getKeys());
    }

    public Node getParent() {
        return parent;
    }

    public void reload(List<Integer> keys) {
        this.keys = new ArrayList<>();
        this.keys.addAll(keys);
        kids = new ArrayList<>();
    }

    public void removeChild(List<Integer> keys) {
        for (int i = 0; i < getKids().size(); i++) {
            if (getKids().get(i).getKeys().size() == keys.size() && getKids().get(i).getKeys().containsAll(keys)) {
                getKids().remove(i);
                break;
            }
        }
    }

    public void removeChild(int index) {
        getKids().remove(index);

    }

    public void removeKey(int index) {

        keys.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(keys, node.keys) &&
                Objects.equals(kids, node.kids) &&
                Objects.equals(parent, node.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keys, kids, parent);
    }

    @Override
    public int compareTo(Node o) {
        return this.getKeys().get(0) - o.getKeys().get(0);
    }
}
