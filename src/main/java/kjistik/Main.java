package kjistik;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String path = "./src/main/resources";
    static File directory = new File(path);

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int select;
        System.out.println("¿Qué árbol quiere cargar?");
        if (directory.isDirectory()) {
            File files[] = directory.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + " - " + files[i].getName());
            }
            select = input.nextInt();
            input.nextLine();
            control(files[select - 1], input);
        } else {
            System.out.println("No hay archivos");
        }
    }

    static void control(File file, Scanner input) {
        System.out.println("Cargando los datos desde: " + file.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(file));
            JSONObject rootNode = (JSONObject) obj;
            Tree tree = buildTree(rootNode);
            System.out.println("Primer nodo: " + tree.first.value);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    static Tree buildTree(JSONObject jsonObject) {
        Tree tree = new Tree();
        tree.first = buildNode(jsonObject);
        return tree;
    }

    static Node buildNode(JSONObject jsonObject) {
        Node node = new Node();
        node.value = (String) jsonObject.get("value");
        node.children = new ArrayList<>();

        JSONArray childrenArray = (JSONArray) jsonObject.get("children");
        if (childrenArray != null) {
            for (Object child : childrenArray) {
                node.children.add(buildNode((JSONObject) child));
            }
        }

        return node;
    }
}
