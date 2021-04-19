import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node> {

    private final List<Integer> keys = new ArrayList<>();
    private final List<Node> kids = new ArrayList<>();
    public Node parent = null;

    public List<Integer> getKeys() {
        return keys;
    }

    public List<Node> getKids() {
        return kids;
    }


    public void addKids(List<Node> listNodes) {
        for (Node n : listNodes) {
            n.parent = this;
            kids.add(n);
        }
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

    public void removeChild(List<Integer> keys){

        for (int i = 0; i <  getKids().size(); i++) {
        if ( getKids().get(i).getKeys().size() == keys.size() &&  getKids().get(i).getKeys().containsAll(keys)) {
            getKids().remove(i);
            break;
        }
    }

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
