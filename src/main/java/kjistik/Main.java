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
    static boolean flag = true;
    static Tree tree = new Tree();

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
        int op;
        do {
             System.out.println(
                "[1] Imprimir todo el árbol\n"+
                "[2] Buscar un valor especifico\n"+
                "[3] Salir\n"
                ); 
                op = input.nextInt();
                input.nextLine();
                buildOption(op, input); 
        }while(flag);
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
            System.out.println();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    static Tree buildTree(JSONObject jsonObject) {
        tree.first = buildNode(jsonObject);
        return tree;
    }

    static Node buildNode(JSONObject jsonObject) {
        Node node = new Node();
        if(jsonObject.containsKey("type") && jsonObject.containsKey("value")){
            node.type = (String) jsonObject.get("type");
            node.value = (String) jsonObject.get("value");
        }else {
            node.type = null;
            node.value = null;
        }
        node.children = new ArrayList<>();

        JSONArray childrenArray = (JSONArray) jsonObject.get("children");
        if (childrenArray != null) {
            for (Object child : childrenArray) {
                node.children.add(buildNode((JSONObject) child));
            }
        }

        return node;
    }

    static void buildOption(int opcion, Scanner input){
        switch (opcion) {
            case 1:
                StringBuilder sb = new StringBuilder(); 
                dfsPrintTree(tree.first, sb);
                break;
            case 2:
                dfsPrintValue(tree.first, input);
                break;
            case 3:
                flag = false;
                break;
            default:
                break;
        }        
    }

    static void dfsPrintTree(Node node, StringBuilder sb) {

        int length = sb.length();
        sb.append(node.type).append(": ").append(node.value);

        System.out.println(sb.toString());
        sb.setLength(length);

        for(Node child : node.children){
            dfsPrintTree(child, sb);
        }

        System.out.println();
    }

    static void dfsPrintValue(Node node, Scanner input) {
        String value = "";
        System.out.println("Ingrese el valor a buscar: ");
        value = input.nextLine();
        Node data = find(tree.first, value);

        if(data != null) {
            StringBuilder sb = new StringBuilder(); 
            sb.append(data.type).append("\n").append(data.value).append("\n");
            String d = data.children.toString();
            char [] dt = d.toCharArray();

            for(int i = 0; i < dt.length; i++){  
                if(dt[i] != '[' && dt[i] != ']') {
                    sb.append(dt[i]);
                }
            }

            System.out.println();
            System.out.println(sb.toString());
        }
    }

    static Node find(Node node, String value) {
        
        if(node.type.equals(value) || node.value.equals(value)){
            return node;
        }

        for (Node child : node.children) {
            
            Node found = find(child, value);
            if(found != null) {
                return child;
            }
        }
        return null;
    }

}
