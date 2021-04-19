import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node> {

    private List<Integer> keys = new ArrayList<>();
    private List<Node> kids = new ArrayList<>();
    public Node parent;

    public List<Integer> getKeys() {
        return keys;
    }

    public List<Node> getKids() {
        return kids;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addChildren(Node node) {
        node.setParent(this);
        kids.add(node);
    }

    public void addKey(Integer value) {
        keys.add(value);
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
        return this.getKeys().get(0)-  o.getKeys().get(0);
    }
}
