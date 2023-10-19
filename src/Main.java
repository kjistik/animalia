import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Tree animals = new Tree("Animalia");
        // there will be a tree with a minimum of 8 levels (kingdom, , phylum, class,
        // order, family, genus, sprecies and subspecies)
        Node current = animals.first;
        current.addChildren(new Node(current, "Mamíferos"));
        control(current, animals);
    }

    static void control(Node current, Tree animals) {
        while (true) {
            select_text(current, animals);
            current = select_input(current.children.size(), current);
        }
    }

    static Node select_input(int num, Node current) {
        int choice;
        boolean flag = false;
        do {
            System.out.println("Ingrese una de las opciones");
            choice = input.nextInt() - 1;
            input.nextLine();
            if (choice >= 0 && 0 <= current.children.size()) {
                current = current.children.get(choice);
                flag = true;
            } else if (choice == -1) {
                current = current.parent;
                flag = true;
            } else if (choice < -1) {
                System.exit(-1);
            }
        } while (!flag);

        return current;
    }

    static void select_text(Node current, Tree animals) {
        if (current != animals.first) {
            System.out.println("[" + 0 + "] - " + current.parent.value);
        }
        for (int i = 0; i < current.children.size(); i++) {
            System.out.println("[" + (i + 1) + "] - " + current.children.get(i).value);
        }
    }
}
