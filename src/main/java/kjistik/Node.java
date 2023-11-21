package kjistik;

import java.util.ArrayList;

class Node {
    String type;
    String value;
    ArrayList<String> path;
    ArrayList<Node> children;

    @Override
    public String toString() {
        return this.type + "\n" + this.value + "\n" + this.children;
    }
}