package kjistik;

import java.util.ArrayList;

class Node {
    String type;
    String value;
    ArrayList<Node> children;
    Node parent;

    public Node(String type, String value, Node parent) {
        this.type = type;
        this.value = value;
        this.children = new ArrayList<Node>();
        this.parent = parent;
    }

    public void addChild(Node child) {
        child.parent = this;
        this.children.add(child);
    }


    @Override
    public String toString() {
        return this.type + "\n" + this.value + "\n" + this.children;
    }
}