import java.util.ArrayList;
import java.util.List;

public class Node {
    Node parent;
    List<Node> children = new ArrayList<Node>();
    String value;

    public Node(Node parent, String value) {
        this.parent = parent;
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addChildren(Node children) {
        this.children.add(children);
    }
}
